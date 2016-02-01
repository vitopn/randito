package org.randito;

import org.mockito.internal.util.reflection.FieldSetter;

import java.lang.reflect.Field;

public class RanditoAnnotations {

    public static void init(Object target) {
        Class<?> targetClass = target.getClass();
        Field[] fields = targetClass.getDeclaredFields();
        for(Field field : fields){
            processField(target, field);
        }
    }

    private static void processField(Object target, Field field) {
        Class<Randito> randomClass = Randito.class;
        Randito annotation = field.getAnnotation(randomClass);
        if(annotation == null){
            return;
        }

        if(field.getType() == String.class){
            new FieldSetter(target, field).set(generateString(field, annotation));
        } else if(field.getType() == int.class){
            new FieldSetter(target, field).set(generateInt(annotation));
        }
    }

    private static int generateInt(Randito annotation) {
        return RandomGenerator.generateRandomInt(annotation.minIntInclusive(), annotation.maxIntExclusive());
    }

    private static String generateString(Field field, Randito annotation) {
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
