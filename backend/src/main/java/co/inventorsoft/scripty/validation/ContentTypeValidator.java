package co.inventorsoft.scripty.validation;

import co.inventorsoft.scripty.model.dto.ContentTypes;
import org.springframework.http.MediaType;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ContentTypeValidator implements ConstraintValidator<ValidContentType, String> {
    @Override
    public void initialize(ValidContentType constraintAnnotation) {

    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        try {
            MediaType.parseMediaType(value);
        } catch (IllegalArgumentException e) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(e.getMessage()).addConstraintViolation();
            return false;
        }
        if (!ContentTypes.contains(value)) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("You cannot use such ContentType '" + value + "'. Try to use another one.").addConstraintViolation();
            return false;
        }
        return true;
    }
}
