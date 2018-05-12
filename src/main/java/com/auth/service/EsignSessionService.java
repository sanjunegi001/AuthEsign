package com.auth.service;

import com.auth.model.EsignSession;

public interface EsignSessionService {

	public EsignSession save(EsignSession esignsession);
	
	public EsignSession findBytxn(String txn);
}
