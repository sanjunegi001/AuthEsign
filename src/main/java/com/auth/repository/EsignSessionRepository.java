package com.auth.repository;

import java.io.Serializable;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.auth.model.EsignSession;


@Repository
public interface EsignSessionRepository extends CrudRepository<EsignSession, Serializable>{

	public EsignSession save(EsignSession esingsession);
	
	public EsignSession findBytxn(String txn);
}
