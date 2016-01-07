package br.com.gedai.utils;

import java.security.MessageDigest;

public class Criptografia {
	public static String getSenha(String senha) {
		StringBuilder hexString = new StringBuilder();
		
		try {
			MessageDigest algorithm = MessageDigest.getInstance("SHA-256");
			byte messageDigest[] = algorithm.digest(senha.getBytes("UTF-8"));
			
			for (byte b : messageDigest) 
				hexString.append(String.format("%02X", 0xFF & b));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return hexString.toString();
	}
	
}
