package com.easidiomas.auth.encryption;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

/**
 * The utility class AESDecryptor contains the functionality to decrypt an AES
 * encrypted String.
 */
public class AESDecryptor {

    /**
     * Receives an AES encrypted string and decrypts it by means of the AESEncryptor
     * default IV and KEY.
     * 
     * @param encrypted string to decript.
     * @return the decrypted string if decryption was possible or null otherwise.
     */
    public static String decrypt(String encrypted) {
        try {
            IvParameterSpec iv = new IvParameterSpec(AESEncryptor.IV.getBytes("UTF-8"));
            SecretKeySpec skeySpec = new SecretKeySpec(AESEncryptor.KEY.getBytes("UTF-8"), "AES");

            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
            cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);
            byte[] original = cipher.doFinal(Base64.getDecoder().decode(encrypted));

            return new String(original);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }
}
