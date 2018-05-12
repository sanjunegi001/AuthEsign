package com.auth.repository;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.auth.model.EsignDetail;
import com.auth.model.EsignSession;

@Repository
public interface EsignDetailsRepository extends CrudRepository<EsignDetail, Long>{

	List<EsignDetail>  findByrequestby(String esign_request_by);
	
	EsignDetail findByid(int id); 
	
	List<EsignDetail> findByfilepathIgnoreCaseContaining(@Param("esign_file_path") String filepath);

	EsignDetail save(EsignDetail esigndetail);
	void delete(EsignDetail esigndetail);
}
