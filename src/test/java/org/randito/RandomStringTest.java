package org.randito;

import org.junit.Assert;
import org.junit.Test;

public class RandomStringTest {
    @Test
    public void test_notARandomStringIsNotSet() throws Exception {
        Assert.assertNull(new HasNonRandomString().initAndGetValue());
    }

    @Test
    public void test_randomStringIsSet() throws Exception {
        Assert.assertNotNull(new HasRandomString().validateAndReturnValue());
    }

    @Test
    public void test_randomString_startsWith_propertyName(){
        HasRandomString target = new HasRandomString();
        RanditoAnnotations.initRands(target);
        Assert.assertTrue(new HasRandomString().validateAndReturnValue().startsWith("theValue"));
    }

    @Test
    public void test_upperCaseRandomString_upperCase(){
        String value = new HasUpperCaseRandomString().validateAndReturnValue();
        Assert.assertEquals(value.toUpperCase(), value);
    }

    @Test
    public void test_lowerCaseRandomString_isLowerCase(){
        String value = new HasLowerCaseRandomString().validateAndReturnValue();
        Assert.assertEquals(value.toLowerCase(), value);
    }

    @Test
    public void test_hasRandomString_isMixedCase(){
        isMixedCase(new HasRandomString().validateAndReturnValue());
    }

    @Test
    public void test_HasRandomStringCaseChangeNone_isMixedCase(){
        isMixedCase(new HasRandomStringCaseChangeNone().validateAndReturnValue());
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
        @Rand
        private String theValue;
        @Override
        String getValue() {
            return theValue;
        }
    }
    class HasUpperCaseRandomString extends HasValue<String>{
        @Rand(caseChange = Rand.CASE_CHANGE.UPPER)
        private String theValue;
        @Override
        String getValue() {
            return theValue;
        }
    }
    class HasLowerCaseRandomString extends HasValue<String>{
        @Rand(caseChange = Rand.CASE_CHANGE.LOWER)
        private String theValue;
        @Override
        String getValue() {
            return theValue;
        }
    }
    class HasRandomStringCaseChangeNone extends HasValue<String>{
        @Rand(caseChange = Rand.CASE_CHANGE.NONE)
        private String theValue;
        @Override
        String getValue() {
            return theValue;
        }
    }
}
