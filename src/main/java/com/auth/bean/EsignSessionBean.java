package com.auth.bean;

import java.io.Serializable;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.context.annotation.ScopedProxyMode;

@Component
@Scope(value="globalSession", proxyMode =ScopedProxyMode.TARGET_CLASS)
public class EsignSessionBean implements Serializable{

	public String request_time;
	
	public String authmode;
	
	public String requested_by;

	public String getRequest_time() {
		return request_time;
	}

	public void setRequest_time(String request_time) {
		this.request_time = request_time;
	}

	public String getAuthmode() {
		return authmode;
	}

	public void setAuthmode(String authmode) {
		this.authmode = authmode;
	}

	public String getRequested_by() {
		return requested_by;
	}

	public void setRequested_by(String requested_by) {
		this.requested_by = requested_by;
	}
	
	
	
}
