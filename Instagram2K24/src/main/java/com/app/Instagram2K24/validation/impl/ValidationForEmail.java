package com.app.Instagram2K24.validation.impl;

import com.app.Instagram2K24.validation.DataUtil;
import com.app.Instagram2K24.validation.EmailValidation;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.apache.commons.lang3.StringUtils;

public class ValidationForEmail implements ConstraintValidator<EmailValidation, String> {

    @Override
    public void initialize(EmailValidation constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String string, ConstraintValidatorContext constraintValidatorContext) {
        if (StringUtils.isEmpty(string)){
            return true;
        }
        else {
            return DataUtil.isValidEmailString(string);
        }
    }
}
