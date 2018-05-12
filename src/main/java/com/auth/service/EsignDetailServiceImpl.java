package com.auth.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.auth.model.EsignDetail;
import com.auth.repository.EsignDetailsRepository;

@Service
public class EsignDetailServiceImpl implements EsignDetailService{
	
	@Autowired
	EsignDetailsRepository esigndetailsrepo;

	@Override
	public List<EsignDetail> findByrequestby(String esign_request_by) {
		// TODO Auto-generated method stub
		return esigndetailsrepo.findByrequestby(esign_request_by);
	}

	@Override
	public void delete(EsignDetail esigndetail) {
		// TODO Auto-generated method stub
		esigndetailsrepo.delete(esigndetail);
	}

	@Override
	public EsignDetail findByid(int id) {
		return esigndetailsrepo.findByid(id);
	}

	@Override
	public List<EsignDetail> findByfilepathIgnoreCaseContaining(String filepath) {
		// TODO Auto-generated method stub
		return esigndetailsrepo.findByfilepathIgnoreCaseContaining(filepath);
	}

	@Override
	public EsignDetail save(EsignDetail esigndetail) {
		// TODO Auto-generated method stub
		return esigndetailsrepo.save(esigndetail);
	}

	
}
