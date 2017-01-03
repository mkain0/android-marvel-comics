package com.mkain.marvelcomics.network.helpers;

import android.util.Log;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class HashGenerator {

    private static final String ALGORITHM = "MD5";
    private static final String TAG = "HashGenerator";
    private static final String MESSAGE = "Cannot generate hash";

    public static String generate(String timestamp, String publicKey, String privateKey) {
        StringBuilder md5 = new StringBuilder();
        try {
            String value = timestamp + privateKey + publicKey;
            MessageDigest md5Encoder = MessageDigest.getInstance(ALGORITHM);
            byte[] md5Bytes = md5Encoder.digest(value.getBytes());

            for (int i = 0; i < md5Bytes.length; ++i) {
                md5.append(Integer.toHexString((md5Bytes[i] & 0xFF) | 0x100).substring(1, 3));
            }
        } catch (NoSuchAlgorithmException e) {
            Log.w(TAG, MESSAGE, e);
        }
        return md5.toString();
    }
}
