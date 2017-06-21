package com.elva.security.des;

import java.security.SecureRandom;
import java.util.UUID;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

import org.apache.commons.codec.binary.Base64;

public class DESTestMain {
    static String password = "12345678";
    public static String encrypt() throws Exception{
        String content = UUID.randomUUID().toString().replace("-", "");
        Base64 base64 = new Base64();
        
        DESKeySpec desKeySpec = new DESKeySpec(password.getBytes());
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
        SecretKey key = keyFactory.generateSecret(desKeySpec);
        
        SecureRandom random = new SecureRandom();
        Cipher cipher = Cipher.getInstance("DES");
        cipher.init(Cipher.ENCRYPT_MODE, key,random);
        
        byte[] encryptData=cipher.doFinal(content.getBytes());
        String base64Data = base64.encodeAsString(encryptData);
        System.out.println(base64Data);
        return base64Data;
    }
    
    public static String decrypt(String base64Data) throws Exception{
        Base64 base64 = new Base64();
        
        DESKeySpec desKeySpec = new DESKeySpec(password.getBytes());
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
        SecretKey key = keyFactory.generateSecret(desKeySpec);
        
        SecureRandom random = new SecureRandom();
        Cipher cipher = Cipher.getInstance("DES");
        cipher.init(Cipher.DECRYPT_MODE, key,random);
        
        byte[] src=base64.decode(base64Data.getBytes());
        
        byte[] decryptData=cipher.doFinal(src);
        
        String orig = new String(decryptData);
        System.out.println(orig);
        return orig;
    }
    
    public static void main(String args[]) throws Exception{
        String encStr = encrypt();
        decrypt(encStr);
    }
}
