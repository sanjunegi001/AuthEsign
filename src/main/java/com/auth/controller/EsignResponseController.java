package com.auth.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.SignatureException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.auth.bean.EsignResp;
import com.auth.model.EsignDetail;
import com.auth.model.EsignSession;
import com.auth.service.EsignDetailService;
import com.auth.service.EsignSessionService;
import com.auth.util.AUAUtilities;
import com.auth.util.ESIGNProperties;
import com.auth.util.XMLConverter;
import com.auth.util.signPDF;
import com.nsdl.esign.preverifiedNo.service.EsignService;


@Controller
public class EsignResponseController {

	public String UPLOADED_FOLDER="";
	public String tickimagepath="";
	public String signedpdfpath="";
	public String authmode="";
	
	@Autowired
	EsignSessionService essessioservice;
	
	@Autowired
	EsignDetailService esigndetailservice;
	
	/**
	 * 
	 * @method for handle response from NSDL
	 * @param eSignResp
	 * @param aadhaar
	 * @param request
	 * @param session
	 * @param model
	 * @return
	 * @throws IOException
	 * @throws SignatureException
	 */
	@RequestMapping(value = { "/response" }, method = RequestMethod.POST)
	public String resps(@RequestParam("msg") String eSignResp, @RequestParam("obj") String aadhaar,
			HttpServletRequest request, ModelMap model) throws IOException, SignatureException {
		
		ESIGNProperties.load();
		/** Set Response Time **/
		String response_time = "";
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
		Date reqdate = new Date();
		response_time = dateFormat.format(reqdate);
		
		/**Initialize variables**/
		EsignService esingservice=new EsignService();
		String ipaddress = AUAUtilities.getClientIpAddr(request);
		signPDF spdf = new signPDF(eSignResp);
		File inFile = null;
		File outFile = null;
		byte []pdf = null;
		XMLConverter XMLconverter = new XMLConverter();
		EsignResp esignresp = new EsignResp();
		HashMap<Integer,byte[]>mapsign=new HashMap<Integer,byte[]>();
		List<Long> listdocid=new ArrayList<Long>();
		List<String> listdoctimestamp=new ArrayList<String>();
		tickimagepath=ESIGNProperties.getTickimagepath();
		UPLOADED_FOLDER=ESIGNProperties.getUploadpath();
		

		try {

			esignresp = (EsignResp) XMLconverter.convertFromXMLToObject(esignresp, eSignResp);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			
			request.getSession().setAttribute("esigntransactionnm","");
			request.getSession().setAttribute("error", "A2011");
			request.getSession().setAttribute("message", "Something Went Wrong");
			return "esignError";
			

		}
		
		EsignDetail esigndetail=new EsignDetail();
		
		try{
			
			EsignSession esession=essessioservice.findBytxn(esignresp.getTxn());
			
			if (esignresp.getStatus().equalsIgnoreCase("1")) {
				
				
				for (int i = 0; i < esignresp.getSignatures().getDocSignature().length; i++) {
					
					pdf = esignresp.getSignatures().getDocSignature()[i].getValue();
				}
				
				inFile = new File(esession.getPdf_path());
				FileInputStream fis = new FileInputStream(inFile);
				
				
				/**write output file**/
				File file = new File(UPLOADED_FOLDER + File.separator + "signed");
				if (!file.exists()) { // if File is Exist remains as it is
										// otherwise create folder
					if (file.mkdir()) {

					}
				}
				
				outFile = new File(UPLOADED_FOLDER+File.separator+"signed"+File.separator+inFile.getName());
				FileOutputStream fos = new FileOutputStream(outFile);

				// below the block used for spacing in output sign document
				int c;
				byte[] buffer = new byte[8 * 1024];
				while ((c = fis.read(buffer)) != -1) {
					fos.write(buffer, 0, c);
				}
				fis.close();
				signedpdfpath=UPLOADED_FOLDER+File.separator+"signed"+File.separator+inFile.getName();
				
				mapsign.put(1, pdf);
				listdocid.add((long) 1);
				listdoctimestamp.add(esession.getDoc_timestamp());
				authmode=(esession.getAuth_mode()==1?"OTP":"BIO");
				
				esingservice.getSignOnPdf(mapsign, listdocid, listdoctimestamp, signedpdfpath, 
						tickimagepath, 10, 1, esession.getEsign_name(), esession.getEsign_location(), esession.getEsign_reason(),
						40, 60, 160, 70, "");
				
				esigndetail.setEsign_status(1);
				esigndetail.setEsign_aadhaar(aadhaar);
				esigndetail.setEsign_auth_type(authmode);
				esigndetail.setEsign_file_path(outFile.getAbsolutePath().replaceAll(".pdf", "_signedFinal.pdf"));
				esigndetail.setEsign_reason(esignresp.getResCode());
				esigndetail.setEsign_reasoncode(esignresp.getErrCode());
				esigndetail.setEsign_timestamp(esignresp.getTs());
				esigndetail.setEsign_transaction_id(esignresp.getTxn());
				esigndetail.setEsign_request_on(esession.getRequest_time());
				esigndetail.setEsign_response_on(new Timestamp(dateFormat.parse(response_time).getTime()));
				esigndetail.setEsign_env("PREPROD");
				esigndetail.setEsign_ip_address(ipaddress);
				esigndetail.setEsign_request_by(esession.getRequested_by());
				esigndetailservice.save(esigndetail);
				request.getSession().setAttribute("esigntransactionnm", esignresp.getTxn());
				request.getSession().setAttribute("message", "Esign Prosses Successfully Done");
				return "esignSuccess";
				
			}else {
				
				esigndetail.setEsign_status(0);
				esigndetail.setEsign_aadhaar(aadhaar);
				esigndetail.setEsign_auth_type(authmode);
				esigndetail.setEsign_file_path("");
				esigndetail.setEsign_reason(esignresp.getResCode());
				esigndetail.setEsign_reasoncode(esignresp.getErrCode());
				esigndetail.setEsign_timestamp(esignresp.getTs());
				esigndetail.setEsign_transaction_id(esignresp.getTxn());
				esigndetail.setEsign_request_on(esession.getRequest_time());
				esigndetail.setEsign_response_on(new Timestamp(dateFormat.parse(response_time).getTime()));
				esigndetail.setEsign_env("PREPROD");
				esigndetail.setEsign_ip_address(ipaddress);
				esigndetail.setEsign_request_by(esession.getRequested_by());
				esigndetailservice.save(esigndetail);
				request.getSession().setAttribute("esigntransactionnm", esignresp.getTxn());
				request.getSession().setAttribute("error", esignresp.getErrCode());
				request.getSession().setAttribute("message", esignresp.getErrMsg());
				return "esignError";
			}
			
		}catch(Exception ex) {
			
			request.getSession().setAttribute("esigntransactionnm","");
			request.getSession().setAttribute("error", "A201");
			request.getSession().setAttribute("message", "Something Went Wrong");
			return "esignError";
		}
				
	}
	
}
