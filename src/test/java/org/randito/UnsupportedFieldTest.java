package org.randito;

import org.junit.Test;

public class UnsupportedFieldTest {
    @Rand
    SomeClass value;

    @Test(expected = UnsupportedFieldTypeException.class)
    public void testIt(){
        RanditoAnnotations.initRands(this);
    }


    private class SomeClass{

    }
}
