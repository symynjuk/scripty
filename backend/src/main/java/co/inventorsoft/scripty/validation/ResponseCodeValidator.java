package co.inventorsoft.scripty.validation;

import org.springframework.http.HttpStatus;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ResponseCodeValidator implements ConstraintValidator<ValidResponseCode, Integer> {
    @Override
    public boolean isValid(Integer value, ConstraintValidatorContext context) {
        if(HttpStatus.resolve(value) == null) {
            return false;
        }
        return true;
    }
}
