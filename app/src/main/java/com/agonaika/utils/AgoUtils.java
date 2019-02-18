package com.agonaika.utils;

import android.util.Base64;

import java.nio.charset.Charset;
import java.security.SecureRandom;
import java.security.spec.KeySpec;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;

public class AgoUtils {

    private final static int KEY_LENGTH = 128;
    private final static int ITERATION_COUNT = 50; //performance vs security //

    public static String getOne(String str) {
        String p1 = DeviceInfo.getDeviceId();
        return str + p1;
    }

    public static String encode(String stringToCode, String z) {
        try {
            stringToCode = encrypt(z, stringToCode);
        } catch (Exception e1) {
            e1.printStackTrace();
            stringToCode = "";
        }
        return stringToCode;
    }

    public static String decode(String stringToDecode, String z) {
        try {
            stringToDecode = decrypt(z, stringToDecode);
        } catch (Exception e1) {}

        return stringToDecode;
    }

    public static String encrypt(String seed, String cleartext) throws Exception {
        /* Store these things on disk used to derive key later: */
        // 256-bits for AES-256, 128-bits for AES-128, etc
        if (cleartext == null) {
            cleartext = "";
        }
        int saltLength = KEY_LENGTH / 8; // bytes; should be the same size as the output (256 / 8 = 32)

        byte[] salt; // Should be of saltLength

        /* When first creating the key, obtain a salt with this: */
        SecureRandom random = new SecureRandom();
        salt = new byte[saltLength];
        random.nextBytes(salt);

        /* Use this to derive the key from the password: */
        KeySpec keySpec = new PBEKeySpec(seed.toCharArray(), salt,
                ITERATION_COUNT, KEY_LENGTH);

        try {
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
            byte[] keyBytes = keyFactory.generateSecret(keySpec).getEncoded();
            SecretKey secretKey = new SecretKeySpec(keyBytes, "AES");

            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");

            byte[] iv = new byte[cipher.getBlockSize()];
            random.nextBytes(iv);
            IvParameterSpec ivParams = new IvParameterSpec(iv);

            cipher.init(Cipher.ENCRYPT_MODE, secretKey, ivParams);

            byte[] encryptedMessage = cipher.doFinal(cleartext.getBytes(Charset.forName("UTF-8")));

            byte[] concatenatedMessage = new byte[encryptedMessage.length + salt.length + iv.length];
            System.arraycopy(salt, 0, concatenatedMessage,0, salt.length);
            System.arraycopy(iv, 0, concatenatedMessage, salt.length, iv.length);
            System.arraycopy(encryptedMessage, 0 , concatenatedMessage, saltLength + iv.length, encryptedMessage.length);

            return Base64.encodeToString(concatenatedMessage, Base64.DEFAULT);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private static SecretKey deriveKeyPbkdf2(byte[] salt, String pass) {
        try {
            KeySpec keySpec = new PBEKeySpec(pass.toCharArray(), salt,
                    ITERATION_COUNT, KEY_LENGTH);
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
            byte[] keyBytes = keyFactory.generateSecret(keySpec).getEncoded();
            return new SecretKeySpec(keyBytes, "AES");
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String decrypt(String seed, String encrypted) throws Exception {
        byte[] concatenatedMessage = Base64.decode(encrypted, Base64.DEFAULT);
        try {
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            byte[] iv = new byte[cipher.getBlockSize()];
            byte[] salt = new byte[KEY_LENGTH/8];
            byte[] encryptedMessage = new byte[concatenatedMessage.length - salt.length - iv.length];

            System.arraycopy(concatenatedMessage, 0, salt,0,salt.length);
            System.arraycopy(concatenatedMessage, salt.length, iv,0,iv.length);
            System.arraycopy(concatenatedMessage, salt.length + iv.length, encryptedMessage,0, encryptedMessage.length);
            SecretKey secretKey = deriveKeyPbkdf2(salt, seed);

            IvParameterSpec ivParams = new IvParameterSpec(iv);
            cipher.init(Cipher.DECRYPT_MODE, secretKey, ivParams);
            byte[] plaintext = cipher.doFinal(encryptedMessage);
            return new String(plaintext, "UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return encrypted;
    }
}
