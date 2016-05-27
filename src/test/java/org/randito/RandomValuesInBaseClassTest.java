package org.randito;

import org.junit.Assert;
import org.junit.Test;

public class RandomValuesInBaseClassTest {

    @Test
    public void testIt(){
        DataClass target = new DataClass();
        RanditoAnnotations.initRands(target);
        Assert.assertNotNull(target.getValue());
    }
    class DataSubClass {
        @Rand
        private String value;

        public String getValue() {
            return value;
        }
    }
    private class DataClass extends DataSubClass{

    }
}
