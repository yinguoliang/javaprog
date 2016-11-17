1. 密码
      加密系统都需要一个密码，java的加密解密套件不接受普通的字符串作为密码，而必须使用java.security.Key的实现类。创建Key类由很多种方法。
      加密套件：javax.crypto.Cipher
      备注：有些加密算法会加盐（如PBE），这个是加密算法的问题，跟密码没有关系
1.1 KeyGenerator生成一个随机密码
      不用我们制定特定的字符串作为密码，随机生成一个密码。
      可以通过key.getEncoded()获得密码的byte数字形式，
      然后我们就可以将这个byte数字保存起来（转成字符串或者文件）
      也可以将key对象保存到keystore文件中（SecretKey)

1.2 javax.crypto.SecretKeyFactory
     根据给定的密码(byte数组）生成密码java.security.Key
     SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
     SecretKey secKey = keyFactory.generateSecret(null);
      
     另外：非对称秘钥通常会以文件或者byte的形式给到我们，这是我们也需要用SecretKeyFactory将byte数组形式的密码转换为Key对象。

1.3 KeyPairGenerator
      用于生成非对称秘钥
      公钥和私钥可以存储为文件或者字符串(base64编码)
      使用SecretKeyFactory可以将这些文件或者byte数组转换为Key类
      也可以存储到KeyStore文件中（私钥可以直接存储，公钥需要转换为证书Certificate形式）

1.4 java.security.KeyStore
      顾名思义，KeyStore是用来存储Key的。 
      我们可以将SecretKey, PrivateKey,Certificate等存储到KeyStore中。
      （公钥证书生成类X509V3CertificateGenerator）

2 加密和签名
   加密：加密是将明文加密为密文的过程，密文最终是需要还原的
   签名：对明文进行验证，防止明文被篡改。