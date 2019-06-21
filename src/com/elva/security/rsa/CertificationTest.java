package com.elva.security.rsa;

import java.math.BigInteger;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.cert.X509Certificate;

import org.bouncycastle.asn1.x509.X509Name;
import org.bouncycastle.x509.X509V3CertificateGenerator;

public class CertificationTest {
    /**
     * ���ɹ�Կ֤��
     * @param pubKey ��Ҫ��֤�Ĺ�Կ
     * @param trustedPrivateKey ��֤�˵�˽Կ
     * @throws Exception
     */
    public static void generateCertification(PublicKey pubKey,PrivateKey trustedPrivateKey) throws Exception{
        X509V3CertificateGenerator certGen = new X509V3CertificateGenerator();
        certGen.setPublicKey(pubKey);
        certGen.setSerialNumber(BigInteger.valueOf(1231212122L));
        certGen.setSignatureAlgorithm("SM3WITHSM2");
        certGen.setSubjectDN(new X509Name("YYY.com"));
        certGen.setIssuerDN(new X509Name("YYY.com"));
        /**
         * ����֤�飺���������Լ���˽Կ����֤��Կ����ݣ�ʹ��ĳ����Կ�ܵ���ҵ�����
         */
        X509Certificate cert = certGen.generate(trustedPrivateKey);
        
        System.out.println(cert);
    }
    
    public static KeyPair generateKeyPair() throws Exception{
        KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance("RSA");
        keyPairGen.initialize(1024);
        KeyPair keyPair = keyPairGen.generateKeyPair();
        return keyPair;
    }
    
    public static void main(String args[]) throws Exception{
        KeyPair pair = generateKeyPair();
        KeyPair pair2 = generateKeyPair();
        generateCertification(pair.getPublic(),pair2.getPrivate());
    }
    
}
