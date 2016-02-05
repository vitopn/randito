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


    @Test
    public void test_multipleLongs() {
        new HasTwoRandomLongs().setMinValue(-1L).setMaxValue(1L).validate();
    }

    class HasRandomLong extends HasValue<Long>{
        @Rand
        private Long theValue;

        @Override
        Long getValue() {
            return theValue;
        }
    }

    class HasRandomLongMaxLong extends HasValue<Long>{
        @Rand(maxLongExclusive = Long.MIN_VALUE + 3)
        private Long theValue;

        @Override
        Long getValue() {
            return theValue;
        }
    }

    class HasRandomLongMinLong extends HasValue<Long>{
        @Rand(minLongInclusive = Long.MAX_VALUE - 3)
        private Long theValue;

        @Override
        Long getValue() {
            return theValue;
        }
    }

    class HasRandomLongMinAndMaxLong extends HasValue<Long>{
        @Rand(minLongInclusive = -1, maxLongExclusive = 1)
        private Long theValue;

        @Override
        Long getValue() {
            return theValue;
        }
    }

    class HasTwoRandomLongs extends HasXValues<Long> {
        @Rand(minLongInclusive = -1, maxLongExclusive = 1)
        private Long value1;
        @Rand(minLongInclusive = -1, maxLongExclusive = 1)
        private Long value2;

        @Override
        Long getValue(int index) {
            if (index == 0) {
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
