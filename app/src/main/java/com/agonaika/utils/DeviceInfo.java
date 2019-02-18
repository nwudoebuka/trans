package com.agonaika.utils;

import android.content.Context;
import android.content.res.Configuration;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.provider.Settings;
import android.util.Base64;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;

import com.agonaika.data.AgoAppEngine;

import java.io.IOException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.UnrecoverableEntryException;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.util.UUID;

public class DeviceInfo {

    private static DisplayMetrics displayMetrics;

    private static int sScreenSize;

    private static String sDeviceId;
    private static String sDeviceName;
    private static String sPlatformDescription;

    static {
        final Context context = AgoAppEngine.getContext();

        String androidDeviceId = Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID);
        if (androidDeviceId == null) {
            androidDeviceId = "";
        }

        sDeviceId = UUID.nameUUIDFromBytes(androidDeviceId.getBytes()).toString();
        sDeviceName = Build.DEVICE;
        sPlatformDescription = Build.MODEL + " - " + Build.VERSION.RELEASE;
        sScreenSize = context.getResources().getConfiguration().screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK;
        final Display display = ((WindowManager) context.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();
        displayMetrics = new DisplayMetrics();
        display.getMetrics(displayMetrics);
    }

    public static boolean hasConnection() {
        ConnectivityManager manager = (ConnectivityManager) AgoAppEngine.getContext()
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        if (manager != null) {
            NetworkInfo info = manager.getActiveNetworkInfo();
            return info != null && info.isConnected();
        }
        return false;
    }

    public static String getDeviceId() {
        return sDeviceId;
    }

    public static PrivateKey getPrivateKey(String alias) {
        PrivateKey retval = null;

        KeyStore keyStore;
        try {
            keyStore = KeyStore.getInstance("AndroidKeyStore");
            keyStore.load(null, null);
            KeyStore.Entry entry = keyStore.getEntry(alias, null);
            if (!(entry instanceof KeyStore.PrivateKeyEntry)) {

            } else {
                retval =((KeyStore.PrivateKeyEntry) entry).getPrivateKey();
            }

        } catch (KeyStoreException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (CertificateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (UnrecoverableEntryException e) {
            e.printStackTrace();
        }

        return retval;
    }

    public static PublicKey getPublicKey(String alias) {
        PublicKey publicKey = null;

        KeyStore keyStore;
        try {
            keyStore = KeyStore.getInstance("AndroidKeyStore");
            keyStore.load(null);
            Certificate certificate = keyStore.getCertificate(alias);
            publicKey = certificate.getPublicKey();
        } catch (KeyStoreException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (CertificateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return publicKey;
    }

    @Override
    public String toString() {
        return  "DeviceId=" + sDeviceId
                + " DeviceName=" + sDeviceName
                + " PlatformDescription=" + sPlatformDescription
                + " ScreenSize=" + sScreenSize;
    }

    public static String getEncodedStringOfPublicKey(String alias) {
        String encodedStringofPublicKey = "";

        byte [] byteArray = getPublicKey(alias).getEncoded();
        encodedStringofPublicKey = Base64.encodeToString(byteArray, Base64.DEFAULT);

        return encodedStringofPublicKey;
    }
}
