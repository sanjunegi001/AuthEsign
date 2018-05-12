package com.auth.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.Principal;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jettison.json.JSONObject;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.auth.bean.EsignSessionBean;
import com.auth.model.EsignSession;
import com.auth.service.EsignSessionService;
import com.auth.util.AUAUtilities;
import com.auth.util.ESIGNProperties;
import com.auth.util.EsignRequestCreater;
//import com.auth.util.EsignRequestCreater;
//import com.auth.util.signPDF;
import com.auth.util.signPDF;

@Controller
public class EsignRequestController {

	
	
	@Autowired
	EsignSessionService essesionserivce;
	
	@Autowired
	EsignSessionBean esignsessionbean;
	
	private static Logger LOG = org.slf4j.LoggerFactory.getLogger(EsignRequestController.class);
	
	private String UPLOADED_FOLDER="";
	
	
	@RequestMapping({"/esign"})
	public String esignHome(Principal principal) {
		
		LOG.trace("Entering esign controller, for  landing view.");
		esignsessionbean.setRequested_by(principal.getName());
		return "esign";
	
	}
	
	@RequestMapping({"/esignuser"})
	public String esignuserHome(Principal principal) {
		
		LOG.trace("Entering esign controller, for  landing view.");
		esignsessionbean.setRequested_by(principal.getName());
		return "esignuser";
	
	}
	
	@RequestMapping(value = "/doesign", method = RequestMethod.POST)
	public @ResponseBody String otpdocsign(Principal principal,HttpServletRequest request,
			HttpServletResponse response) throws Exception {
				
		try {
			ESIGNProperties.load();
			UPLOADED_FOLDER = ESIGNProperties.getUploadpath();
			LOG.trace("sanjaynegi"+UPLOADED_FOLDER);
			
			/** Set Global Variables **/
			String aadhaarno="",authmode="",response_time = "",request_time="",requestXML="",requested_by="",
					name="",location="",reason="";
			
			String orgip = AUAUtilities.getClientIpAddr(request);
			
			/*Set Request Time*/
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
			Date reqdate = new Date();
			request_time = dateFormat.format(reqdate);
			
			aadhaarno = request.getParameter("aadhaarno");
			authmode = request.getParameter("authmode");
			name=request.getParameter("name");
			location=request.getParameter("location");
			reason=request.getParameter("reason");
			requested_by=principal.getName();
			List<MultipartFile> files = ((MultipartHttpServletRequest) request)
				    .getFiles("file");
			
			/** Set Transaction Id **/
			String txn = EsignRequestCreater.generateTXN();
			
			String fileName="";
			String extensionOfFileName="";
			JSONObject jesign = new JSONObject();
			
			for (MultipartFile mpf:files) {
				
				fileName=mpf.getOriginalFilename();
				extensionOfFileName = fileName.substring(fileName.lastIndexOf(".") + 1);
				if (null != extensionOfFileName && extensionOfFileName.equalsIgnoreCase("pdf")) {
					
					Date dNow = new Date();
					SimpleDateFormat ft = new SimpleDateFormat("yyyyMMdd_HHmmss");
					String datetime = ft.format(dNow);
					
					signPDF spdf = new signPDF(authmode, aadhaarno);
					
					/*upload the pdf files */
					byte[] bytes = mpf.getBytes();
					Path path = Paths
							.get(UPLOADED_FOLDER + File.separator +"unsigned"+File.separator+ mpf.getOriginalFilename().replaceAll(".pdf", "").replaceAll(".PDF", "") + datetime + ".pdf");
					Files.write(path, bytes);
					
					/*read the file*/
					File inFile = new File(
							UPLOADED_FOLDER + File.separator +"unsigned"+File.separator+ mpf.getOriginalFilename().replaceAll(".pdf", "").replaceAll(".PDF", "") + datetime + ".pdf");
					
					/**set Session Value**/
					esignsessionbean.setRequest_time(request_time);
					esignsessionbean.setRequested_by(requested_by);
					if (authmode.equals("1"))
						esignsessionbean.setAuthmode("OTP");
					else if (authmode.equals("2"))
						esignsessionbean.setAuthmode("BIO");
					else
						esignsessionbean.setAuthmode("IRIS");
					
					/*Save session value*/
					EsignSession esession=new EsignSession();
					esession.setIpaddress(orgip);
					esession.setRequest_time(new Timestamp(dateFormat.parse(request_time).getTime()));
					esession.setRequested_by(requested_by);
					esession.setTransaction_id(txn);
					esession.setPdf_path(inFile.toString());
					esession.setEsign_name(name);
					esession.setEsign_location(location);
					esession.setEsign_reason(reason);
					esession.setDoc_id(1);
					esession.setDoc_timestamp(datetime);
					esession.setAuth_mode(Integer.parseInt(authmode));
					essesionserivce.save(esession);
					
					try {
						InputStream targetStream = new FileInputStream(inFile);
						requestXML = spdf.getResponseNSDL(targetStream, txn);
						
						if (!requestXML.isEmpty() && requestXML != null) {
							
							jesign.put("statuscode", "1");
							jesign.put("inputxml", requestXML);
							jesign.put("addharno", aadhaarno);
							return jesign.toString();

						} else {
						
							jesign.put("statuscode", "2");
							jesign.put("error", "Invalid input xml");
							return jesign.toString();
						}
						
					}catch(Exception ex){
						jesign.put("statuscode", "3");
						jesign.put("message", "There is some Technical issue please contact technical support Team");
						System.out.println(ex.getMessage());
						return jesign.toString();
					}
					
					
					
				}else {
					
					LOG.error("Invalid Pdf Files");
					jesign.put("statuscode", "4");
					return jesign.toString();
					
				}
				
				
			}
			return jesign.toString();
			
		}catch(Exception ex) {
			
			LOG.error("Exception Occure::"+ex.getMessage());
			JSONObject jesign = new JSONObject();
		    jesign.put("statuscode", "3");
			jesign.put("message", "There is some Technical issue please contact technical support Team");
			System.out.println(ex.getMessage());
			return jesign.toString();
			
		}
		
	
		
	}
}
