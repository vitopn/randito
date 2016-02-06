package org.randito;

import org.junit.Test;

public class RandomEnumTest {
    @SuppressWarnings("unused")
    public enum TEST_ENUM{
        VALUE01,
        VALUE02
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


    class HasRandomEnum extends HasValue<TEST_ENUM>{
        @Rand
        private TEST_ENUM value;

        @Override
        TEST_ENUM getValue() {
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
