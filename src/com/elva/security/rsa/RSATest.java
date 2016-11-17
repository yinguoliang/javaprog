package com.elva.security.rsa;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

import org.apache.commons.codec.binary.Base64;

public class RSATest {
    /**
     * …˙≥…√ÿ‘ø∂‘
     * @throws Exception
     */
    public static void generateKeyPair() throws Exception{
        KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA");
        
        generator.initialize(1024);
        
        KeyPair keyPair = generator.generateKeyPair();
        
        RSAPublicKey pubKey = (RSAPublicKey)keyPair.getPublic();
        RSAPrivateKey priKey = (RSAPrivateKey)keyPair.getPrivate();
        
        Base64 base64 = new Base64();
        System.out.println("=============public key=================");
        byte[] pubByte = pubKey.getEncoded();
        System.out.println(pubByte.length);
        System.out.println(base64.encodeAsString(pubByte));
        
        byte[] decodePub = base64.decode("MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCQ6/9cpuadhXh4zUdKgQJGBzKEcVq1vJSFly2kykm4SLLRmiQExoYkohFci+nGeuSh4g+AICejo+M3ivVZCc1fMUex5wCzYkqGzxrTq9yf7ChB5hAF7m3Gm3qAt2Oz3OKD/NBztrJSQvxGBWHChcASx7szNWIqKRBkdwFzK7EGRQIDAQAB");
        
        System.out.println(decodePub.length);
        System.out.println("=============private key=================");
        byte[] priByte = priKey.getEncoded();
        System.out.println(priByte.length);
        System.out.println(base64.encodeAsString(priByte));
        
        byte[] decodePri = base64.decode("MIICdQIBADANBgkqhkiG9w0BAQEFAASCAl8wggJbAgEAAoGBAJDr/1ym5p2FeHjNR0qBAkYHMoRxWrW8lIWXLaTKSbhIstGaJATGhiSiEVyL6cZ65KHiD4AgJ6Oj4zeK9VkJzV8xR7HnALNiSobPGtOr3J/sKEHmEAXubcabeoC3Y7Pc4oP80HO2slJC/EYFYcKFwBLHuzM1YiopEGR3AXMrsQZFAgMBAAECgYAF/88Fbd9UHAddsNnHqsmGTpv3wZ2IsnLPiO+Zx8n/d/r3FFNzYyiAcbkYRK57yfCXBv2vRyV877bjQLxhYDeBLce87KK89sLFqWqOlPbirNBD5n7KZ4JB23L4e65VgMy0/zqbwgPeVqN2UqARf2okWQiEmvZptXuF6BYh84DnTQJBAPxX8G7X1osPsCDTKEGr5XuHYbLVN979D126wt67E4dwtRHod29VhIwanPlYUeQAhL7YMhBqPo2KouSAjcCLUVcCQQCTBZS1DdGovCq8+sQWiviOvCxG+u6uR+riVHXJrDHTNQ8u4grAXi+Es1U/JLLTyxbXRAqI/SQjqau3rYZujtfDAkBFRr8zLv4vXzczS8UZgl+V8izYM8EK7t5xFPy6uRE7da3AJNnlLs0cyZGewqh4t8u5uRDFgOV7ZjwiD0jbO2QxAkBJSjjtYKwpb1PmzSM/EZwCm29/Gsws+RDwLqZhGIPmA8dD2c7+QnVxs2xFg+fZiJ4kRXjy/S4s0QIXGFuxdWe9AkBhxE2WXr1J+ZGOulKOzeSb6g8bnrdgpUUCFN2m9Vc/qUNy/mwDI4ufoXWH9WEOup6FlgbOZdcXMpAsjVjz4Odu");
        System.out.println(decodePri.length);
        
        
    }
    
    public static void main(String args[]) throws Exception {
        generateKeyPair();
    }
}
