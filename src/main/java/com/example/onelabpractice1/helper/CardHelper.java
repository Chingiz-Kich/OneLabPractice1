package com.example.onelabpractice1.helper;

public class CardHelper {
    public static String getNumber() {
        int max = 9999;
        int min = 1000;
        StringBuilder number = new StringBuilder();
        for (int i = 0; i < 4; i++) {
            int random_int = (int)Math.floor(Math.random()*(max-min+1)+min);
            number.append(random_int);
        }
        return number.toString();
    }
}
