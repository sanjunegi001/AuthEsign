/*
 * 
 */
package com.auth.util;

import java.util.Random;

// TODO: Auto-generated Javadoc
/**
 * The Class Util.
 */
public class Util {

	/**
	 * Generate captcha text method 1.
	 *
	 * @return the string
	 */
	public static String generateCaptchaTextMethod1() {

		Random rdm = new Random();
		int rl = rdm.nextInt(); // Random numbers are generated.
		String hash1 = Integer.toHexString(rl); // Random numbers are converted
												// to Hexa Decimal.

		return hash1;

	}

	/**
	 * Generate captcha text method 2.
	 *
	 * @param captchaLength
	 *            the captcha length
	 * @return the string
	 */
	public static String generateCaptchaTextMethod2(int captchaLength) {

		String saltChars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
		StringBuffer captchaStrBuffer = new StringBuffer();
		java.util.Random rnd = new java.util.Random();

		while (captchaStrBuffer.length() < captchaLength) {
			int index = (int) (rnd.nextFloat() * saltChars.length());
			captchaStrBuffer.append(saltChars.substring(index, index + 1));
		}

		return captchaStrBuffer.toString();

	}

}