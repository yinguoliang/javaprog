1. ����
      ����ϵͳ����Ҫһ�����룬java�ļ��ܽ����׼���������ͨ���ַ�����Ϊ���룬������ʹ��java.security.Key��ʵ���ࡣ����Key���ɺܶ��ַ�����
      �����׼���javax.crypto.Cipher
      ��ע����Щ�����㷨����Σ���PBE��������Ǽ����㷨�����⣬������û�й�ϵ
1.1 KeyGenerator����һ���������
      ���������ƶ��ض����ַ�����Ϊ���룬�������һ�����롣
      ����ͨ��key.getEncoded()��������byte������ʽ��
      Ȼ�����ǾͿ��Խ����byte���ֱ���������ת���ַ��������ļ���
      Ҳ���Խ�key���󱣴浽keystore�ļ��У�SecretKey)

1.2 javax.crypto.SecretKeyFactory
     ���ݸ���������(byte���飩��������java.security.Key
     SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
     SecretKey secKey = keyFactory.generateSecret(null);
      
     ���⣺�ǶԳ���Կͨ�������ļ�����byte����ʽ�������ǣ���������Ҳ��Ҫ��SecretKeyFactory��byte������ʽ������ת��ΪKey����

1.3 KeyPairGenerator
      �������ɷǶԳ���Կ
      ��Կ��˽Կ���Դ洢Ϊ�ļ������ַ���(base64����)
      ʹ��SecretKeyFactory���Խ���Щ�ļ�����byte����ת��ΪKey��
      Ҳ���Դ洢��KeyStore�ļ��У�˽Կ����ֱ�Ӵ洢����Կ��Ҫת��Ϊ֤��Certificate��ʽ��

1.4 java.security.KeyStore
      ����˼�壬KeyStore�������洢Key�ġ� 
      ���ǿ��Խ�SecretKey, PrivateKey,Certificate�ȴ洢��KeyStore�С�
      ����Կ֤��������X509V3CertificateGenerator��

2 ���ܺ�ǩ��
   ���ܣ������ǽ����ļ���Ϊ���ĵĹ��̣�������������Ҫ��ԭ��
   ǩ���������Ľ�����֤����ֹ���ı��۸ġ�