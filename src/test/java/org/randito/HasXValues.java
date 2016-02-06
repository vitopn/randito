package org.randito;

import org.hamcrest.Matchers;
import org.junit.Assert;

import java.util.HashSet;
import java.util.Set;

public abstract class HasXValues<T extends Comparable<T>> {
    private static final int SOMEWHAT_RANDOM_MAX_ATTEMPT_COUNT = 50;
    private static final int RANGE_CHECK_MAX_ATTEMPT_COUNT = 1000;
    private static final int UNIQUE_CHECK_MAX_ATTEMPT_COUNT = 100;


    abstract T getValue(int index);
    abstract int getNumValues();
    protected boolean shouldValidateUniqueness() {
        return true;
    }

    private T maxValue = null;
    private T minValue = null;


    public T initAndGetValue(int index){
        initValues();
        return getValue(index);
    }

    private void initValues() {
        RanditoAnnotations.initRands(this);
    }

    public void validate(){
        initValues();
        for(int i = 0; i < getNumValues(); i++){
            validateSomeWhatRandom(i);
            validateRange(i);
        }
        validateUniqueness();
    }

    private void validateUniqueness() {
        for(int i = 0; i < UNIQUE_CHECK_MAX_ATTEMPT_COUNT; i++) {
            initValues();
            validateCurrentValueUniqueness();
        }
    }

    private void validateCurrentValueUniqueness() {
        if(!shouldValidateUniqueness()){
            return;
        }
        Set<T> values = new HashSet<T>();
        for(int i = 0; i < getNumValues(); i++){
            T currentValue = getValue(i);
            Assert.assertFalse("Duplicate value=" + currentValue, values.contains(currentValue));
            values.add(currentValue);
        }
    }

    private void validateRange(int index) {
        for(int attemptNumber = 0; attemptNumber <  RANGE_CHECK_MAX_ATTEMPT_COUNT; attemptNumber++){
            T value = initAndGetValue(index);
            if(minValue != null){
                Assert.assertThat(value, Matchers.greaterThanOrEqualTo(minValue));
            }
            if(maxValue != null){
                Assert.assertThat(value, Matchers.lessThan(maxValue));
            }
        }
    }

    private void validateSomeWhatRandom(int index) {
        T firstRandomValue =  getValue(index);
        for(int attemptNumber = 0; attemptNumber < SOMEWHAT_RANDOM_MAX_ATTEMPT_COUNT; attemptNumber++){
            if(firstRandomValue.compareTo(initAndGetValue(index)) != 0){
                return;
            }
        }
        Assert.fail(String.format("Value hasn't changed in %d attempts", SOMEWHAT_RANDOM_MAX_ATTEMPT_COUNT));
    }

    public T validateAndReturnValue(int index){
        validate();
        return getValue(index);
    }

    public HasXValues<T> setMaxValue(T maxValue) {
        this.maxValue = maxValue;
        return this;
    }

    public HasXValues<T> setMinValue(T minValue) {
        this.minValue = minValue;
        return this;
    }}
