package org.randito;

import org.junit.Test;

public class RandomLongPrimitiveTest {

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
        @Rand
        private long theValue;

        @Override
        Long getValue() {
            return theValue;
        }
    }

    class HasRandomLongMaxLong extends HasValue<Long>{
        @Rand(maxLongExclusive = Long.MIN_VALUE + 3)
        private long theValue;

        @Override
        Long getValue() {
            return theValue;
        }
    }

    class HasRandomLongMinLong extends HasValue<Long>{
        @Rand(minLongInclusive = Long.MAX_VALUE - 3)
        private long theValue;

        @Override
        Long getValue() {
            return theValue;
        }
    }

    class HasRandomLongMinAndMaxLong extends HasValue<Long>{
        @Rand(minLongInclusive = -1, maxLongExclusive = 1)
        private long theValue;

        @Override
        Long getValue() {
            return theValue;
        }
    }

}
