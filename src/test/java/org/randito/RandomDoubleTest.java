package org.randito;

public class RandomDoubleTest extends BaseNumberTest{


    @SuppressWarnings("Duplicates")
    @Override
    HasXValues getTargetInstance(TEST_CLASS_TYPE targetType) {
        switch(targetType){
            case SINGLE_NO_RANGE:
                return new HasRandomDouble();
            case SINGLE_MAX_IS_MIN_PLUS_3:
                return new HasRandomDoubleMaxDouble().setMinValue(Double.MIN_VALUE).setMaxValue(Double.MIN_VALUE + 3);
            case SINGLE_MIN_IS_MAX_MINUS_3:
                new HasRandomDoubleMinDouble().setMinValue(Double.MAX_VALUE - .00000003);
            case SINGLE_MIN_MAX_TWO_APART:
                return new HasRandomDoubleMinAndMaxDouble().setMinValue(-1.0).setMaxValue(1.0);
            case MULTIPLE_MIN_MAX_TWO_APART:
                return new HasTwoRandomDouble().setMinValue(-1.0).setMaxValue(1.0);
            case MULTIPLE_MORE_FIELDS_THAN_VALUES:
                return new HasThreeRandomDouble().setMinValue(-1.0).setMaxValue(1.0);
        }
        return null;
    }

    private class HasRandomDouble extends HasValue<Double>{
        @Rand
        private Double theValue;

        @Override
        Double getValue() {
            return theValue;
        }
    }

    private class HasRandomDoubleMaxDouble extends HasValue<Double>{
        @Rand(maxDoubleExclusive = Double.MIN_VALUE + 3)
        private Double theValue;

        @Override
        Double getValue() {
            return theValue;
        }
    }

    private class HasRandomDoubleMinDouble extends HasValue<Double>{
        @Rand(minDoubleInclusive = Double.MAX_VALUE - 3)
        private Double theValue;

        @Override
        Double getValue() {
            return theValue;
        }
    }

    private class HasRandomDoubleMinAndMaxDouble extends HasValue<Double>{
        @Rand(minDoubleInclusive = -1, maxDoubleExclusive = 1)
        private Double theValue;

        @Override
        Double getValue() {
            return theValue;
        }
    }

    private class HasTwoRandomDouble extends HasXValues<Double> {
        @Rand(minDoubleInclusive = -1, maxDoubleExclusive = 1)
        private Double value1;
        @Rand(minDoubleInclusive = -1, maxDoubleExclusive = 1)
        private Double value2;

        @Override
        Double getValue(int index) {
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

    @SuppressWarnings("Duplicates")
    private class HasThreeRandomDouble extends  HasXValues<Double>{
        @Rand(minDoubleInclusive = -1, maxDoubleExclusive= 1)
        private double value1;
        @Rand(minDoubleInclusive = -1, maxDoubleExclusive= 1)
        private double value2;
        @Rand(minDoubleInclusive = -1, maxDoubleExclusive= 1)
        private double value3;

        @Override
        protected boolean shouldValidateUniqueness() {
            return false;
        }

        @Override
        Double getValue(int index) {
            if(index == 0){
                return value1;
            } else if(index == 1){
                return value2;
            } else {
                return value2;
            }
        }

        @Override
        int getNumValues() {
            return 3;
        }
    }

}
