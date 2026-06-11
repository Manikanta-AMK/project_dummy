package com.GBHSR.utilites;

import java.util.Base64;

public class EncryptionUtil {

    public static String encrypt(String value, String key) {

        return Base64.getEncoder().encodeToString(value.getBytes());
    }

    public static String decrypt(String encryptedValue, String key) {

        return new String(Base64.getDecoder().decode(encryptedValue));
    }
}