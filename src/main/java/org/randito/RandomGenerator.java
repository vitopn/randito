package org.randito;

@SuppressWarnings("WeakerAccess") // used externally
public class RandomGenerator {
    private static RandomGeneratorUtil randomGeneratorUtil = new RandomGeneratorUtil();

    public RandomGeneratorUtil getRandomGeneratorUtil() {
        return randomGeneratorUtil;
    }

    public static String generateRandomString(String prefix) {
        return randomGeneratorUtil.generateRandomString(prefix);
    }

    public static int generateRandomInt() {
        return randomGeneratorUtil.generateRandomInt();
    }

    public static int generateRandomInt(int minInclusive, int maxExclusive) {
        return randomGeneratorUtil.generateRandomInt(minInclusive, maxExclusive);
    }

    public static long generateRandomLong(long minInclusive, long maxExclusive) {
        return randomGeneratorUtil.generateRandomLong(minInclusive, maxExclusive);
    }


    public static double generateRandomDouble(double minInclusive, double maxExclusive) {
        return randomGeneratorUtil.generateRandomDouble(minInclusive, maxExclusive);
    }
}
