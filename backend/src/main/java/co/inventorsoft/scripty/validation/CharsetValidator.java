package co.inventorsoft.scripty.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.nio.charset.Charset;

public class CharsetValidator implements ConstraintValidator<ValidCharset, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return Charset.isSupported(value);
    }
}
