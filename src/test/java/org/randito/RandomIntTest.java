package org.randito;

import org.junit.Test;

public class RandomIntTest {

    @Test
    public void testIt(){
        new HasRandomInt().validate();
    }

    @Test
    public void test_maxInt(){
        new HasRandomIntMaxInt().setMinValue(Integer.MIN_VALUE).setMaxValue(Integer.MIN_VALUE + 3).validate();
    }

    @Test
    public void test_minInt(){
        new HasRandomIntMinInt().setMinValue(Integer.MAX_VALUE - 3).validate();
    }

    @Test
    public void test_minAndMaxInt(){
        new HasRandomIntMinAndMaxInt().setMinValue(-1).setMaxValue(1).validate();
    }

    @Test
    public void test_multipleInts(){
        new HasTwoRandomInts().setMinValue(-1).setMaxValue(1).validate();
    }

    class HasRandomInt extends HasValue<Integer>{
        @Rand
        private int theValue;

        @Override
        Integer getValue() {
            return theValue;
        }
    }

    class HasRandomIntMaxInt extends HasValue<Integer>{
        @Rand(maxIntExclusive = Integer.MIN_VALUE + 3)
        private int theValue;

        @Override
        Integer getValue() {
            return theValue;
        }
    }

    class HasRandomIntMinInt extends HasValue<Integer>{
        @Rand(minIntInclusive = Integer.MAX_VALUE - 3)
        private int theValue;

        @Override
        Integer getValue() {
            return theValue;
        }
    }

    class HasRandomIntMinAndMaxInt extends HasValue<Integer>{
        @Rand(minIntInclusive = -1, maxIntExclusive = 1)
        private int theValue;

        @Override
        Integer getValue() {
            return theValue;
        }
    }

    class HasTwoRandomInts extends HasXValues<Integer>{
        @Rand(minIntInclusive = -1, maxIntExclusive = 1)
        private int value1;
        @Rand(minIntInclusive = -1, maxIntExclusive = 1)
        private int value2;

        @Override
        Integer getValue(int index) {
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


}
