package org.randito;

public class RandomIntegerTest extends BaseNumberTest{


    @Override
    HasXValues getTargetInstance(TEST_CLASS_TYPE targetType) {
        switch(targetType){
            case SINGLE_NO_RANGE:
                return new HasRandomInt();
            case SINGLE_MAX_IS_MIN_PLUS_3:
                return new HasRandomIntMaxInt().setMinValue(Integer.MIN_VALUE).setMaxValue(Integer.MIN_VALUE + 3);
            case SINGLE_MIN_IS_MAX_MINUS_3:
                new HasRandomIntMinInt().setMinValue(Integer.MAX_VALUE - 3);
            case SINGLE_MIN_MAX_TWO_APART:
                return new HasRandomIntMinAndMaxInt().setMinValue(-1).setMaxValue(1);
            case MULTIPLE_MIN_MAX_TWO_APART:
                return new HasTwoRandomInts().setMinValue(-1).setMaxValue(1);
        }
        return null;
    }

    class HasRandomInt extends HasValue<Integer>{
        @Rand
        private Integer theValue;

        @Override
        Integer getValue() {
            return theValue;
        }
    }

    class HasRandomIntMaxInt extends HasValue<Integer>{
        @Rand(maxIntExclusive = Integer.MIN_VALUE + 3)
        private Integer theValue;

        @Override
        Integer getValue() {
            return theValue;
        }
    }

    class HasRandomIntMinInt extends HasValue<Integer>{
        @Rand(minIntInclusive = Integer.MAX_VALUE - 3)
        private Integer theValue;

        @Override
        Integer getValue() {
            return theValue;
        }
    }

    class HasRandomIntMinAndMaxInt extends HasValue<Integer>{
        @Rand(minIntInclusive = -1, maxIntExclusive = 1)
        private Integer theValue;

        @Override
        Integer getValue() {
            return theValue;
        }
    }

    class HasTwoRandomInts extends  HasXValues<Integer>{
        @Rand(minIntInclusive = -1, maxIntExclusive = 1)
        private Integer value1;
        @Rand(minIntInclusive = -1, maxIntExclusive = 1)
        private Integer value2;

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
