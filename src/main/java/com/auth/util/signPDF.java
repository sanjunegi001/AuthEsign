package com.auth.util;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.awt.print.PageFormat;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.GeneralSecurityException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.SignatureException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.log4j.Logger;
//import org.apache.pdfbox.exceptions.COSVisitorException;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDResources;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.common.PDStream;
//import org.apache.pdfbox.pdmodel.edit.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
//import org.apache.pdfbox.pdmodel.graphics.xobject.PDXObjectImage;
import org.apache.pdfbox.pdmodel.interactive.annotation.PDAnnotation;
import org.apache.pdfbox.pdmodel.interactive.annotation.PDAnnotationWidget;
import org.apache.pdfbox.pdmodel.interactive.annotation.PDAppearanceDictionary;
import org.apache.pdfbox.pdmodel.interactive.annotation.PDAppearanceStream;
import org.apache.pdfbox.pdmodel.interactive.digitalsignature.PDSignature;
import org.apache.pdfbox.pdmodel.interactive.digitalsignature.SignatureInterface;
import org.apache.pdfbox.pdmodel.interactive.digitalsignature.SignatureOptions;
import org.apache.pdfbox.pdmodel.interactive.digitalsignature.visible.PDVisibleSigProperties;
import org.apache.pdfbox.pdmodel.interactive.digitalsignature.visible.PDVisibleSignDesigner;
import org.apache.pdfbox.pdmodel.interactive.form.PDAcroForm;
import org.apache.pdfbox.pdmodel.interactive.form.PDField;
import org.apache.pdfbox.pdmodel.interactive.form.PDSignatureField;
import org.apache.pdfbox.util.Matrix;
import org.w3c.dom.CharacterData;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

import com.auth.bean.EsignResp;
import com.auth.bean.GenericResponce;
import com.itextpdf.text.pdf.TSAClient;
import com.auth.util.EsignRequestCreater;
import com.auth.util.SaveIncrementalSignObject;

public class signPDF implements SignatureInterface {

	public static String OTP = null;
	public static String Aadhaar = "";
	public static String eKYCDATA = null;
	public static String TimeStamp = null;
	public static String AUTHMODE = "";
	public static int Responce = 1;
	public static GenericResponce gr = null;
	public SignatureOptions options = null;
	private static Logger log = Logger.getLogger(signPDF.class);
	public static Integer mapId = 0;
	public static Integer SignetureId = 0;
	public static String msgbody=null;
	public static String imagepath="";
	
	
	public signPDF(String msg){
		msgbody=msg;
		
	}

	

	public signPDF(String authmode, String aadhaar) throws KeyStoreException,
			UnrecoverableKeyException, NoSuchAlgorithmException, CertificateException, IOException {
		Aadhaar = aadhaar; // Value is setting from the constructur
		AUTHMODE=authmode;

	}

	

	
	/**
	 * method to get input xml for redirect URL
	 * @param content
	 * @param txn
	 * @return
	 * @throws IOException
	 */
	public String getResponseNSDL(InputStream content,String txn) throws IOException {
		
		

		EsignRequestCreater Esignauth = new EsignRequestCreater();
		String inputxml = null;
		if(AUTHMODE.equals("1")){
			
			inputxml=Esignauth.Esigndoc("1",Aadhaar, content,txn);
			
		}else if(AUTHMODE.equals("2")){
			inputxml=Esignauth.Esigndoc("2",Aadhaar, content, txn);
			
		}
		
		
		return inputxml;

	}

	@Override
	public byte[] sign(InputStream content) throws IOException {

		// TODO Auto-generated method stub
		byte[] pdf = null;
		String body=msgbody;
		System.out.println("samkau::"+body);
		try {
		    
				XMLConverter XMLconverter = new XMLConverter();
				EsignResp responce = new EsignResp();
				try {

					responce = (EsignResp) XMLconverter.convertFromXMLToObject(responce, body);

				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();

				}	

			gr = new GenericResponce();

		

			if (responce.getStatus().equalsIgnoreCase("1")) {

				Responce = 1;

				for (int i = 0; i < responce.getSignatures().getDocSignature().length; i++) {
					
					pdf = responce.getSignatures().getDocSignature()[i].getValue();
				}
				
				gr.setStatus(responce.getErrMsg());
				gr.setStatuscode(responce.getStatus());
				gr.setReasoncode(responce.getErrCode());
				gr.setReason(responce.getResCode());
				gr.setTransactionid(responce.getTxn());
				gr.setTimestamp(responce.getTs());

			} else {

				Responce = 0;
				pdf = "".getBytes();
				gr.setStatus(responce.getErrMsg());
				gr.setStatuscode(responce.getStatus());
				gr.setReasoncode(responce.getErrCode());
				gr.setReason(responce.getResCode());
				gr.setTransactionid(responce.getTxn());
				gr.setTimestamp(responce.getTs());

				return pdf;

			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}

		return pdf;
	}

	public static String getCharacterDataFromElement(Element e) {
		Node child = e.getFirstChild();
		if (child instanceof CharacterData) {
			CharacterData cd = (CharacterData) child;
			return cd.getData();
		}
		return "";
	}
	
   
    
}
