package com.andrei.laboratory_tracking_api.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;

import java.util.Random;
import java.util.logging.Level;


public class TokenGenerator {


    private static final int TOKEN_SIZE = 128;

    public static String generateToken() {
        int leftLimit = 48;
        int rightLimit = 122;

        Random random = new Random();
        return random.ints(leftLimit, rightLimit + 1)
                .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                .limit(TOKEN_SIZE)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
    }

}
