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
            new FieldSetter(target, field).set(RandomGenerator.generateRandomString(field.getName()));
        }
    }
}
