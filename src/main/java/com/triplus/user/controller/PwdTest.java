package com.triplus.user.controller;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class PwdTest {
    public static void main(String[] args) {
        try {
            String raw = "123";
            MessageDigest md = MessageDigest.getInstance("SHA-512");
            md.update(raw.getBytes());
            String hex = String.format("%0128x", new BigInteger(1, md.digest()));
            System.out.println("hex:" + hex);

            
        } catch (NoSuchAlgorithmException e) {
            System.out.println(e);
        }
    }
}
