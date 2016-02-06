package org.randito;

import org.junit.Assert;
import org.junit.Test;

public class RandomEnumTest {
    @SuppressWarnings("unused")
    public enum TEST_ENUM{
        VALUE01,
        VALUE02
    }

    @SuppressWarnings("unused")
    public enum TEST_ENUM2{
        VALUE01,
        VALUE02,
        VALUE03,
        VALUE04
    }

    @Test
    public void testIt(){
        new HasRandomEnum().validate();
    }
    @Test
    public void testMultiple(){
        new HasTwoRandomEnums().validate();

    }
    @Test
    public void testMoreFieldsThanValues(){
        new HasMoreRandomEnumsThanEnumValues().validate();

    }

    @Test
    public void testExcludes(){
        for(int i = 0; i < 100;  i++) {
            TEST_ENUM2 value = new HasRandomEnumWithExcludes().validateAndReturnValue();
            Assert.assertNotEquals(TEST_ENUM2.VALUE03, value);
            Assert.assertNotEquals(TEST_ENUM2.VALUE04, value);
        }
    }


    class HasRandomEnum extends HasValue<TEST_ENUM>{
        @Rand
        private TEST_ENUM value;

        @Override
        TEST_ENUM getValue() {
            return value;
        }
    }

    class HasRandomEnumWithExcludes extends HasValue<TEST_ENUM2>{
        @Rand(excludeEnums = {"VALUE03", "VALUE04"})
        private TEST_ENUM2 value;

        @Override
        TEST_ENUM2 getValue() {
            return value;
        }
    }

    class HasTwoRandomEnums extends HasXValues<TEST_ENUM>{
        @Rand
        private TEST_ENUM value1;
        @Rand
        private TEST_ENUM value2;

        @Override
        TEST_ENUM getValue(int index) {
            if(index == 0){
                return value1;
            } else {
                return value2;
            }
        }

        @Override
        int getNumValues() {
            return 2;
        }
    }

    @SuppressWarnings("Duplicates")
    class HasMoreRandomEnumsThanEnumValues extends HasXValues<TEST_ENUM>{
        @Rand
        private TEST_ENUM value1;
        @Rand
        private TEST_ENUM value2;
        @Rand
        private TEST_ENUM value3;

        @Override
        protected boolean shouldValidateUniqueness() {
            return false;
        }

        @Override
        TEST_ENUM getValue(int index) {
            if(index == 0){
                return value1;
            } else if(index == 1){
                return value2;
            } else {
                return value3;
            }
        }

        @Override
        int getNumValues() {
            return 3;
        }
    }

}
