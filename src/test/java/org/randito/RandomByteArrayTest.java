package org.randito;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

public class RandomByteArrayTest {
    @Test
    public void testIt(){
        new HasRandomByteArray().validate();
    }

    private class HasRandomByteArray {
        @Rand
        private byte [] value;

        byte [] getValue() {
            return value;
        }

        void validate() {
            byte [] firstRandomValue = initAndGetValye();
            for(int attemptNumber = 0; attemptNumber < HasXValues.SOMEWHAT_RANDOM_MAX_ATTEMPT_COUNT; attemptNumber++){
                if(!Arrays.equals(firstRandomValue, initAndGetValye())){
                    return;
                }
            }
            Assert.fail(String.format("Value hasn't changed in %d attempts",
                    HasXValues.SOMEWHAT_RANDOM_MAX_ATTEMPT_COUNT));
        }

        private byte [] initAndGetValye() {
            RanditoAnnotations.initRands(this);
            byte [] firstRandomValue = getValue();
            Assert.assertNotNull(getValue());
            return firstRandomValue;
        }
    }

}
