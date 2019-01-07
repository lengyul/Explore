package com.expolre.utils;

import java.io.FileInputStream;
import java.io.InputStream;
import java.security.KeyFactory;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.Provider;
import java.security.PublicKey;
import java.security.cert.Certificate;
import java.security.cert.CertificateFactory;
import java.security.spec.KeySpec;
import java.security.spec.X509EncodedKeySpec;
import org.apache.commons.codec.binary.Base64;

/**
 * RSA加解密，加签解签
 * <li>依赖 commons-codec-1.10.jar
 * <li>依赖 bcprov-jdk15on-1.59.jar(解决Sun和IBM差异)
 * @author ShineDin
 *
 */
public class RSAUtil {
	private static final String CHARSET = "UTF-8";
	private static final String RSA = "RSA";
	private static final String PKCS12 = "PKCS12";
	private static final String X_509 = "X.509";
	private static final Provider PROVIDER = null;  
	
	
	/**
	 * 从文件中读私钥
	 * @param filename
	 * @param passwd
	 * @return
	 * @throws Exception
	 */
	public static PrivateKey readPrivateKeyByFile(String filename, String passwd) throws Exception{
		return readPrivateKeyByStream(new FileInputStream(filename), passwd);
	}
	
	/**
	 * 从流中读取私钥
	 * @param stream
	 * @param passwd
	 * @return
	 * @throws Exception
	 */
	public static PrivateKey readPrivateKeyByStream(InputStream stream, String passwd) throws Exception{
		KeyStore keyStore = KeyStore.getInstance(PKCS12, PROVIDER);
		keyStore.load(stream, passwd.toCharArray());
		String keyAlias = keyStore.aliases().nextElement();
		return (PrivateKey) keyStore.getKey(keyAlias, passwd.toCharArray());
	}
	
	/**
	 * 从Base64编码的私钥中读取公钥
	 * @param publicKeyBase64Encoded
	 * @return
	 * @throws Exception
	 */
	public static PublicKey readPublicKeyByBase64Encoded(String pubKey) throws Exception {
		// 获取公钥
		byte[] encodedKey = Base64.decodeBase64(pubKey.getBytes());
		KeySpec keySpec = new X509EncodedKeySpec(encodedKey);
        KeyFactory keyFactory = KeyFactory.getInstance(RSA, PROVIDER);
        return keyFactory.generatePublic(keySpec);
	}

	/**
	 * 从文件中读取私钥
	 * @param filename
	 * @return
	 * @throws Exception
	 */
	public static PublicKey readPublicKeyByFile(String filename) throws Exception {
		return readPublicKeyByStream(new FileInputStream(filename));
	}
	
	/**
	 * 从流中读取公钥
	 * @param stream
	 * @return
	 * @throws Exception
	 */
	public static PublicKey readPublicKeyByStream(InputStream stream) throws Exception {
		CertificateFactory certificatefactory=CertificateFactory.getInstance(X_509, PROVIDER); 
		Certificate cert = certificatefactory.generateCertificate(stream);
		return cert.getPublicKey();
	}

}
