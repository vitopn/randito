package org.randito;

import org.bson.types.ObjectId;
import org.junit.Test;

public class ObjectIdTest {
    @Test
    public void testIt(){
        new HasRandomObjectId().validate();
    }

    class HasRandomObjectId extends HasValue<ObjectId>{
        @Rand
        private ObjectId value;

        @Override
        ObjectId getValue() {
            return value;
        }
    }
}
