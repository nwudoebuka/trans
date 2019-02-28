package com.agonaika.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

public class StreamUtil {
    private static final String UTF_8 = "UTF-8";

    public static String getStringFromStream(InputStream inputStream) {
        BufferedReader streamReader;

        try {
            streamReader = new BufferedReader(new InputStreamReader(inputStream, UTF_8));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        }

        StringBuilder responseBuilder = new StringBuilder();
        String inputString;

        try {
            while ((inputString = streamReader.readLine()) != null) {
                responseBuilder.append(inputString);
            }
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

        return responseBuilder.toString();
    }
}
