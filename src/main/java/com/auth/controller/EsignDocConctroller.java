package com.auth.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import org.codehaus.jettison.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.auth.bean.EsignSessionBean;
import com.auth.bean.Tag;
import com.auth.model.EsignDetail;
import com.auth.service.EsignDetailService;
import com.google.gson.Gson;

@Controller
public class EsignDocConctroller {

	private static final int BUFFER_SIZE = 4096;
	
	@Autowired
	EsignDetailService esigndetailservice;
	
	@Autowired
	EsignSessionBean esignsessionbean;
	
	
	/**
	 * mathod for esign display Form
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/document", method = RequestMethod.GET)
	public String Esigndocument(Model model,@RequestParam String searchdocument) throws Exception {
		
		
		
	try {	  
		
	  ArrayList<HashMap<String, Object>> data = new ArrayList<HashMap<String, Object>>();
	 if(searchdocument.isEmpty()) {	
		
		List<EsignDetail> esigndetails=esigndetailservice.findByrequestby(esignsessionbean.getRequested_by());
		for(EsignDetail esigndetail:esigndetails) {
			
			HashMap<String, Object> map1 = new HashMap<String, Object>();
			File f = new File(esigndetail.getEsign_file_path());
			double bytes = f.length();
			
			Integer kilobytes = (int) (bytes / 1024);
			map1.put("id", esigndetail.getId());
			map1.put("name", f.getName().replaceAll("_signedFinal", ""));
			map1.put("docs", esigndetail.getEsign_file_path());
			map1.put("fsize", kilobytes);
			map1.put("date", esigndetail.getEsign_response_on());
			data.add(map1);
		}
		model.addAttribute("employee", data);
	 }else {
		 List<EsignDetail> esigndetails=esigndetailservice.findByfilepathIgnoreCaseContaining(searchdocument);
		 for(EsignDetail esigndetail:esigndetails) {
				
				HashMap<String, Object> map1 = new HashMap<String, Object>();
				File f = new File(esigndetail.getEsign_file_path());
				double bytes = f.length();
				
				Integer kilobytes = (int) (bytes / 1024);
				map1.put("id", esigndetail.getId());
				map1.put("name", f.getName().replaceAll("_signedFinal", ""));
				map1.put("docs", esigndetail.getEsign_file_path());
				map1.put("fsize", kilobytes);
				map1.put("date", esigndetail.getEsign_response_on());
				data.add(map1);
			}
			model.addAttribute("employee", data);
	 }		
		return "document";
		
		
	}catch(Exception ex) {
		
		return "esign";
	}	
	}
	
	
	/**
	 * mathod for download the singed files
	 * @param request
	 * @param session
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = { "/download", "/download" }, method = RequestMethod.GET)
	public void doDownload(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		String fpath = request.getParameter("dfpath");
		
		ServletContext context = request.getServletContext();
		File f = new File(fpath);
		File downloadFile = new File(fpath);
		FileInputStream inputStream = new FileInputStream(downloadFile);
		String mimeType = context.getMimeType(fpath);
		if (mimeType == null) { // This File Type used MIME
			// set to binary type if MIME mapping not found
			mimeType = "application/octet-stream";
		}
		response.setContentType(mimeType);
		response.setContentLength((int) downloadFile.length());
		String headerKey = "Content-Disposition";
		String headerValue = String.format("attachment; filename=\"%s\"",
				f.getName().replaceAll(".pdf", "") + ".pdf");
		response.setHeader(headerKey, headerValue);
		response.setHeader("X-Download-Options", "noopen");
		OutputStream outStream = response.getOutputStream();
		byte[] buffer = new byte[BUFFER_SIZE];
		int bytesRead = -1;
		// write bytes read from the input stream into the output stream
		while ((bytesRead = inputStream.read(buffer)) != -1) {
			outStream.write(buffer, 0, bytesRead);
		}
		inputStream.close();
		outStream.close();
	
		
	}
	
	/**
	 * mathod for delete file
	 * @param request
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/esignDelete", method = RequestMethod.POST)
	public @ResponseBody String EsignDelete(HttpServletRequest request) throws Exception {
		
		
		int delete_id = Integer.parseInt(request.getParameter("esignseid"));
		
		JSONObject jo = new JSONObject();
		try {
			EsignDetail esingdetail=esigndetailservice.findByid(delete_id);
			esigndetailservice.delete(esingdetail);
			jo.put("status", "1");

		} catch (Exception e) {
			jo.put("status", "0");

		}
		return jo.toString();
	}

	@RequestMapping(value = "/searchDoc", method = RequestMethod.GET)
	@ResponseBody
	public String getTags(@RequestParam String tagName) {
		Gson gson = new Gson();
		List<EsignDetail> usertag=esigndetailservice.findByfilepathIgnoreCaseContaining(tagName);
		
		
		List<Tag>tag=new ArrayList<Tag>();
		int count=0;
		for(EsignDetail authuser:usertag){
		    Tag tag1=new Tag(); 
		    tag1.setId(++count);
		    int index = authuser.getEsign_file_path().lastIndexOf(File.separator);
		    tag1.setTagName(authuser.getEsign_file_path().substring(index + 1));
		    
		    tag.add(tag1);
			
		}
		
		return gson.toJson(tag);

	}

}
