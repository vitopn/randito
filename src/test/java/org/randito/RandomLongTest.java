package org.randito;

import org.junit.Test;

public class RandomLongTest {

    @Test
    public void testIt(){
        new HasRandomLong().validate();
    }

    @Test
    public void test_maxLong(){
        new HasRandomLongMaxLong().setMinValue(Long.MIN_VALUE).setMaxValue(Long.MIN_VALUE + 3).validate();
    }

    @Test
    public void test_minLong(){
        new HasRandomLongMinLong().setMinValue(Long.MAX_VALUE - 3).validate();
    }

    @Test
    public void test_minAndMaxLong(){
        new HasRandomLongMinAndMaxLong().setMinValue(-1L).setMaxValue(1L).validate();
    }

    class HasRandomLong extends HasValue<Long>{
        @Randito
        private Long theValue;

        @Override
        Long getValue() {
            return theValue;
        }
    }

    class HasRandomLongMaxLong extends HasValue<Long>{
        @Randito(maxLongExclusive = Long.MIN_VALUE + 3)
        private Long theValue;

        @Override
        Long getValue() {
            return theValue;
        }
    }

    class HasRandomLongMinLong extends HasValue<Long>{
        @Randito(minLongInclusive = Long.MAX_VALUE - 3)
        private Long theValue;

        @Override
        Long getValue() {
            return theValue;
        }
    }

    class HasRandomLongMinAndMaxLong extends HasValue<Long>{
        @Randito(minLongInclusive = -1, maxLongExclusive = 1)
        private Long theValue;

        @Override
        Long getValue() {
            return theValue;
        }
    }

}
