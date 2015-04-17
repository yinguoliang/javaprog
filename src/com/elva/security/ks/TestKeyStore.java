package com.elva.security.ks;

import java.io.File;
import java.io.FileInputStream;
import java.security.Key;
import java.security.KeyStore;
import java.security.cert.Certificate;
import java.util.Enumeration;

import javax.crypto.spec.SecretKeySpec;
/**
 * KeyStore���Դ洢PrivateKey/SecretKey/TrustCertificate
 * @author lenovo
 *
 */
public class TestKeyStore {
	/**
	 * ��JKS�ж�ȡ
	 * @throws Exception
	 */
	public static void readJKS() throws Exception{
		String filePath =TestKeyStore.class.getResource("my.jks").getFile(); 
		File file = new File(filePath);
		//ÿ��KeyStore�����Ӧһ��keystore�ļ�
		KeyStore keystore = KeyStore.getInstance("JKS");
		keystore.load(new FileInputStream(file), "123456".toCharArray());
		Enumeration<String> e = keystore.aliases();
		while(e.hasMoreElements()){
			String alias = e.nextElement();
			//��ȡ˽Կ
			Key key = keystore.getKey(alias, "p123456".toCharArray());
			if(key!=null) System.out.println(alias+":\t"+key.getClass());
			//��ȡ֤��
			Certificate cert = keystore.getCertificate(alias);
			if(cert!=null) System.out.println(alias+":\t"+cert.getClass());
		}
	}
	/**
	 * ��JCEKS�ж�ȡ
	 * @throws Exception
	 */
	public static void readJCEKS() throws Exception{
		String filePath =TestKeyStore.class.getResource("my.jceks").getFile(); 
		File file = new File(filePath);
		KeyStore keystore = KeyStore.getInstance("JCEKS");
		keystore.load(new FileInputStream(file), "123456".toCharArray());
		Enumeration<String> e = keystore.aliases();
		while(e.hasMoreElements()){
			String alias = e.nextElement();
			String password = "p123456";
			if(alias.equals("ygl.seckey")) password = "pk123456";
			//��ȡPrivateKey��SecretKey
			Key key = keystore.getKey(alias, password.toCharArray());
			if(key!=null) System.out.println(alias+":\t"+key.getClass());
			//��ȡCertificate
			Certificate cert = keystore.getCertificate(alias);
			if(cert!=null) System.out.println(alias+":\t"+cert.getClass());
		}
		
		SecretKeySpec sk = (SecretKeySpec)keystore.getKey("ygl.seckey", "pk123456".toCharArray());
		System.out.println(sk.getEncoded());
		
	}
	public static void main(String[] args) throws Exception {
		readJCEKS();
	}

}
