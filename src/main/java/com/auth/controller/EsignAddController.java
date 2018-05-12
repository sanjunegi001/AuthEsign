package com.auth.controller;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.auth.model.User;
import com.auth.repository.AuthUserRepository;
import com.auth.service.UserService;

@Controller
public class EsignAddController {

	@Autowired
	UserService userservice;
	
	@Autowired
	AuthUserRepository authrepository;
	
	@RequestMapping({"/authclient"})
	public String addclient() {
		
		return "authuser";
	}
	
	@RequestMapping(value = "/saveAuthuser", method = {RequestMethod.POST, RequestMethod.GET})
	public ModelAndView saveUser(@ModelAttribute("authuser") User authuser, Model model) {
		
		String dataadded = "",pass="";
		 String role="";
		    DateFormat dateFormatt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date reqdatee = new Date();
			dataadded = dateFormatt.format(reqdatee);
			
			
			try {
			
			if (authuser.getRole().equals("1"))
				role="ROLE_ADMIN";
			else
				role="ROLE_USER";
			if (userservice.findOneByUserName(authuser.getUserName())) {
				return new ModelAndView("authuser", "authuser",
						" This authuser-login :" + authuser.getUserName() + " is already exist");

			} else {
					
				
			User authusermodel=new User();
			authusermodel.setActive_status(1);
			authusermodel.setCreatedOn(new Timestamp(dateFormatt.parse(dataadded).getTime()));
			authusermodel.setPassword(authuser.getPassword());
			authusermodel.setRole(role);
			authusermodel.setUserfname(authuser.getUserfname());
			authusermodel.setUserlname(authuser.getUserlname());
			authusermodel.setUserName(authuser.getUserName());
			authusermodel.setModifiedOn(new Timestamp(dateFormatt.parse(dataadded).getTime()));
			authrepository.save(authusermodel);
			
			return new ModelAndView("authuser", "authuser", authuser.getUserName()+ " successfully added.");
		}
	  }catch(Exception ex) {
		  return new ModelAndView("redirect:/login");
		  
	  }		
	}
	
}
