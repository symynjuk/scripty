package co.inventorsoft.scripty.validation;

import co.inventorsoft.scripty.model.dto.Methods;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class MethodValidator implements ConstraintValidator<ValidMethod, String> {
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (!Methods.contains(value)){
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("You cannot use such method '" + value + "'. Try to use another one.").addConstraintViolation();
            return false;
        }
        return true;
    }
}
