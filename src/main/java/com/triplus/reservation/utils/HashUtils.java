package com.triplus.reservation.utils;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

public class HashUtils {

    public static String getSignature(String oid, int price, long timestamp) {
        String params = "oid=" + oid + "&price=" + price + "&timestamp=" + timestamp;
        return getSHA_256(params);
    }

    public static String getAuthSignature(String authToken, long timestamp) {
        String params = "authToken=" + authToken + "&timestamp=" + timestamp;
        return getSHA_256(params);
    }

    public static String getMkey(String mid) {
        return getSHA_256(mid);
    }

    public static String getSHA_256(String input) {

        String toReturn = null;
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            digest.reset();
            digest.update(input.getBytes(StandardCharsets.UTF_8));
            toReturn = String.format("%064x", new BigInteger(1, digest.digest()));
        } catch (Exception e) {
            e.printStackTrace();
        }

        return toReturn;
    }

    public static String getSHA_512(String input) {

        String toReturn = null;
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-512");
            digest.reset();
            digest.update(input.getBytes(StandardCharsets.UTF_8));
            toReturn = String.format("%0128x", new BigInteger(1, digest.digest()));
        } catch (Exception e) {
            e.printStackTrace();
        }

        return toReturn;
    }

}
