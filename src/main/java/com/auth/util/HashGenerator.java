package com.auth.util;
/*******************************************************************************
 * DISCLAIMER: The sample code or utility or tool described herein
 *    is provided on an "as is" basis, without warranty of any kind.
 *    UIDAI does not warrant or guarantee the individual success
 *    developers may have in implementing the sample code on their
 *    environment. 
 *    
 *    UIDAI does not warrant, guarantee or make any representations
 *    of any kind with respect to the sample code and does not make
 *    any representations or warranties regarding the use, results
 *    of use, accuracy, timeliness or completeness of any data or
 *    information relating to the sample code. UIDAI disclaims all
 *    warranties, express or implied, and in particular, disclaims
 *    all warranties of merchantability, fitness for a particular
 *    purpose, and warranties related to the code, or any service
 *    or software related thereto. 
 *    
 *    UIDAI is not responsible for and shall not be liable directly
 *    or indirectly for any direct, indirect damages or costs of any
 *    type arising out of use or any action taken by you or others
 *    related to the sample code.
 *    
 *    THIS IS NOT A SUPPORTED SOFTWARE.
 ******************************************************************************/
//package com.paam.fullForm.authXml;

import java.io.FileInputStream;
import java.security.MessageDigest;
import java.security.Security;

import org.bouncycastle.jce.provider.BouncyCastleProvider;
/**
 * A utility class to create SHA-256 hashes
 * 
 * @author UIDAI
 *
 */

/* This Class is used for Generating Hash*/
public class HashGenerator {

	static {
		Security.addProvider(new BouncyCastleProvider());
	}
	
	public static byte[] generateSha256Hash(byte[] message) {
		String algorithm = "SHA-256";
		String SECURITY_PROVIDER = "BC";

		byte[] hash = null;

		MessageDigest digest;
		try {
			digest = MessageDigest.getInstance(algorithm, SECURITY_PROVIDER);
			digest.reset();
			hash = digest.digest(message);
			
		System.out.println(hash);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return hash;
	}

	
	
	/*public static String genaratePDFHash(String pdfFilePath) throws IOException,NullPointerException, NoSuchAlgorithmException {
		String hexFormat = null;
		MessageDigest md = MessageDigest.getInstance("SHA-256");
		// String pdfFilePath = "F:/JARS/itext-5.1.0/texttpPDF.pdf";

		FileInputStream fis = new FileInputStream(pdfFilePath);
		byte[] dataBytes = new byte[1024];
		int nread = 0;
		while ((nread = fis.read(dataBytes)) != -1) {
					md.update(dataBytes, 0, nread);
			}
		byte[] mdbytes = md.digest();
		StringBuilder hexString = new StringBuilder();
		for (int i = 0; i < mdbytes.length; i++) {
		    hexString.append(Integer.toHexString(0xFF & mdbytes[i]));
		}
		hexFormat = hexString.toString();
		System.out.println("Hex Format : " + hexFormat);
		fis.close();
		
		return hexFormat;
}*/
	
	public static String genaratePDFHash(String fileName) throws Exception {
		MessageDigest md = MessageDigest.getInstance("SHA-256");
		FileInputStream fis = new FileInputStream(fileName);

		byte[] dataBytes = new byte[1024];

		int nread = 0;
		while ((nread = fis.read(dataBytes)) != -1) {
			md.update(dataBytes, 0, nread);
		}

		fis.close();

		byte[] mdbytes = md.digest();

		StringBuffer hexString = new StringBuffer();

		for (int i = 0; i < mdbytes.length; i++) {
			hexString.append(Integer.toString((mdbytes[i] & 0xFF) + 256, 16)
					.substring(1));
		}


		return hexString.toString();
	}
	
	/*public static void main(String [] args) throws NullPointerException{
        //GenaratePidHash.genarate("4567");
        String otp="4565";
        String pidXml = "<Pid ts=\"2016-08-11T19:32:30.45\" ver=\"1.6\"> <Pv otp=\""
                 + otp + "\" pin=\"y\"/></Pid>";
        generateSha256Hash(pidXml.getBytes());
   }*/

}
