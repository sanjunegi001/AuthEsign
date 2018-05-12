/*
 * Created on Nov 1, 2013
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.auth.util;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.ConnectException;
import java.net.URL;
import java.net.URLEncoder;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.KeyManager;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

/* 1.This class is used for connectioning Server  with the help of URL*/
public class ConnectHttps {

	public static class DummyTrustManager implements X509TrustManager {

		public DummyTrustManager() {
		}

		public boolean isClientTrusted(X509Certificate cert[]) {
			return true;
		}

		public boolean isServerTrusted(X509Certificate cert[]) {
			return true;
		}

		public X509Certificate[] getAcceptedIssuers() {
			return new X509Certificate[0];
		}

		public void checkClientTrusted(X509Certificate[] arg0, String arg1) throws CertificateException {

		}

		public void checkServerTrusted(X509Certificate[] arg0, String arg1) throws CertificateException {

		}
	}

	public static class DummyHostnameVerifier implements HostnameVerifier {

		public boolean verify(String urlHostname, String certHostname) {
			return true;
		}

		public boolean verify(String arg0, SSLSession arg1) {
			return true;
		}
	}

	public static String getResponseFromHttps(String asaUrl, String xml) {
		
	
		SSLContext sslcontext = null;
		String responseXML = "";
		try {
			sslcontext = SSLContext.getInstance("TLSv1.2");

			sslcontext.init(new KeyManager[0], new TrustManager[] { new DummyTrustManager() }, new SecureRandom());
		} catch (NoSuchAlgorithmException e) {

			System.out.println("Exception in ConnectHttps :" + e);

		} catch (KeyManagementException e) {
			System.out.println("Exception in ConnectHttps :" + e);
		}

		String urlParameters = "msg=";
		try {
			// urlParameters = urlParameters + URLEncoder.encode(xml, "UTF-8");

			urlParameters = xml;
			System.out.println("urlParameters"+urlParameters);

		} catch (Exception e) {

			System.out.println("Exception in ConnectHttps :" + e);

		}
		/*
		 * if(urlParameters.contains("+")) urlParameters =
		 * urlParameters.replaceAll("+", "%2b");
		 */

		try {

			SSLSocketFactory factory = sslcontext.getSocketFactory();
			URL url;
			HttpsURLConnection connection;
			InputStream is = null;

			String ip = asaUrl;
			System.out.println("ip" + ip);

			url = new URL(null, ip, new sun.net.www.protocol.https.Handler());
			connection = (HttpsURLConnection) url.openConnection();
			connection.setRequestMethod("POST");
			connection.setRequestProperty("Content-Type", "application/xml");
			connection.setRequestProperty("Content-Length", "" + Integer.toString(urlParameters.getBytes().length));
			connection.setRequestProperty("Content-Language", "en-US");
			connection.setUseCaches(false);
			connection.setDoInput(true);
			connection.setDoOutput(true);
			connection.setSSLSocketFactory(factory);
			connection.setHostnameVerifier(new DummyHostnameVerifier());
			OutputStream os = connection.getOutputStream();
			OutputStreamWriter osw = new OutputStreamWriter(os);
			osw.write(urlParameters);
			osw.flush();
			osw.close();

			is = connection.getInputStream();
			BufferedReader in = new BufferedReader(new InputStreamReader(is));

			String strTemp;
			while ((strTemp = in.readLine()) != null) {
				responseXML = responseXML + strTemp;

			}

			System.out.println("Output: " + in.readLine());

			is.close();
			in.close();
			connection.disconnect();
		} catch (ConnectException e) {
			System.out.println("Exception in ConnectHttps :" + e);
		} catch (Exception e) {

			System.out.println("Exception in ConnectHttps :" + e);
		}
		System.out.println("In getResponseFromHttps method of ConnectHttps");
		return responseXML;
	}

	public static String getResponseFromHttps(String httpsUrl, String postParName, String postParValue) {
		System.out.println("getResponseFromHttps kua");
		System.out.println("In getResponseFromHttps method of ConnectHttps");
		SSLContext sslcontext = null;
		String responseXML = "";
		try {
			sslcontext = SSLContext.getInstance("SSL");

			sslcontext.init(new KeyManager[0], new TrustManager[] { new DummyTrustManager() }, new SecureRandom());
		} catch (NoSuchAlgorithmException e) {

			System.out.println("Exception in ConnectHttps :" + e);

		} catch (KeyManagementException e) {
			System.out.println("Exception in ConnectHttps :" + e);
		}

		String urlParameters = postParName + "=";
		try {
			urlParameters = urlParameters + URLEncoder.encode(postParValue, "UTF-8");
		} catch (Exception e) {

			System.out.println("Exception in ConnectHttps :" + e);

		}
		try {
			SSLSocketFactory factory = sslcontext.getSocketFactory();
			URL url;
			HttpsURLConnection connection;
			InputStream is = null;

			String ip = httpsUrl;
			url = new URL(ip);
			System.out.println("URL " + ip);
			connection = (HttpsURLConnection) url.openConnection();
			connection.setRequestMethod("POST");
			connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
			connection.setRequestProperty("Content-Length", "" + Integer.toString(urlParameters.getBytes().length));
			connection.setRequestProperty("Content-Language", "en-US");
			connection.setUseCaches(false);
			connection.setDoInput(true);
			connection.setDoOutput(true);
			connection.setSSLSocketFactory(factory);
			connection.setHostnameVerifier(new DummyHostnameVerifier());
			OutputStream os = connection.getOutputStream();
			OutputStreamWriter osw = new OutputStreamWriter(os);
			osw.write(urlParameters);
			osw.flush();
			osw.close();
			is = connection.getInputStream();
			BufferedReader in = new BufferedReader(new InputStreamReader(is));

			String strTemp;
			while ((strTemp = in.readLine()) != null) {
				responseXML = strTemp;
			}
			is.close();
			in.close();
		} catch (ConnectException e) {
			System.out.println("Exception in ConnectHttps for license key check :" + e);
			responseXML = "Unable to connect to server";
			e.printStackTrace();
		} catch (Exception e) {
			responseXML = "Exception Occured";
			System.out.println("Exception in ConnectHttps for  license key check  :" + e);
			e.printStackTrace();
		}
		System.out.println("In getResponseFromHttps method of ConnectHttps for license key check  ");
		return responseXML;
	}

	public static String getResponseFromEsing(String asaUrl, String xml) {
		System.out.println("nn2");
		SSLContext sslcontext = null;
		String responseXML = "";
		try {
			sslcontext = SSLContext.getInstance("TLSv1.2");

			sslcontext.init(new KeyManager[0], new TrustManager[] { new DummyTrustManager() }, new SecureRandom());
		} catch (NoSuchAlgorithmException e) {

		} catch (KeyManagementException e) {

		}

		String urlParameters = "msg=";
		try {
			
		    urlParameters =urlParameters + URLEncoder.encode(xml, "UTF-8");
			urlParameters = urlParameters+"&obj=634892409985";
			
			//System.out.println("urlParameters::"+urlParameters);
		} catch (Exception e) {

			System.out.println("Exception in ConnectHttps :" + e);

		}
		/*
		 * if(urlParameters.contains("+")) urlParameters =
		 * urlParameters.replaceAll("+", "%2b");
		 */

		try {
			SSLSocketFactory factory = sslcontext.getSocketFactory();
			URL url;
			HttpsURLConnection connection;
			InputStream is = null;

			String ip = asaUrl.trim();
			url = new URL(null, ip, new sun.net.www.protocol.https.Handler());
			
			System.out.println("URL " + ip);
			System.out.println("urlParameters::"+urlParameters);
			connection = (HttpsURLConnection) url.openConnection();
			connection.setRequestMethod("POST");
			connection.setRequestProperty("Content-Type","application/x-www-form-urlencoded");
			connection.setRequestProperty("Content-Length", "" + Integer.toString(urlParameters.getBytes().length));
			connection.setRequestProperty("Content-Language", "en-US");
			connection.setUseCaches(false);
			connection.setDoInput(true);
			connection.setDoOutput(true);
			connection.setSSLSocketFactory(factory);
			connection.setHostnameVerifier(new DummyHostnameVerifier());
			OutputStream os = connection.getOutputStream();
			OutputStreamWriter osw = new OutputStreamWriter(os);
			osw.write(urlParameters);
			osw.flush();
			osw.close();

			is = connection.getInputStream();
			BufferedReader in = new BufferedReader(new InputStreamReader(is));

			String strTemp;
			while ((strTemp = in.readLine()) != null) {
				responseXML = responseXML + strTemp;
			}
			
			is.close();
			in.close();
			connection.disconnect();
		} catch (ConnectException e) {
			System.out.println("Exception in ConnectHttps :" + e);
		} catch (Exception e) {

			System.out.println("Exception in ConnectHttps :" + e);
		}
		System.out.println("negggggg:"+responseXML);
		System.out.println("In getResponseFromHttps method of ConnectHttps");
		return responseXML;
	}
}
