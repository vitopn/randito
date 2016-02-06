package org.randito;

import org.junit.Test;

public abstract class BaseNumberTest {
    public enum TEST_CLASS_TYPE{
        SINGLE_NO_RANGE,
        SINGLE_MIN_IS_MAX_MINUS_3,
        SINGLE_MAX_IS_MIN_PLUS_3,
        SINGLE_MIN_MAX_TWO_APART,
        MULTIPLE_MORE_FIELDS_THAN_VALUES,
        MULTIPLE_MIN_MAX_TWO_APART
    }

    abstract HasXValues getTargetInstance(TEST_CLASS_TYPE targetType);

    @Test
    public void testIt(){
        getTargetInstance(TEST_CLASS_TYPE.SINGLE_NO_RANGE).validate();
    }

    @Test
    public void test_maxInt(){
        getTargetInstance(TEST_CLASS_TYPE.SINGLE_MAX_IS_MIN_PLUS_3).validate();
    }

    @Test
    public void test_minInt(){
        getTargetInstance(TEST_CLASS_TYPE.SINGLE_MIN_IS_MAX_MINUS_3).validate();
    }

    @Test
    public void test_minAndMaxInt(){
        getTargetInstance(TEST_CLASS_TYPE.SINGLE_MIN_MAX_TWO_APART).validate();
    }

    @Test
    public void test_multipleInts(){
        getTargetInstance(TEST_CLASS_TYPE.MULTIPLE_MIN_MAX_TWO_APART).validate();
    }

    @Test
    public void test_multipleIntsMoreFieldsThanValues(){
        getTargetInstance(TEST_CLASS_TYPE.MULTIPLE_MORE_FIELDS_THAN_VALUES).validate();
    }

}
