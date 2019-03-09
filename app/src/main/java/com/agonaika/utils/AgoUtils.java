package com.agonaika.utils;

import android.os.Build;
import android.security.keystore.KeyGenParameterSpec;
import android.security.keystore.KeyProperties;
import android.text.TextUtils;
import android.util.Base64;

import com.agonaika.data.AgoPreferences;

import java.nio.charset.Charset;
import java.security.KeyStore;
import java.security.SecureRandom;
import java.security.spec.KeySpec;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;

import static android.os.Build.VERSION_CODES.M;

public class AgoUtils {

    private final static int KEY_LENGTH = 128;
    private final static int ITERATION_COUNT = 50; //performance vs security //

    private final static String KEYSTORE = "AndroidKeyStore";
    private final static String KEY_ALIAS_USER = "IngeniousMedKey";
    private final static int GCM_IV_SIZE = 12;
    private final static int IV_LENGTH = 256;
    private static final String HMAC_ALGORITHM = "HmacSHA256";
    private static String dbKey = null;

    public static String encodeUData(String i, String u) {
        return encode(i, getOne(u));
    }

    public static String decodeUData(String i, String u) {
        return decode(i, getOne(u));
    }

    public static String AddAZeroBeforeSingleDigit(int num) {
        return (num <= 9) ? ("0" + num) : ("" + num);
    }

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

    /**
     *
     * Here we encrypt some arbritrary String using AES and Google's recommended method:
     * http://android-developers.blogspot.com/2016/06/security-crypto-provider-deprecated-in.html
     * and
     * https://nelenkov.blogspot.com/2012/04/using-password-based-encryption-on.html
     *
     */
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

    /**
     *  Encrypts data using the Android Keystore for devices on API 23 and above, (Marshamallow)
     *  Prevents extraction of private keys. Stores IV in beginning of encrypted string for
     *  extraction later.
     *
     */

    public static String encryptWithKeyStore(String value) {
        String encrypted = "";

        if (!TextUtils.isEmpty(value)) {
            try {
                generateKeyStoreKey();
                KeyStore ks = KeyStore.getInstance(KEYSTORE);
                ks.load(null);

                KeyStore.Entry entry = ks.getEntry(KEY_ALIAS_USER, null);

                SecretKey secretKey = ((KeyStore.SecretKeyEntry) entry).getSecretKey();

                Cipher c = Cipher.getInstance("AES/GCM/NoPadding");

                byte[] iv;

                c.init(Cipher.ENCRYPT_MODE, secretKey);

                iv = c.getParameters().getParameterSpec(GCMParameterSpec.class).getIV();

                byte[] rawEncrypted = c.doFinal(value.getBytes(Charset.forName("UTF-8")));


                byte[] combinedMessage = new byte[iv.length + rawEncrypted.length];
                System.arraycopy(iv, 0, combinedMessage, 0, iv.length);
                System.arraycopy(rawEncrypted, 0, combinedMessage, iv.length, rawEncrypted.length);


                encrypted = Base64.encodeToString(combinedMessage, Base64.DEFAULT);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return encrypted;
    }

    /**
     *  Decrypts data using the Android Keystore for devices on API 23 and above, (Marshamallow)
     *  Prevents extraction of private keys. Assuming an IV length (12), uses first part of
     *  encrypted string as the IV to decrypt the 2nd half of the string.
     *
     */
    public static String decryptWithKeyStore(String encrypted) {
        String value = "";
        if (!TextUtils.isEmpty(encrypted)) {
            try {
                KeyStore ks = KeyStore.getInstance(KEYSTORE);
                ks.load(null);

                SecretKey secretKey = (SecretKey) ks.getKey(KEY_ALIAS_USER, null);

                Cipher c = Cipher.getInstance("AES/GCM/NoPadding");

                byte[] iv = new byte[GCM_IV_SIZE];

                byte[] rawCombinedMessage = Base64.decode(encrypted, Base64.DEFAULT);
                byte[] rawEncrypted = new byte[rawCombinedMessage.length - iv.length];
                System.arraycopy(rawCombinedMessage, 0, iv, 0, iv.length);
                System.arraycopy(rawCombinedMessage, iv.length, rawEncrypted, 0, rawEncrypted.length);
                c.init(Cipher.DECRYPT_MODE, secretKey, new GCMParameterSpec(128, iv));

                byte[] plaintext = c.doFinal(rawEncrypted);
                return new String(plaintext, "UTF-8");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return value;
    }

    /**
     *  This method generates encryption keys using the Android Keystore.
     *  Only compatible for Marshmallow devices and above(sdk 23+).
     *  Will not do anything on versions below sdk 23.
     */
    public static void generateKeyStoreKey() {
        if (Build.VERSION.SDK_INT >= M) {
            //generate keys
            try {
                KeyStore keyStore = KeyStore.getInstance(KEYSTORE);
                keyStore.load(null);

                if (!keyStore.containsAlias(KEY_ALIAS_USER)) {
                    KeyGenerator keyGenerator = KeyGenerator.getInstance(KeyProperties.KEY_ALGORITHM_AES, KEYSTORE);
                    keyGenerator.init(new KeyGenParameterSpec.Builder(KEY_ALIAS_USER,
                            KeyProperties.PURPOSE_ENCRYPT | KeyProperties.PURPOSE_DECRYPT)
                            .setBlockModes(KeyProperties.BLOCK_MODE_GCM)
                            .setEncryptionPaddings(KeyProperties.ENCRYPTION_PADDING_NONE)
                            .setRandomizedEncryptionRequired(false)
                            .build());
                    keyGenerator.generateKey();
                }

            } catch (Exception e) {
                //report error
                e.printStackTrace();
            }
        }
    }

    /**
     * Encrypts pin with Android Keystore if using Android API 23+ (Marshmallow) and saves to pref.
     * Uses older insecure encryption method otherwise.
     *
     * @param value 4 digit quick pin
     */
    public static void saveAndEncryptQuickPinToPref(String value) {
        if (Build.VERSION.SDK_INT >= M) {
            AgoPreferences.putString(AgoPreferences.getKeyForCurrentUser(AgoPreferences.PREF_QUICK_PASSCODE), AgoUtils.encryptWithKeyStore(value));
        } else {//insecure pre-M method
            String encodedPin = AgoEncrypter.encodeValue(value);
            AgoPreferences.putString(AgoPreferences.getKeyForCurrentUser(AgoPreferences.PREF_QUICK_PASSCODE), encodedPin);
        }
    }

    public static void resetDBKeyInMemory() {
        dbKey = null;
    }
}
