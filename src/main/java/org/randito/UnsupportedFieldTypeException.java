package org.randito;

public class UnsupportedFieldTypeException extends RuntimeException {
    public UnsupportedFieldTypeException(Class<?> fieldType) {
        super("Unsupported field type.  Type=" + fieldType.getName());
    }
}
