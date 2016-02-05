package org.randito;

public class RandomLongTest extends BaseNumberTest{


    @SuppressWarnings("Duplicates")
    @Override
    HasXValues getTargetInstance(BaseNumberTest.TEST_CLASS_TYPE targetType) {
        switch(targetType){
            case SINGLE_NO_RANGE:
                return new HasRandomLong();
            case SINGLE_MAX_IS_MIN_PLUS_3:
                return new HasRandomLongMaxLong().setMinValue(Long.MIN_VALUE).setMaxValue(Long.MIN_VALUE + 3);
            case SINGLE_MIN_IS_MAX_MINUS_3:
                new HasRandomLongMinLong().setMinValue(Long.MAX_VALUE - 3);
            case SINGLE_MIN_MAX_TWO_APART:
                return new HasRandomLongMinAndMaxLong().setMinValue(-1L).setMaxValue(1L);
            case MULTIPLE_MIN_MAX_TWO_APART:
                return new HasTwoRandomLongs().setMinValue(-1L).setMaxValue(1L);
        }
        return null;
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
