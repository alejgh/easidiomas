package com.easidiomas.auth.encryption;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

/**
 * The utility class AESEncryptor contains the functionality to encrypt an
 * String. It uses a default key and vector initialization. If any error during
 * encryption will return null.
 */
public class AESEncryptor {

    // Shared with the decryptor.
    public static final String KEY = System.getProperty("ENCRYPTION_KEY", "aesEncryptionKey");
    public static final String IV = System.getProperty("ENCRYPTION_VECTOR", "encryptionIntVec");

    /**
     * Receives an string and encrypts it by means of the AESEncryptor default IV
     * and KEY.
     * 
     * @param value string to encrypt.
     * @return the encrypted string if encryption was possible or null otherwise.
     */
    public static String encrypt(String value) {
        try {
            IvParameterSpec iv = new IvParameterSpec(IV.getBytes("UTF-8"));
            SecretKeySpec skeySpec = new SecretKeySpec(KEY.getBytes("UTF-8"), "AES");

            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
            cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);

            byte[] encrypted = cipher.doFinal(value.getBytes());
            return Base64.getEncoder().encodeToString(encrypted);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }
}
