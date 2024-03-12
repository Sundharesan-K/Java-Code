package com.app.Instagram2K24.validation;

import com.app.Instagram2K24.validation.impl.ValidationForEmail;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({ElementType.ANNOTATION_TYPE,ElementType.FIELD,ElementType.PARAMETER,ElementType.TYPE,ElementType.TYPE_USE,ElementType.TYPE_PARAMETER})
@Retention(RUNTIME)
@Documented
@Constraint(validatedBy = {ValidationForEmail.class})
public @interface EmailValidation {
    String message() default "Invalid Email Formant";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
