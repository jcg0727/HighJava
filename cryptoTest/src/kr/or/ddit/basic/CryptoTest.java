package kr.or.ddit.basic;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

import kr.or.ddit.util.CryptoUtil;

public class CryptoTest {
	public static void main(String[] args) throws Exception {
		String plainText = "Hello, World! 가나다라 마바사아 123456 !@#$%^&";
		String key = "a1b2c3d4e5f6g7h8"; // 암호화 키값 (16자리 이상)
		
		System.out.println("원본데이터 : " + plainText);
		String sha512 = CryptoUtil.sha512(plainText);
		System.out.println("단방향 SHA-26 : " + sha512 + " - " + sha512.length());
		System.out.println();
		
		
		
		//암호화 하기 
		String enc = CryptoUtil.encryptAED256(plainText, key); 
		System.out.println("암호화된 값 : " + enc);
		
		//복호화 하기 
		String dec = CryptoUtil.decryptAES256(enc, key);
		System.out.println("복호화된 값 : " + dec);
		
		
	}
}
