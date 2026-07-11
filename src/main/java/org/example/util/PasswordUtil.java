package org.example.util;

import java.security.MessageDigest;

public class PasswordUtil {

    private PasswordUtil() {
    }

    public static String encrypt(
            String password
    ) {

        try {

            MessageDigest md =
                    MessageDigest.getInstance("SHA-256");

            byte[] hash =
                    md.digest(
                            password.getBytes()
                    );

            StringBuilder builder =
                    new StringBuilder();

            for (byte b : hash) {

                builder.append(
                        String.format(
                                "%02x",
                                b
                        )
                );

            }

            return builder.toString();

        } catch (Exception e) {

            e.printStackTrace();

        }

        return password;

    }

}