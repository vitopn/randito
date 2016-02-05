package org.randito;

abstract class HasValue<T extends Comparable<T>> extends HasXValues<T>{
    abstract T getValue();

    public int getNumValues(){
        return 1;
    }

    T getValue(int index){
        return getValue();
    }

    public T initAndGetValue() {
        return initAndGetValue(0);
    }

    public T validateAndReturnValue() {
        return validateAndReturnValue(0);
    }
}
