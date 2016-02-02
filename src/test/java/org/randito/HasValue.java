package org.randito;

import org.hamcrest.Matchers;
import org.junit.Assert;

abstract class HasValue<T extends Comparable<T>>{
    public static final int SOMEWHAT_RANDOM_MAX_ATTEMPT_COUNT = 10;
    private static final int RANGE_CHECK_MAX_ATTEMPT_COUNT = 1000;

    private T maxValue = null;
    private T minValue = null;

    abstract T getValue();

    public T initAndGetValue(){
        RanditoAnnotations.initRands(this);
        return getValue();
    }

    public void validate(){
        RanditoAnnotations.initRands(this);
        validateSomeWhatRandom();
        validateRange();
    }

    private void validateRange() {
        for(int attemptNumber = 0; attemptNumber <  RANGE_CHECK_MAX_ATTEMPT_COUNT; attemptNumber++){
            T value = initAndGetValue();
            if(minValue != null){
                Assert.assertThat(value, Matchers.greaterThanOrEqualTo(minValue));
            }
            if(maxValue != null){
                Assert.assertThat(value, Matchers.lessThan(maxValue));
            }
        }
    }

    private void validateSomeWhatRandom() {
        T firstRandomValue =  getValue();
        for(int attemptNumber = 0; attemptNumber < SOMEWHAT_RANDOM_MAX_ATTEMPT_COUNT; attemptNumber++){
            if(firstRandomValue.compareTo(initAndGetValue()) != 0){
                return;
            }
        }
        Assert.fail(String.format("Value hasn't changed in %d attempts", SOMEWHAT_RANDOM_MAX_ATTEMPT_COUNT));
    }

    public T validateAndReturnValue(){
        validate();
        return getValue();
    }

    public HasValue setMaxValue(T maxValue) {
        this.maxValue = maxValue;
        return this;
    }

    public HasValue<T> setMinValue(T minValue) {
        this.minValue = minValue;
        return this;
    }
}
