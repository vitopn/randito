package org.randito;

import org.mockito.internal.util.reflection.FieldSetter;

import java.lang.reflect.Field;
import java.util.*;

@SuppressWarnings("WeakerAccess")
public class RanditoAnnotations {

    private static final int MAX_UNIQUE_ATTEMPT = 50;
    private static final int MAX_EXCLUDES_RETRY = 50;
    private final Object target;
    private Map<Class,Set<Object>> values;

    public RanditoAnnotations(Object target) {
        this.target = target;
        values = new HashMap<>();
    }

    @SuppressWarnings("WeakerAccess")
    public static void initRands(Object target) {
        new RanditoAnnotations(target).execute();
    }

    private void execute() {
        processTargetClass(target.getClass());
    }

    private void processTargetClass(Class<?> targetClass) {
        if(targetClass == null){
            return;
        }
        Field[] fields = targetClass.getDeclaredFields();
        for(Field field : fields){
            processField(target, field);
        }
        processTargetClass(targetClass.getSuperclass());
    }


    private void processField(Object target, Field field) {
        Class<Rand> randomClass = Rand.class;
        Rand annotation = field.getAnnotation(randomClass);
        if(annotation == null){
            return;
        }

        Class<?> fieldType = field.getType();
        FieldSetter fieldSetter = new FieldSetter(target, field);
        Object value = null;
        for(int attempt = 0; attempt < MAX_UNIQUE_ATTEMPT; attempt++) {
            value = generateValue(annotation, field, fieldType);
            if(isUnique(fieldType, value)){
                setValue(fieldSetter, fieldType, value);
                return;
            }
        }
        setValue(fieldSetter, fieldType, value);
    }

    private Object generateValue(Rand annotation, Field field, Class<?> fieldType) {
        Object value;
        byte [] bytes = new byte[1];
        Class byteArrayClass = bytes.getClass();
        if (fieldType == String.class) {
            value = generateString(field, annotation);
        } else if ((fieldType == int.class) || (fieldType == Integer.class)) {
            value = generateInt(annotation);
        } else if ((fieldType == long.class) || (fieldType == Long.class)) {
            value = generateLong(annotation);
        } else if (fieldType.isEnum()) {
            value = generateEnumValue(annotation, field);
        } else if (fieldType == Boolean.class || (fieldType == boolean.class)) {
            value = generateBooleanValue();
        } else if (fieldType == byteArrayClass){
            value = generateByteArray(field, annotation);
        } else {
            throw new UnsupportedFieldTypeException(fieldType);
        }
        return value;
    }

    private byte[] generateByteArray(Field field, Rand annotation) {
        return generateString(field, annotation).getBytes();
    }

    private boolean generateBooleanValue() {
        return RandomGenerator.generateRandomInt(0, 2) == 0;
    }

    private Object generateEnumValue(Rand annotation, Field field) {
        Class<?> fieldType = field.getType();
        Set<String> excludeValueNames = new HashSet<>(Arrays.asList(annotation.excludeEnums()));
        for(int i = 0 ;  i < MAX_EXCLUDES_RETRY; i++) {
            Object[] enumConstants = fieldType.getEnumConstants();
            int randomEnumIndex = RandomGenerator.generateRandomInt(0, enumConstants.length);
            Object enumConstant = enumConstants[randomEnumIndex];
            if(!excludeValueNames.contains(enumConstant.toString())) {
                return enumConstant;
            }
        }
        throw new FailedToFindUnexcludedValueForFieldException(field);
    }

    private boolean isUnique(Class<?> fieldType, Object value) {
        return !getValuesForType(fieldType).contains(value);
    }

    private void setValue(FieldSetter fieldSetter , Class<?> fieldType, Object value) {
        fieldSetter.set(value);
        getValuesForType(fieldType).add(value);
    }

    private Set<Object> getValuesForType(Class<?> fieldType) {
        if(!values.containsKey(fieldType)){
            values.put(fieldType, new HashSet<>());
        }
        return values.get(fieldType);
    }

    private  Object generateLong(Rand annotation) {
        return RandomGenerator.generateRandomLong(annotation.minLongInclusive(), annotation.maxLongExclusive());
    }

    private int generateInt(Rand annotation) {
        return RandomGenerator.generateRandomInt(annotation.minIntInclusive(), annotation.maxIntExclusive());
    }

    private String generateString(Field field, Rand annotation) {
        String value = RandomGenerator.generateRandomString(field.getName());
        switch (annotation.caseChange()){
            case UPPER:
                value = value.toUpperCase();
                break;
            case LOWER:
                value =value.toLowerCase();
                break;
        }
        return value;
    }
}
