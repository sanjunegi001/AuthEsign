package com.auth.service;

import java.util.List;

import org.springframework.data.repository.query.Param;

import com.auth.model.EsignDetail;

public interface EsignDetailService {

	List<EsignDetail>  findByrequestby(String esign_request_by);
	
	EsignDetail findByid(int id); 
	
	void delete(EsignDetail esigndetail);
	
	EsignDetail save(EsignDetail esigndetail);
	
	List<EsignDetail> findByfilepathIgnoreCaseContaining(String filepath);
}
