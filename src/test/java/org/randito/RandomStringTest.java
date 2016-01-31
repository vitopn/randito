package org.randito;

import org.junit.Assert;
import org.junit.Test;

public class RandomStringTest {
    public static final int SOMEWHAT_RANDOM_MAX_ATTEMPT_COUNT = 10;

    @Test
    public void test_notARandomStringIsNotSet() throws Exception {
        Assert.assertNull(new HasNonRandomString().initAndGetValue());
    }

    @Test
    public void test_randomStringIsSet() throws Exception {
        Assert.assertNotNull(new HasRandomString().validateSomewhatRandomAndGetValue());
    }

    @Test
    public void test_randomString_startsWith_propertyName(){
        HasRandomString target = new HasRandomString();
        RanditoAnnotations.init(target);
        Assert.assertTrue(new HasRandomString().validateSomewhatRandomAndGetValue().startsWith("theValue"));
    }

    @Test
    public void test_upperCaseRandomString_upperCase(){
        String value = new HasUpperCaseRandomString().validateSomewhatRandomAndGetValue();
        Assert.assertEquals(value.toUpperCase(), value);
    }

    @Test
    public void test_lowerCaseRandomString_isLowerCase(){
        String value = new HasLowerCaseRandomString().validateSomewhatRandomAndGetValue();
        Assert.assertEquals(value.toLowerCase(), value);
    }

    @Test
    public void test_hasRandomString_isMixedCase(){
        isMixedCase(new HasRandomString().validateSomewhatRandomAndGetValue());
    }

    @Test
    public void test_HasRandomStringCaseChangeNone_isMixedCase(){
        isMixedCase(new HasRandomStringCaseChangeNone().validateSomewhatRandomAndGetValue());
    }

    private void isMixedCase(String value) {
        Assert.assertNotEquals(value.toLowerCase(), value);
        Assert.assertNotEquals(value.toUpperCase(), value);
    }

    class HasNonRandomString extends HasValue<String>{
        @SuppressWarnings("unused")
        private String theValue;

        @Override
        String getValue() {
            return theValue;
        }
    }
    class HasRandomString extends HasValue<String>{
        @Randito
        private String theValue;
        @Override
        String getValue() {
            return theValue;
        }
    }
    class HasUpperCaseRandomString extends HasValue<String>{
        @Randito(caseChange = Randito.CASE_CHANGE.UPPER)
        private String theValue;
        @Override
        String getValue() {
            return theValue;
        }
    }
    class HasLowerCaseRandomString extends HasValue<String>{
        @Randito(caseChange = Randito.CASE_CHANGE.LOWER)
        private String theValue;
        @Override
        String getValue() {
            return theValue;
        }
    }
    class HasRandomStringCaseChangeNone extends HasValue<String>{
        @Randito(caseChange = Randito.CASE_CHANGE.NONE)
        private String theValue;
        @Override
        String getValue() {
            return theValue;
        }
    }
}
