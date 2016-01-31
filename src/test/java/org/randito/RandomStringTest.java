package org.randito;

import org.junit.Assert;
import org.junit.Test;

public class RandomStringTest {
    public static final int SOMEWHAT_RANDOM_MAX_ATTEMPT_COUNT = 10;

    @Test
    public void test_notARandomStringIsNotSet() throws Exception {
        HasNonRandomString target = new HasNonRandomString();
        RanditoAnnotations.init(target);
        Assert.assertNull(target.value);
    }

    @Test
    public void test_randomStringIsSet() throws Exception {
        HasRandomString target = new HasRandomString();
        RanditoAnnotations.init(target);
        Assert.assertNotNull(target.theValue);
    }

    @Test
    public void test_randomString_startsWith_propertyName(){
        HasRandomString target = new HasRandomString();
        RanditoAnnotations.init(target);
        Assert.assertTrue(target.theValue.startsWith("theValue"));
    }

    @Test
    public void test_randomString_isSomeWhatRandom(){
        HasRandomString target = new HasRandomString();
        RanditoAnnotations.init(target);
        String firstRandomValue =  target.theValue;
        for(int attemptNumber = 1; attemptNumber < SOMEWHAT_RANDOM_MAX_ATTEMPT_COUNT; attemptNumber++){
            RanditoAnnotations.init(target);
            if(firstRandomValue.compareTo(target.theValue) != 0){
                return;
            }
        }
        Assert.fail(String.format("Value hasn't changed in %d attempts", SOMEWHAT_RANDOM_MAX_ATTEMPT_COUNT));
    }

    @Test
    public void test_upperCaseRandomString_upperCase(){
        HasUpperCaseRandomString target = new HasUpperCaseRandomString();
        RanditoAnnotations.init(target);
        Assert.assertEquals(target.theValue.toUpperCase(), target.theValue);
    }

    @Test
    public void test_lowerCaseRandomString_isLowerCase(){
        HasLowerCaseRandomString target = new HasLowerCaseRandomString();
        RanditoAnnotations.init(target);
        Assert.assertEquals(target.theValue.toLowerCase(), target.theValue);
    }

    @Test
    public void test_hasRandomString_isMixedCase(){
        HasRandomString target = new HasRandomString();
        RanditoAnnotations.init(target);
        isMixedCase(target.theValue);
    }

    @Test
    public void test_HasRandomStringCaseChangeNone_isMixedCase(){
        HasRandomStringCaseChangeNone target = new HasRandomStringCaseChangeNone();
        RanditoAnnotations.init(target);
        isMixedCase(target.theValue);
    }

    private void isMixedCase(String value) {
        Assert.assertNotEquals(value.toLowerCase(), value);
        Assert.assertNotEquals(value.toUpperCase(), value);
    }

    class HasNonRandomString {
        String value;
    }
    class HasRandomString{
        @Randito
        String theValue;
    }
    class HasUpperCaseRandomString {
        @Randito(caseChange = Randito.CASE_CHANGE.UPPER)
        String theValue;
    }
    class HasLowerCaseRandomString {
        @Randito(caseChange = Randito.CASE_CHANGE.LOWER)
        String theValue;
    }
    class HasRandomStringCaseChangeNone {
        @Randito(caseChange = Randito.CASE_CHANGE.NONE)
        String theValue;
    }
}
