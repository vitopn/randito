package org.randito;

import org.junit.Assert;

abstract class HasValue<T extends Comparable<T>>{
    abstract T getValue();

    public T initAndGetValue(){
        RanditoAnnotations.init(this);
        return getValue();
    }

    public void validateSomewhatRandom(){
        RanditoAnnotations.init(this);
        T firstRandomValue =  getValue();
        for(int attemptNumber = 1; attemptNumber < RandomStringTest.SOMEWHAT_RANDOM_MAX_ATTEMPT_COUNT; attemptNumber++){
            RanditoAnnotations.init(this);
            if(firstRandomValue.compareTo(getValue()) != 0){
                return;
            }
        }
        Assert.fail(String.format("Value hasn't changed in %d attempts", RandomStringTest.SOMEWHAT_RANDOM_MAX_ATTEMPT_COUNT));
    }

    public T validateSomewhatRandomAndGetValue(){
        validateSomewhatRandom();
        return getValue();
    }
}
