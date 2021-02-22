package com.easidiomas.auth.encryption;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

public class AESDecryptor {

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
