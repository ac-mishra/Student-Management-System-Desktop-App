package org.example;

import org.example.util.PasswordUtil;

public class TestPassword {

    public static void main(String[] args) {

        System.out.println(
                PasswordUtil.hashPassword("admin123")
        );

    }

}
