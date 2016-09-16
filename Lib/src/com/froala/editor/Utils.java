package com.froala.editor;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Calendar;

import javax.servlet.http.Part;

/**
 *
 * @author florin@froala.com
 *
 *         Basic utils.
 */
public final class Utils {

	/**
	 * Private constructor.
	 */
	private Utils() {

	}

	public static String generateUniqueString() throws NoSuchAlgorithmException {

		String miliseconds = Calendar.getInstance().get(Calendar.MILLISECOND) + "";
		MessageDigest d = null;
		d = java.security.MessageDigest.getInstance("SHA-1");
		d.reset();
		d.update(miliseconds.getBytes());
		return bytesToHex(d.digest());
	}

	public static String bytesToHex(byte[] in) {
		StringBuilder builder = new StringBuilder();
		for (byte b : in) {
			builder.append(String.format("%02x", b));
		}
		return builder.toString();
	}

	public static String getFileName(Part part) {
		for (String cd : part.getHeader("content-disposition").split(";")) {
			if (cd.trim().startsWith("filename")) {
				return cd.substring(cd.indexOf('=') + 1).trim().replace("\"", "");
			}
		}
		return null;
	}
}
