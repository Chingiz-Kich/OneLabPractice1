package com.example.onelabpractice1.helper;

import java.util.Random;

public class CardHelper {
    private static final Random random = new Random();

    private CardHelper() { }

    public static String getNumber() {
        int max = 9999;
        int min = 1000;
        StringBuilder number = new StringBuilder();
        for (int i = 0; i < 4; i++) {
            int randomInt = random.nextInt() *(max-min+1)+min;
            number.append(randomInt);
        }
        return number.toString();
    }
}
