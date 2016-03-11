package org.randito;

import org.junit.Test;

public class RandomBooleanPrimitiveTest {

    @Test
    public void testIt(){
        new HasRandomBoolean().validate();
    }
    @Test
    public void testMultiple(){
        new HasTwoRandomBooleans().validate();

    }
    @Test
    public void testMoreFieldsThanValues(){
        new HasMoreThanTwoRandomBooleans().validate();

    }

    class HasRandomBoolean extends HasValue<Boolean>{
        @Rand
        private boolean value;

        @Override
        Boolean getValue() {
            return value;
        }
    }

    class HasTwoRandomBooleans extends HasXValues<Boolean>{
        @Rand
        private boolean value1;
        @Rand
        private boolean value2;

        @Override
        Boolean getValue(int index) {
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
    class HasMoreThanTwoRandomBooleans extends HasXValues<Boolean>{
        @Rand
        private boolean value1;
        @Rand
        private boolean value2;
        @Rand
        private boolean value3;

        @Override
        protected boolean shouldValidateUniqueness() {
            return false;
        }

        @Override
        Boolean getValue(int index) {
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
