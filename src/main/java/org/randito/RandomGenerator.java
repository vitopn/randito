package org.randito;

import java.util.concurrent.ThreadLocalRandom;

public class RandomGenerator {
    public static String generateRandomString(String prefix) {
        return prefix + Integer.toString(generateRandomInt());
    }

    public static int generateRandomInt() {
        return ThreadLocalRandom.current().nextInt();
    }

    public static int generateRandomInt(int minInclusive, int maxExclusive) {
        return ThreadLocalRandom.current().nextInt(minInclusive, maxExclusive);
    }

    public static long generateRandomLong(long minInclusive, long maxExclusive) {
        return ThreadLocalRandom.current().nextLong(minInclusive, maxExclusive);
    }
}
