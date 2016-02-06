package org.randito;

import java.lang.reflect.Field;

public class FailedToFindUnexcludedValueForFieldException extends RuntimeException {
    public FailedToFindUnexcludedValueForFieldException(Field field) {
        super("Failed to find an value that was not excluded for field=" + field.getName());
    }
}
