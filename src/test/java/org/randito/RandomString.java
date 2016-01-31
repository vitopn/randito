package org.randito;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class RandomString {
    public static final int SOMEWHAT_RANDOM_MAX_ATTEMPT_COUNT = 10;
    @Randito
    String randomString;

    String notARandomString;

    @Before
    public void setUp() throws Exception {
        RanditoAnnotations.init(this);
    }

    @Test
    public void test_randomStringIsSet() throws Exception {
        Assert.assertNotNull(randomString);
    }

    @Test
    public void test_notARandomStringIsNotSet() throws Exception {
        RanditoAnnotations.init(this);
        Assert.assertNull(notARandomString);
    }

    @Test
    public void test_randomString_startsWith_randomString(){
        Assert.assertTrue(randomString.startsWith("randomString"));
    }

    @Test
    public void test_randomString_someWhatRandom(){
        String firstRandomValue = randomString;
        for(int attemptNumber = 1; attemptNumber < SOMEWHAT_RANDOM_MAX_ATTEMPT_COUNT; attemptNumber++){
            RanditoAnnotations.init(this);
            if(firstRandomValue.compareTo(randomString) != 0){
                return;
            }
        }
        Assert.fail(String.format("Value hasn't changed in %d attempts", SOMEWHAT_RANDOM_MAX_ATTEMPT_COUNT));
    }

}
