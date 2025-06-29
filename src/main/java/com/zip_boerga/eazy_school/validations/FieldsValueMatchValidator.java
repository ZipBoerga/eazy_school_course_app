package com.zip_boerga.eazy_school.validations;

import com.zip_boerga.eazy_school.validations.annotations.FieldsValueMatch;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.BeanWrapperImpl;

public class FieldsValueMatchValidator implements ConstraintValidator<FieldsValueMatch, Object> {
    private String field;
    private String fieldMatch;

    @Override
    public void initialize(FieldsValueMatch constraintAnnotation) {
        this.field = constraintAnnotation.field();
        this.fieldMatch = constraintAnnotation.fieldMatch();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        Object fieldValue = new BeanWrapperImpl(value).getPropertyValue(this.field);
        Object fieldMatchValue = new BeanWrapperImpl(value).getPropertyValue(this.fieldMatch);
        return fieldValue != null && fieldValue.equals(fieldMatchValue);
    }
}
