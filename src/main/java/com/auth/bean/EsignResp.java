package com.auth.bean;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;



/*<EsignResp status="" ts="" txn="" resCode="" errCode="" errMsg="">
<UserX509Certificate>base64 value of end user certificate (.cer)</UserX509Certificate>
<Signatures>
<DocSignature id="" sigHashAlgorithm="SHA256" error=""> Signature data in raw (PKCS#1) or PKCS7 (CMS)
signature as requested
</DocSignature>
<DocSignature id="" sigHashAlgorithm="SHA256"
error=""> Signature data in raw (PKCS#1) or PKCS7
(CMS)
signature as requested
</DocSignature>
</Signatures>
<AadhaarResp>base-64 encoded authentication response which is contained within the eKYC response of UIDAI</AadhaarResp>
<Signature>Signature of ESP</Signature>
</EsignResp>
*/

@XmlRootElement(name = "EsignResp")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {"userX509Certificate", "signatures","aadhaarResp","signature" })
public class EsignResp {
	@XmlElement(name = "UserX509Certificate", required = true)
	protected String userX509Certificate;
	@XmlElement(name = "Signatures", required = true)
	protected Signatures signatures;
	@XmlElement(name = "AadhaarResp", required = true)
	protected String aadhaarResp;
	@XmlElement(name = "Signature", required = true)
	protected String signature;
	@XmlAttribute(name = "status", required = true)
	protected String status;
	@XmlAttribute(name = "ts", required = true)
	protected String ts;
	@XmlAttribute(name = "txn", required = true)
	protected String txn;
	@XmlAttribute(name = "resCode", required = true)
	protected String resCode;
	@XmlAttribute(name = "errCode", required = true)
	protected String errCode;
	@XmlAttribute(name = "errMsg", required = true)
	protected String errMsg;
	public String getUserX509Certificate() {
		return userX509Certificate;
	}
	public void setUserX509Certificate(String userX509Certificate) {
		this.userX509Certificate = userX509Certificate;
	}
	
	public String getAadhaarResp() {
		return aadhaarResp;
	}
	public void setAadhaarResp(String aadhaarResp) {
		this.aadhaarResp = aadhaarResp;
	}
	public String getSignature() {
		return signature;
	}
	public void setSignature(String signature) {
		this.signature = signature;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getTs() {
		return ts;
	}
	public void setTs(String ts) {
		this.ts = ts;
	}
	public String getTxn() {
		return txn;
	}
	public void setTxn(String txn) {
		this.txn = txn;
	}
	public String getResCode() {
		return resCode;
	}
	public void setResCode(String resCode) {
		this.resCode = resCode;
	}
	public String getErrCode() {
		return errCode;
	}
	public void setErrCode(String errCode) {
		this.errCode = errCode;
	}
	public String getErrMsg() {
		return errMsg;
	}
	public void setErrMsg(String errMsg) {
		this.errMsg = errMsg;
	}
	public Signatures getSignatures() {
		return signatures;
	}
	public void setSignatures(Signatures signatures) {
		this.signatures = signatures;
	}
	
	
	
	
}
