package com.auth.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.StringReader;
import java.io.StringWriter;
import java.security.KeyStore;
import java.security.Security;
import java.security.cert.X509Certificate;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.xml.bind.DatatypeConverter;
import javax.xml.crypto.dsig.CanonicalizationMethod;
import javax.xml.crypto.dsig.DigestMethod;
import javax.xml.crypto.dsig.Reference;
import javax.xml.crypto.dsig.SignatureMethod;
import javax.xml.crypto.dsig.SignedInfo;
import javax.xml.crypto.dsig.Transform;
import javax.xml.crypto.dsig.XMLSignature;
import javax.xml.crypto.dsig.XMLSignatureFactory;
import javax.xml.crypto.dsig.dom.DOMSignContext;
import javax.xml.crypto.dsig.keyinfo.KeyInfo;
import javax.xml.crypto.dsig.keyinfo.KeyInfoFactory;
import javax.xml.crypto.dsig.keyinfo.X509Data;
import javax.xml.crypto.dsig.spec.C14NMethodParameterSpec;
import javax.xml.crypto.dsig.spec.TransformParameterSpec;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.xml.sax.InputSource;


import com.auth.util.CustomBase64;
import com.auth.util.HashGenerator;
import com.auth.util.ESIGNProperties;
import com.auth.util.Encrypter;

/**
 * 
 * @class for prepare request for NSDL
 * @author sanjay.negi
 *
 */
public class EsignRequestCreater {
	public static HttpServletRequest requestStatic = null;
	private KeyStore.PrivateKeyEntry keyEntry;
	private static final String KEY_STORE_TYPE = "PKCS12";
	private static final String MEC_TYPE = "DOM";
	private static final String WHOLE_DOC_URI = "";


	/**
	 * @mathod to prepare request for NSDL
	 * @param authmode
	 * @param aadhaar
	 * @param content
	 * @param txn
	 * @return
	 * @throws IOException
	 */
	public String Esigndoc(String authmode,String aadhaar, InputStream content,String txn) throws IOException {
		  
		
		//String propFilePath = "";
		String FinalEsignReq = "";
		//ESIGNProperties.load(propFilePath);
		String timestamp = generateTimeStamp().toString();

//		Log.subaua.info("Esign Document MetaData:" + "ASPID::" + ESIGNProperties.getAspId() +"::Authmode:"+authmode +"::TIMESTAMP::"
//				+ timestamp + "TransactionID::" + txn);
		String encodedEsignXML = GenrateEsignXMLforNSDL(aadhaar, authmode, timestamp, content,txn);
		FinalEsignReq=encodedEsignXML;
		
		return FinalEsignReq;
	
		
		
	}
	
	/**
	 * method to set request esign xml
	 * @param aadhaar
	 * @param AuthMode
	 * @param timestamp
	 * @param content
	 * @param txn
	 * @return
	 * @throws IOException
	 */
	String GenrateEsignXMLforNSDL(String aadhaar,String AuthMode,String timestamp , InputStream content,String txn)
			throws IOException {
		{
			Security.addProvider(new BouncyCastleProvider());
		}
		
		String EsignXML = "";
		String NewEsignXML = "";
		String propFilePath = "";
		//ESIGNProperties.load(propFilePath);

		try {

			
			OutputStream outputStream = null;
			InputStream ins_inp = content;

			outputStream = new FileOutputStream(
					new File(ESIGNProperties.getUploadpath() + File.separator + "demo.pdf"));

			int read = 0;
			byte[] bytes = new byte[8 * 1024];

			while ((read = ins_inp.read(bytes)) != -1) {
				outputStream.write(bytes, 0, read);
			}

			if (outputStream != null) {
				try {
					// outputStream.flush();
					outputStream.close();
					ins_inp.close();
					// again.close();
				} catch (IOException e) {

					e.printStackTrace();
				}
			}

			
			/** Prepare Request Xml **/
			String PDFpath = ESIGNProperties.getUploadpath() + File.separator + "demo.pdf";
			String PDFGenratedHash = HashGenerator.genaratePDFHash(PDFpath);
			

			EsignXML = "<Esign ver=\"2.0\" sc=\"Y\" ts=\"" + timestamp
					+ "\" txn=\"" + txn + "\"  ekycMode=\"U\" ekycId=\"" + aadhaar + "\" ekycIdType=\"A\" aspId=\"" + ESIGNProperties.getAspId()
					+ "\" AuthMode=\"" + AuthMode
					+ "\" responseSigType=\"pkcs7\" preVerified=\"n\" organizationFlag=\"n\" responseUrl=\"" +ESIGNProperties.getResponseURL() + "\" >";
	
			
			EsignXML = EsignXML + "<Docs>";
			EsignXML = EsignXML + "<InputHash id=\"1\" docInfo=\"addharpd signed\" hashAlgorithm=\"SHA256\">" + PDFGenratedHash + "</InputHash>";
			EsignXML = EsignXML + "</Docs>";
			EsignXML = EsignXML + "</Esign>";

			
			
			NewEsignXML = signXML(EsignXML, true, requestStatic);


			

		} catch (Exception e) {
			System.out.println("Esign XML genration error is:" + e.toString());

		}
		return NewEsignXML;

	}
	
	
	/**
	 * 
	 * @param xmlDocument
	 * @param includeKeyInfo
	 * @param request
	 * @return
	 * @throws IOException
	 */
	
	public String signXML(String xmlDocument, boolean includeKeyInfo, HttpServletRequest request) throws IOException {

		{
			Security.addProvider(new BouncyCastleProvider());
		}

		String a = "";

		a = ESIGNProperties.getP12Certificate();

		FileInputStream keyFileStream = null;

		try {

			keyFileStream = new FileInputStream(new File(a));

		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		this.keyEntry = getKeyFromKeyStore(keyFileStream, (ESIGNProperties.getPwd()).toCharArray(),
				ESIGNProperties.getAlias());

		try {
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			dbf.setNamespaceAware(true);
			Document inputDocument = dbf.newDocumentBuilder().parse(new InputSource(new StringReader(xmlDocument)));

			Document signedDocument = sign(inputDocument, includeKeyInfo);
			StringWriter stringWriter = new StringWriter();
			TransformerFactory tf = TransformerFactory.newInstance();
			Transformer trans = tf.newTransformer();
			trans.transform(new DOMSource(signedDocument), new StreamResult(stringWriter));
			return stringWriter.getBuffer().toString();

		} catch (Exception e) {
			throw new RuntimeException("Error while digitally signing the XML document", e);

		}

	}

	/**
	 * Create the SignedInfo
	 * @param xmlDoc
	 * @param includeKeyInfo
	 * @return
	 * @throws Exception
	 */
	private Document sign(Document xmlDoc, boolean includeKeyInfo) throws Exception {

		if (System.getenv("SKIP_DIGITAL_SIGNATURE") != null) {
			return xmlDoc;
		}

		XMLSignatureFactory fac = XMLSignatureFactory.getInstance(MEC_TYPE);
		Reference ref = fac.newReference(WHOLE_DOC_URI, fac.newDigestMethod(DigestMethod.SHA1, null),
				Collections.singletonList(fac.newTransform(Transform.ENVELOPED, (TransformParameterSpec) null)), null,
				null);

		// Create the SignedInfo.
		SignedInfo sInfo = fac.newSignedInfo(
				fac.newCanonicalizationMethod(CanonicalizationMethod.INCLUSIVE, (C14NMethodParameterSpec) null),
				fac.newSignatureMethod(SignatureMethod.RSA_SHA1, null), Collections.singletonList(ref));

		if (keyEntry == null) {
			throw new RuntimeException(
					"Key could not be read for digital signature. Please check value of signature alias and signature password, and restart the Auth Client");
		}

		X509Certificate x509Cert = (X509Certificate) keyEntry.getCertificate();

		KeyInfo kInfo = getKeyInfo(x509Cert, fac);
		DOMSignContext dsc = new DOMSignContext(this.keyEntry.getPrivateKey(), xmlDoc.getDocumentElement());
		XMLSignature signature = fac.newXMLSignature(sInfo, includeKeyInfo ? kInfo : null);
		signature.sign(dsc);

		Node node = dsc.getParent();
		return node.getOwnerDocument();

	}

	/**
	 * Create the KeyInfo containing the X509Data
	 * @param cert
	 * @param fac
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private KeyInfo getKeyInfo(X509Certificate cert, XMLSignatureFactory fac) {

		// Create the KeyInfo containing the X509Data.
		KeyInfoFactory kif = fac.getKeyInfoFactory();
		List x509Content = new ArrayList();
		x509Content.add(cert.getSubjectX500Principal().getName());
		x509Content.add(cert);
		X509Data xd = kif.newX509Data(x509Content);
		return kif.newKeyInfo(Collections.singletonList(xd));
	}

	
	/**
	 * Load the KeyStore and get the signing key and certificate
	 * @param keyFileStream
	 * @param keyStorePassword
	 * @param alias
	 * @return
	 */
	private KeyStore.PrivateKeyEntry getKeyFromKeyStore(FileInputStream keyFileStream, char[] keyStorePassword,
			String alias) {
		// Load the KeyStore and get the signing key and certificate.
		// FileInputStream keyFileStream = null;
		try {

			KeyStore ks = KeyStore.getInstance(KEY_STORE_TYPE);
			ks.load(keyFileStream, keyStorePassword);

			KeyStore.PrivateKeyEntry entry = (KeyStore.PrivateKeyEntry) ks.getEntry(alias,
					new KeyStore.PasswordProtection(keyStorePassword));
			return entry;

		} catch (Exception e) {
			return null;
		} finally {
			if (keyFileStream != null) {
				try {
					keyFileStream.close();
				} catch (IOException e) {
				}
			}
		}

	}

	/**
	 * mathod for getnrate unique transcation id
	 * @return
	 */
	public static String generateTXN()

	{

		int randomPIN = (int) (Math.random() * 9000) + 1000; // Its create
		// randomPIN
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddhhmmssSSS"); // Date&TimeFormat
		// yyyyMMddhhmmssSSS
		String txn = "UKC:eSign:" + randomPIN + ":" + dateFormat.format(new Date());
		return String.valueOf(txn);
	}

	

	
	/**
	 * mathod for generate timestamp
	 * @return
	 */
	public static String generateTimeStamp() {

		Calendar cal = GregorianCalendar.getInstance();
		int month = (cal.get(Calendar.MONTH) + 1);
		String sa = (month < 10) ? ("0" + month) : ("" + month);

		int DAY_OF_MONTH = (cal.get(Calendar.DAY_OF_MONTH));
		String DAY_OF_MONTH1 = (DAY_OF_MONTH < 10) ? ("0" + DAY_OF_MONTH) : ("" + DAY_OF_MONTH);

		int HOUR_OF_DAY = (cal.get(Calendar.HOUR_OF_DAY));
		String HOUR_OF_DAY1 = (HOUR_OF_DAY < 10) ? ("0" + HOUR_OF_DAY) : ("" + HOUR_OF_DAY);

		int MINUTE = (cal.get(Calendar.MINUTE));
		String MINUTE1 = (MINUTE < 10) ? ("0" + MINUTE) : ("" + MINUTE);

		cal.add(Calendar.SECOND, -55);

		int SECOND = (cal.get(Calendar.SECOND));
		String SECOND1 = (SECOND < 10) ? ("0" + SECOND) : ("" + SECOND);

		String as = cal.get(Calendar.YEAR) + "-" + sa + "-" + DAY_OF_MONTH1 + "T" + HOUR_OF_DAY1 + ":" + MINUTE1 + ":"
				+ SECOND1;
		return as;

	}

}