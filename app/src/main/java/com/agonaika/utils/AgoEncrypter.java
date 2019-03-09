package com.agonaika.utils;

import android.content.ContentValues;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;

import com.agonaika.data.AppConstants;
import com.agonaika.data.remote.JsonService;
import com.agonaika.data.remote.LoginCredential;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.security.KeyStore;
import java.security.interfaces.RSAPublicKey;
import java.util.ArrayList;
import java.util.Locale;

import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.CipherOutputStream;

public class AgoEncrypter {

    public static String AgoPublicKey = "";
    public static String AgoCertificate = "";


    // ==================================================
    // Methods
    // ==================================================

    public static ContentValues encodeData(String[] fields, ContentValues contentValues) {
        for (int i = 0; i < fields.length; i++) {
            String val = "";

            if (contentValues.containsKey(fields[i])) {
                val = contentValues.getAsString(fields[i]);
                val = AgoEncrypter.encodeValue(val);
                contentValues.put(fields[i], val);
            }
        }

        return contentValues;
    }

    public static String encodeValue(String value) {
        String key = AgoEncrypter.getKey();

        if (key == null) {
            throw (new IllegalArgumentException());
        }

        if (TextUtils.isEmpty(value)) {
            return "";
        }

        return AgoUtils.encode(value, key);
    }

    public static String decodeValue(String value) {
        if (TextUtils.isEmpty(value)) {
            return "";
        }

        String key = AgoEncrypter.getKey();

        if (key == null) {
            return "";
        }

        return AgoUtils.decode(value, key);
    }

    public static String getKey() {
        LoginCredential credentials = JsonService.getInstance().GetCredentials();
        String key;

        if (credentials != null) {
            key = credentials.password
                    + credentials.deviceIdUserHash.toLowerCase(Locale.US)
                    + credentials.userId.toLowerCase(Locale.US);
        } else {
            return null;
        }

        return key;
    }

    public static String encryptString(String toEncryptText) {
        String encryptedText = "";
        try {

            KeyStore keyStore = KeyStore.getInstance("AndroidKeyStore");
            keyStore.load(null);

            KeyStore.PrivateKeyEntry privateKeyEntry = (KeyStore.PrivateKeyEntry) keyStore.getEntry(AppConstants.RSA_KEYSTORE, null);
            RSAPublicKey publicKey = (RSAPublicKey) privateKeyEntry.getCertificate().getPublicKey();

            // Encrypt the text

            Cipher input = Cipher.getInstance("RSA/ECB/PKCS1Padding", "AndroidOpenSSL");
            input.init(Cipher.ENCRYPT_MODE, publicKey);

            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            CipherOutputStream cipherOutputStream = new CipherOutputStream(
                    outputStream, input);
            cipherOutputStream.write(toEncryptText.getBytes("UTF-8"));
            cipherOutputStream.close();

            byte [] vals = outputStream.toByteArray();
            encryptedText = Base64.encodeToString(vals, Base64.DEFAULT);
            Log.i("AgoEncrypter", "encrpyted text:" + encryptedText);
        } catch (Exception e) {
            Log.e("AgoEncrypter", Log.getStackTraceString(e));
        }
        return encryptedText;
    }

    public static String decryptString(String encryptedText) {
        String decryptedText = "";
        try {
            KeyStore keyStore = KeyStore.getInstance("AndroidKeyStore");
            keyStore.load(null);

            KeyStore.PrivateKeyEntry privateKeyEntry = (KeyStore.PrivateKeyEntry) keyStore.getEntry(AppConstants.RSA_KEYSTORE, null);

            Cipher output = Cipher.getInstance("RSA/ECB/PKCS1Padding");
            output.init(Cipher.DECRYPT_MODE, privateKeyEntry.getPrivateKey());

            String cipherText = encryptedText;

            CipherInputStream cipherInputStream = new CipherInputStream(
                    new ByteArrayInputStream(Base64.decode(cipherText, Base64.DEFAULT)), output);
            ArrayList<Byte> values = new ArrayList<Byte>();
            int nextByte;
            while ((nextByte = cipherInputStream.read()) != -1) {
                values.add((byte)nextByte);
            }

            byte[] bytes = new byte[values.size()];
            for(int i = 0; i < bytes.length; i++) {
                bytes[i] = values.get(i).byteValue();
            }

            String finalText = new String(bytes, 0, bytes.length, "UTF-8");
            decryptedText = finalText;
            Log.i("AgoEncrypter", "decrypted text:" + decryptedText);

        } catch (Exception e) {
            Log.e("AgoEncrypter", Log.getStackTraceString(e));
        }
        return decryptedText;
    }

}
