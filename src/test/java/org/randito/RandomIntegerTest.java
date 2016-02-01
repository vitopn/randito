package org.randito;

import org.junit.Test;

public class RandomIntegerTest {

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

    class HasRandomInt extends HasValue<Integer>{
        @Randito
        private Integer theValue;

        @Override
        Integer getValue() {
            return theValue;
        }
    }

    class HasRandomIntMaxInt extends HasValue<Integer>{
        @Randito(maxIntExclusive = Integer.MIN_VALUE + 3)
        private Integer theValue;

        @Override
        Integer getValue() {
            return theValue;
        }
    }

    class HasRandomIntMinInt extends HasValue<Integer>{
        @Randito(minIntInclusive = Integer.MAX_VALUE - 3)
        private Integer theValue;

        @Override
        Integer getValue() {
            return theValue;
        }
    }

    class HasRandomIntMinAndMaxInt extends HasValue<Integer>{
        @Randito(minIntInclusive = -1, maxIntExclusive = 1)
        private Integer theValue;

        @Override
        Integer getValue() {
            return theValue;
        }
    }

}
