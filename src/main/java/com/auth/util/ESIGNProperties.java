package com.auth.util;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public class ESIGNProperties {

	/** The log path. */
	private static String logPath;

	private static String pdfServerPath;
	private static String aspId;
	private static String pwd;
	private static String alias;
	private static String ver;
	private static String uidaiCertificate;
	private static String p12Certificate;
	private static String ACforKYC;
	private static String SAforKYC;
	private static String AC;
	private static String SA;
	private static String K;
	private static String AuthXmlns;
	private static String VER;
	private static String UDC;
	private static String PIP;
	private static String FDC;
	private static String IDC;
	private static String LOV;
	private static String CI;
	private static String eSignURL;
	private static String eSignOTP;
	private static String uploadpath;
	private static String proxyIP;
	private static String proxyHost;
	private static String proxyPort;
	private static String proxyPortNumber;
	private static String responseURL;
	private static String tickimagepath;
	
	

	
	public static String getResponseURL() {
		return responseURL;
	}

	public static void setResponseURL(String responseURL) {
		ESIGNProperties.responseURL = responseURL;
	}

	public static String getLogPath() {
		return logPath;
	}

	public static void setLogPath(String logPath) {
		ESIGNProperties.logPath = logPath;
	}

	public static String getPdfServerPath() {
		return pdfServerPath;
	}

	public static void setPdfServerPath(String pdfServerPath) {
		ESIGNProperties.pdfServerPath = pdfServerPath;
	}

	public static String getAspId() {
		return aspId;
	}

	public static void setAspId(String aspId) {
		ESIGNProperties.aspId = aspId;
	}

	public static String getPwd() {
		return pwd;
	}

	public static void setPwd(String pwd) {
		ESIGNProperties.pwd = pwd;
	}

	public static String getAlias() {
		return alias;
	}

	public static void setAlias(String alias) {
		ESIGNProperties.alias = alias;
	}

	public static String getVer() {
		return ver;
	}

	public static void setVer(String ver) {
		ESIGNProperties.ver = ver;
	}

	public static String getUidaiCertificate() {
		return uidaiCertificate;
	}

	public static void setUidaiCertificate(String uidaiCertificate) {
		ESIGNProperties.uidaiCertificate = uidaiCertificate;
	}

	public static String getP12Certificate() {
		return p12Certificate;
	}

	public static void setP12Certificate(String p12Certificate) {
		ESIGNProperties.p12Certificate = p12Certificate;
	}

	public static String getACforKYC() {
		return ACforKYC;
	}

	public static void setACforKYC(String aCforKYC) {
		ESIGNProperties.ACforKYC = aCforKYC;
	}

	public static String getSAforKYC() {
		return SAforKYC;
	}

	public static void setSAforKYC(String sAforKYC) {
		ESIGNProperties.SAforKYC = sAforKYC;
	}

	public static String getAC() {
		return AC;
	}

	public static void setAC(String aC) {
		ESIGNProperties.AC = aC;
	}

	public static String getSA() {
		return SA;
	}

	public static void setSA(String sA) {
		ESIGNProperties.SA = sA;
	}

	public static String getK() {
		return K;
	}

	public static void setK(String k) {
		ESIGNProperties.K = k;
	}

	public static String getAuthXmlns() {
		return AuthXmlns;
	}

	public static void setAuthXmlns(String authXmlns) {
		ESIGNProperties.AuthXmlns = authXmlns;
	}

	public static String getVER() {
		return VER;
	}

	public static void setVER(String vER) {
		ESIGNProperties.VER = vER;
	}

	public static String getUDC() {
		return UDC;
	}

	public static void setUDC(String uDC) {
		ESIGNProperties.UDC = uDC;
	}

	public static String getPIP() {
		return PIP;
	}

	public static void setPIP(String pIP) {
		ESIGNProperties.PIP = pIP;
	}

	public static String getFDC() {
		return FDC;
	}

	public static void setFDC(String fDC) {
		ESIGNProperties.FDC = fDC;
	}

	public static String getIDC() {
		return IDC;
	}

	public static void setIDC(String iDC) {
		ESIGNProperties.IDC = iDC;
	}

	public static String getLOV() {
		return LOV;
	}

	public static void setLOV(String lOV) {
		ESIGNProperties.LOV = lOV;
	}

	public static String getCI() {
		return CI;
	}

	public static void setCI(String cI) {
		ESIGNProperties.CI = cI;
	}

	public static String geteSignURL() {
		return eSignURL;
	}

	public static void seteSignURL(String eSignURL) {
		ESIGNProperties.eSignURL = eSignURL;
	}

	public static String geteSignOTP() {
		return eSignOTP;
	}

	public static void seteSignOTP(String eSignOTP) {
		ESIGNProperties.eSignOTP = eSignOTP;
	}

	/**
	 * Load.
	 *
	 * @param str
	 *            the str
	 */
	public static void load() {
		Properties properties = new Properties();
		try {

			try {

				ClassLoader classloader = Thread.currentThread().getContextClassLoader();
				properties
						.load(new FileInputStream(new File(classloader.getResource("application.properties").getFile())));

			} catch (Exception e) {
				System.out.println(e);
			}
			
			if (properties.getProperty("responseURL") != null) {
				responseURL = properties.getProperty("responseURL").toString();
			}
			
			if (properties.getProperty("logPath") != null) {
				logPath = properties.getProperty("logPath").toString();
			}
			if (properties.getProperty("pdfServerPath") != null) {
				pdfServerPath = properties.getProperty("pdfServerPath").toString();
			}
			if (properties.getProperty("aspId") != null) {
				aspId = properties.getProperty("aspId").toString();
			}

			if (properties.getProperty("pwd") != null) {
				pwd = properties.getProperty("pwd").toString();
			}

			if (properties.getProperty("alias") != null) {
				alias = properties.getProperty("alias").toString();
			}

			if (properties.getProperty("ver") != null) {
				ver = properties.getProperty("ver").toString();
			}

			if (properties.getProperty("uidaiCertificate") != null) {
				uidaiCertificate = properties.getProperty("uidaiCertificate").toString();
			}

			if (properties.getProperty("p12Certificate") != null) {
				p12Certificate = properties.getProperty("p12Certificate").toString();
			}

			if (properties.getProperty("ACforKYC") != null) {
				ACforKYC = properties.getProperty("ACforKYC").toString();
			}
			if (properties.getProperty("SAforKYC") != null) {
				SAforKYC = properties.getProperty("SAforKYC").toString();
			}

			if (properties.getProperty("AC") != null) {
				AC = properties.getProperty("AC").toString();
			}

			if (properties.getProperty("SA") != null) {
				SA = properties.getProperty("SA").toString();
			}

			if (properties.getProperty("K") != null) {
				K = properties.getProperty("K").toString();
			}

			if (properties.getProperty("AuthXmlns") != null) {
				AuthXmlns = properties.getProperty("AuthXmlns").toString();
			}

			if (properties.getProperty("VER") != null) {
				VER = properties.getProperty("VER").toString();
			}

			if (properties.getProperty("UDC") != null) {
				UDC = properties.getProperty("UDC").toString();
			}

			if (properties.getProperty("PIP") != null) {
				PIP = properties.getProperty("PIP").toString();
			}

			if (properties.getProperty("FDC") != null) {
				FDC = properties.getProperty("FDC").toString();
			}

			if (properties.getProperty("IDC") != null) {
				IDC = properties.getProperty("IDC").toString();
			}

			if (properties.getProperty("LOV") != null) {
				LOV = properties.getProperty("LOV").toString();
			}

			if (properties.getProperty("CI") != null) {
				CI = properties.getProperty("CI").toString();
			}

			if (properties.getProperty("eSignURL") != null) {
				eSignURL = properties.getProperty("eSignURL").toString();

			}

			if (properties.getProperty("eSignOTP") != null) {
				eSignOTP = properties.getProperty("eSignOTP").toString();
			}
			if (properties.getProperty("uploadpath") != null) {
				uploadpath = properties.getProperty("uploadpath").toString();
			}
			if (properties.getProperty("tickimagepath") != null) {
				tickimagepath = properties.getProperty("tickimagepath").toString();
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();

		}
	}

	

	public static String getUploadpath() {
		return uploadpath;
	}

	public static void setUploadpath(String uploadpath) {
		ESIGNProperties.uploadpath = uploadpath;
	}

	public static String getProxyIP() {
		return proxyIP;
	}

	public static void setProxyIP(String proxyIP) {
		ESIGNProperties.proxyIP = proxyIP;
	}

	public static String getProxyHost() {
		return proxyHost;
	}

	public static void setProxyHost(String proxyHost) {
		ESIGNProperties.proxyHost = proxyHost;
	}

	public static String getProxyPort() {
		return proxyPort;
	}

	public static void setProxyPort(String proxyPort) {
		ESIGNProperties.proxyPort = proxyPort;
	}

	public static String getProxyPortNumber() {
		return proxyPortNumber;
	}

	public static void setProxyPortNumber(String proxyPortNumber) {
		ESIGNProperties.proxyPortNumber = proxyPortNumber;
	}

	public static String getTickimagepath() {
		return tickimagepath;
	}

	public static void setTickimagepath(String tickimagepath) {
		ESIGNProperties.tickimagepath = tickimagepath;
	}

	
	
}
