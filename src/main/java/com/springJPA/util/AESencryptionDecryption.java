package com.springJPA.util;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.stereotype.Component;
@Component
public class AESencryptionDecryption {
	private static final String ALGORITHM= "AES";
	private static byte[] keyValue = "1234567891234567".getBytes();
	
	public static Key getKey() {
		
		Key key = new SecretKeySpec(keyValue, ALGORITHM);
		
		return key;
	}
	public static String getEncryption(String originalData, Key key) {
		String encryptData="";
		try {
			Cipher cipher = Cipher.getInstance(ALGORITHM);
			
			cipher.init(Cipher.ENCRYPT_MODE, key);
			byte[] encValue = cipher.doFinal(originalData.getBytes());
			byte[] encryptValue = Base64.getEncoder().encode(encValue);
			encryptData = new String(encryptValue,"UTF-8");
			
		} catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | IllegalBlockSizeException | BadPaddingException | UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return encryptData;
	}
	public static String getDecryption(String encryptData, Key key) {
		String decryptData ="";
		try {
			Cipher cipher = Cipher.getInstance(ALGORITHM);
			cipher.init(Cipher.DECRYPT_MODE, key);
			byte[]decValue =Base64.getDecoder().decode(encryptData.getBytes());
			byte[]decryptValue =cipher.doFinal(decValue);
			decryptData = new String(decryptValue, "UTF-8");
			
		} catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | IllegalBlockSizeException | BadPaddingException | UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return decryptData;
		
	}


}
