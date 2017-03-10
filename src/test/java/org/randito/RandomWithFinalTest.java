package org.randito;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class RandomWithFinalTest {
    private static final int START_INT_VALUE = 1;
    @SuppressWarnings("FieldCanBeLocal")
    private final int intValue = START_INT_VALUE;
    private String str;

    @Before
    public void setUp() throws Exception {
        new RandomizeObjectFields(this, false).execute();
    }

    @Test
    public void validateThatStringHasBeenSet() {
        Assert.assertNotNull(str);
    }

    @Test
    public void validateIntHasNotChanged() {
        Assert.assertEquals(START_INT_VALUE,  intValue);
    }

}
