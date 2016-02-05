package org.randito;

import org.junit.Test;

public class UnsupportFieldTest {
    @Rand
    Double value;

    @Test(expected = UnsupportedFieldTypeException.class)
    public void testIt(){
        RanditoAnnotations.initRands(this);
    }
}
