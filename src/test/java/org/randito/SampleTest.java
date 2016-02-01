package org.randito;

import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class SampleTest {
    @Randito
    String randomString;

    @Randito(minIntInclusive = 13, maxIntExclusive = 45)
    int randomInt;

    @Before
    public void setUp() throws Exception {
        RanditoAnnotations.init(this);
    }

    @Test
    public void test_randomInt() {
        Assert.assertThat(randomInt, Matchers.greaterThanOrEqualTo(13));
        Assert.assertThat(randomInt, Matchers.lessThan(45));
    }

    @Test
    public void test_randomString(){
        Assert.assertThat(randomString, Matchers.startsWith("randomString"));
        Assert.assertNotEquals("randomString", randomString);
    }
}
