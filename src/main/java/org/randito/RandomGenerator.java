package org.randito;

import java.util.concurrent.ThreadLocalRandom;

public class RandomGenerator {
    public static String generateRandomString(String prefix) {
        return prefix + Integer.toString(generateRandomInt());
    }

    public static int generateRandomInt() {
        return ThreadLocalRandom.current().nextInt();
    }

    public static int generateRandomInt(int minInt, int maxInt) {
        return ThreadLocalRandom.current().nextInt(minInt, maxInt);
    }

}
