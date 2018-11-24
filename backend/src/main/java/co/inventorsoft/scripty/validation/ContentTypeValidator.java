package co.inventorsoft.scripty.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;

public class ContentTypeValidator implements ConstraintValidator<ValidContentType, String> {
    @Override
    public void initialize(ValidContentType constraintAnnotation) {

    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        String[] content = value.split(Pattern.quote("/"));
        System.out.println(value);
        if (content.length != 2) return false;
        return true;
    }
}
