package com.auth.util;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.UnmarshalException;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.stream.StreamResult;

/**
 * 
 * 
 * @author Naval.Makwana
 * @purpose: object to XML convert
 * @date: 14-8-2015
 */

/*
 * 1. This Class is used for Conversion of i)POJO To XML ii)XML To POJO
 * 2.Declaring the Class variables 3.Generating Setter and Getter Method
 * 
 */
public class XMLConverter {
	private Marshaller marshaller;
	private Unmarshaller unmarshaller;

	public Marshaller getMarshaller() {
		return marshaller;
	}

	public void setMarshaller(Marshaller marshaller) {
		this.marshaller = marshaller;
	}

	public Unmarshaller getUnmarshaller() {
		return unmarshaller;
	}

	public void setUnmarshaller(Unmarshaller unmarshaller) {
		this.unmarshaller = unmarshaller;
	}

	/**
	 * *
	 * 
	 * @author Naval.Makwana
	 * @purpose: Object to XML convert mapping
	 * @return XML string
	 * @date: 30-8-2016
	 */
	public String convertFromObjectToXML(Object object) throws IOException {
		JAXBContext jc = null;
		String xmlString = "";
		try {
			jc = JAXBContext.newInstance(object.getClass());
			setMarshaller(jc.createMarshaller());
			StringWriter sw = new StringWriter();
			getMarshaller().marshal(object, new StreamResult(sw));
			xmlString = sw.toString();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return xmlString;
	}

	/**
	 * *
	 * 
	 * @author Naval.Makwana
	 * @purpose: XML to Object convert mapping
	 * @return XML string
	 * @date: 01-09-2016
	 */
	public Object convertFromXMLToObject(Object object, String xmlString) throws IOException {
		JAXBContext jc = null;

		try {
			jc = JAXBContext.newInstance(object.getClass());
			setUnmarshaller(jc.createUnmarshaller());
			System.out.println("xmlString==" + xmlString);
			StringWriter sw = new StringWriter();
			sw.write(xmlString);
			byte[] barray = sw.toString().getBytes("UTF-8");
			InputStream Is = new ByteArrayInputStream(barray);
			object = unmarshaller.unmarshal(Is);
			return object;

		} catch (UnmarshalException e) {

			System.out.println("There" + e.getMessage());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return xmlString;
	}
}
