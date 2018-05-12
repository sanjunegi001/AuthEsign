package com.auth.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.auth.model.EsignSession;
import com.auth.repository.EsignSessionRepository;

@Service
public class EsignSessionServiceImpl implements EsignSessionService{

	@Autowired
	EsignSessionRepository esession; 
	
	
	
	@Override
	public EsignSession save(EsignSession esignsession) {
		// TODO Auto-generated method stub
		return esession.save(esignsession);
	}



	@Override
	public EsignSession findBytxn(String txn) {
		// TODO Auto-generated method stub
		return esession.findBytxn(txn);
	}

}
