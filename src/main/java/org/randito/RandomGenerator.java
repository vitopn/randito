package org.randito;

import java.util.Random;

public class RandomGenerator {
    private static Random random = new Random();

    public static String generateRandomString(String prefix) {
        return prefix + Integer.toString(random.nextInt());
    }

}
