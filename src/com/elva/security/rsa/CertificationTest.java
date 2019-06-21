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
     * 生成公钥证书
     * @param pubKey 需要认证的公钥
     * @param trustedPrivateKey 公证人的私钥
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
         * 数字证书：公信人用自己的私钥来保证公钥的身份，使得某个公钥受到大家的信任
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
