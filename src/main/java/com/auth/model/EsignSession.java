package com.auth.model;




import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * The Class Esign_Session.
 */
@Entity
@Table(name = "ab_esigning_session")
public class EsignSession {

	
	/** The id. */
	@Id
	@SequenceGenerator(name = "seq_verification", sequenceName = "seq_verification")
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "seq_verification")
	private int id;
	
	
	/** The esign transaction id. */
	@Column(name="transaction_id")
	private String txn;
	
	/** The esign response url. */
	@Column
	private String response_url;
	
	/** The esign request_time. */
	@Column
	private Date request_time;
	
	/** The esign ipaddress. */
	@Column
	private String ipaddress;
	
	/** The esign requested_by. */
	@Column
	private String requested_by;
	
	/** The esign pdf_path. */
	@Column
	private String pdf_path;
	
	/** The esign name. */
	@Column(name="esign_name")
	private String esign;
	
	/** The esign locaion. */
	@Column
	private String esign_location;
	
	/** The esign reason. */
	@Column
	private String esign_reason;
	
	/** The doc id. */
	@Column
	private int doc_id;
	
	/** The doc timestamp. */
	@Column
	private String doc_timestamp;
	
	/** The authmdoe */
	@Column
	private int auth_mode;
	
	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTransaction_id() {
		return txn;
	}

	public void setTransaction_id(String transaction_id) {
		this.txn = transaction_id;
	}

	public String getResponse_url() {
		return response_url;
	}

	public void setResponse_url(String response_url) {
		this.response_url = response_url;
	}

	public Date getRequest_time() {
		return request_time;
	}

	public void setRequest_time(Date request_time) {
		this.request_time = request_time;
	}

	public String getIpaddress() {
		return ipaddress;
	}

	public void setIpaddress(String ipaddress) {
		this.ipaddress = ipaddress;
	}

	public String getRequested_by() {
		return requested_by;
	}

	public void setRequested_by(String requested_by) {
		this.requested_by = requested_by;
	}

	public String getPdf_path() {
		return pdf_path;
	}

	public void setPdf_path(String pdf_path) {
		this.pdf_path = pdf_path;
	}

	public String getEsign_name() {
		return esign;
	}

	public void setEsign_name(String esign_name) {
		this.esign = esign_name;
	}

	public String getEsign_location() {
		return esign_location;
	}

	public void setEsign_location(String esign_location) {
		this.esign_location = esign_location;
	}

	public String getEsign_reason() {
		return esign_reason;
	}

	public void setEsign_reason(String esign_reason) {
		this.esign_reason = esign_reason;
	}

	public int getDoc_id() {
		return doc_id;
	}

	public void setDoc_id(int doc_id) {
		this.doc_id = doc_id;
	}

	public String getDoc_timestamp() {
		return doc_timestamp;
	}

	public void setDoc_timestamp(String doc_timestamp) {
		this.doc_timestamp = doc_timestamp;
	}

	public int getAuth_mode() {
		return auth_mode;
	}

	public void setAuth_mode(int auth_mode) {
		this.auth_mode = auth_mode;
	}
	
	
	
	
}

