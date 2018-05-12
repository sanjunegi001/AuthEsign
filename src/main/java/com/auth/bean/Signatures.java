package com.auth.bean;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/*1.This class is used for Conversion of
 * i)POJO To XML
 * ii)XML To POJO
 *2.XML Element Map with class variables.
 *3.Generating Setter and getter method.
 * */

@XmlRootElement(name = "Signatures")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Signatures", propOrder = {"docSignature" })
public class Signatures {
	@XmlElement(name = "DocSignature", required = true)
	protected DocSignature[] docSignature;

	public DocSignature[] getDocSignature() {
		return docSignature;
	}

	public void setDocSignature(DocSignature[] docSignature) {
		this.docSignature = docSignature;
	}
	
}
