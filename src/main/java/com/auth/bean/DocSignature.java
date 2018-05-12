
package com.auth.bean;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlValue;

/*1.This class is used for Conversion of
 * i)POJO To XML
 * ii)XML To POJO
 *2.Here defining Xml Root Element for mapping purpose   
 *3@xmlAttribute map with class variables
 *4.Generating Setter and Getter method for Accessing Data 
 * 
 * */

@XmlRootElement(name = "DocSignatures")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DocSignatures")
public class DocSignature {
	@XmlAttribute(name = "id", required = true)
	protected String id;
	@XmlAttribute(name = "sigHashAlgorithm", required = true)
	protected String sigHashAlgorithm;
	@XmlAttribute(name = "error", required = true)
	protected String error;
	@XmlValue
	protected byte[] value;

	public byte[] getValue() {
		return value;
	}

	public void setValue(byte[] value) {
		this.value = value;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getSigHashAlgorithm() {
		return sigHashAlgorithm;
	}

	public void setSigHashAlgorithm(String sigHashAlgorithm) {
		this.sigHashAlgorithm = sigHashAlgorithm;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}
}
