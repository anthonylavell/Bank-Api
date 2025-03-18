package com.jrp.pma.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD}) // Only apply to the field of the class
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = UniqueValidator.class)
public @interface IUniqueValue {
    String message() default "Unique Contraint violated";
    Class<?>[] groups() default {};
    Class<? extends Payload> [] payload() default {};
}
