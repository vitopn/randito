package org.randito;

import java.util.concurrent.ThreadLocalRandom;

@SuppressWarnings("WeakerAccess") // used externally
public class RandomGeneratorUtil {
    public String generateRandomString(String prefix) {
        return prefix + Integer.toString(generateRandomInt());
    }

    public int generateRandomInt() {
        return ThreadLocalRandom.current().nextInt();
    }

    public int generateRandomInt(int minInclusive, int maxExclusive) {
        return ThreadLocalRandom.current().nextInt(minInclusive, maxExclusive);
    }

    public long generateRandomLong(long minInclusive, long maxExclusive) {
        return ThreadLocalRandom.current().nextLong(minInclusive, maxExclusive);
    }
}
