package org.randito;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target(FIELD)
@Retention(RUNTIME)
@Documented
@interface Randito {
    int maxIntExclusive() default Integer.MAX_VALUE;

    int minIntInclusive() default Integer.MIN_VALUE;

    enum CASE_CHANGE {
        UPPER,
        LOWER,
        NONE
    }
    CASE_CHANGE caseChange() default CASE_CHANGE.NONE;
}
