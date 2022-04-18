package com.doranco.multitiers.utils;

import java.io.IOException;
import java.io.InputStream;
import java.security.Key;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;

import javax.crypto.spec.SecretKeySpec;

import org.apache.log4j.Logger;



public class KeyGenerator {

	private static KeyGenerator INSTANCE;

	private Key key;
	private KeyStore keyStore;
	
	Logger logger  = Logger.getLogger(KeyGenerator.class);

	public Key getKey() {
		return key;
	}

	private KeyGenerator() throws NoSuchAlgorithmException, KeyStoreException, CertificateException, IOException, UnrecoverableKeyException {

		//loadKeyStore();
		//key = keyStore.getKey("keyToken", "azerty".toCharArray());
		
		String keyString = "cleStatique";
		key = new SecretKeySpec(keyString.getBytes(), 0, keyString.getBytes().length, "DES");
	}

	public static KeyGenerator getInstance() throws NoSuchAlgorithmException, KeyStoreException, CertificateException, IOException, UnrecoverableKeyException {

		if (INSTANCE == null)
			INSTANCE = new KeyGenerator();
		return INSTANCE;
	}

	//chargement de la cle sauvegardee en fichier libraryKeyStore(fichier verrouillé par mot de passe "azerty")
	/*private void loadKeyStore() throws KeyStoreException, NoSuchAlgorithmException, CertificateException, IOException {
		
		InputStream is = null;
		is = this.getClass().getClassLoader().getResourceAsStream("libraryKeyStore");
		keyStore = KeyStore.getInstance("PKCS12");
		keyStore.load(is,  "azerty".toCharArray());
		
	}*/
}
