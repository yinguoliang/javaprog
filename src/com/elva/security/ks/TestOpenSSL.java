package com.elva.security.ks;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.security.Key;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

import javax.crypto.Cipher;

import org.apache.commons.codec.binary.Base64;

public class TestOpenSSL {
	public static String readContent(String filename)throws Exception{
		String filePath =TestKeyStore.class.getResource(filename).getFile(); 
		File file = new File(filePath);
		//读取base64内容
		BufferedReader reader = new BufferedReader(new FileReader(file));
		String rawtxt = "";
		String line = null;
		while((line = reader.readLine())!=null){
			if(line.indexOf("-")>=0) continue;
			rawtxt += line+"\r";
		}
		reader.close();
		System.out.println(rawtxt);
		return rawtxt;
	}
	/**
	 * 读取私钥
	 * @return
	 * @throws Exception
	 */
	public static PrivateKey readPrivateKey() throws Exception{
		String rawtxt = readContent("my.pkcs8.key");
		byte[] rawbytes = Base64.decodeBase64(rawtxt.getBytes());
		KeyFactory keyFactory = KeyFactory.getInstance("RSA");
		PKCS8EncodedKeySpec keyspec = new PKCS8EncodedKeySpec(rawbytes);
		return keyFactory.generatePrivate(keyspec);
	}
	/**
	 * 读取公钥
	 * @return
	 * @throws Exception
	 */
	public static PublicKey readPublicKey() throws Exception{
		String rawtxt = readContent("pub.key");
		byte[] rawbytes = Base64.decodeBase64(rawtxt.getBytes());
		KeyFactory keyFactory = KeyFactory.getInstance("RSA");
		X509EncodedKeySpec keyspec = new X509EncodedKeySpec(rawbytes);
		return keyFactory.generatePublic(keyspec);
	}
	public static String encrypt(Key key,byte[] src) throws Exception{
		Cipher cipher = Cipher.getInstance("RSA");
		cipher.init(Cipher.ENCRYPT_MODE, key);
		byte[] encs = cipher.doFinal(src);
		return Base64.encodeBase64String(encs);
	}
	public static byte[] decrypt(Key key,String enc) throws Exception{
		byte[] encs = Base64.decodeBase64(enc);
		Cipher cipher = Cipher.getInstance("RSA");
		cipher.init(Cipher.DECRYPT_MODE, key);
		return cipher.doFinal(encs);
	}
	public static void main(String[] args) throws Exception {
		/*
		 * 注意，如果解密密码错误，会报：Data must start with zero
		 */
		PrivateKey priKey = readPrivateKey();
		PublicKey  pubKey = readPublicKey();
		//*******私钥加密，公钥解密********
		String enc = encrypt(priKey,"Hello中国".getBytes());
		System.out.println(enc);
		byte[] decs = decrypt(pubKey,enc);
		System.out.println(new String(decs));
		//*******公钥加密，私钥解密********
		String enc2 = encrypt(pubKey,"Hello China".getBytes());
		System.out.println(enc2);
		byte[] decs2 = decrypt(priKey,enc2);
		System.out.println(new String(decs2));
	}
}
