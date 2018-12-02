package co.inventorsoft.scripty.validation;

import org.passay.*;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;

/**
 *
 * @author Symyniuk
 *
 */
public class PasswordConstraintValidator implements ConstraintValidator<ValidPassword, String> {
    @Override
    public void initialize(ValidPassword constraintAnnotation) {
    }

    @Override
    public boolean isValid(String password, ConstraintValidatorContext constraintValidatorContext) {
        PasswordValidator passwordValidator = new PasswordValidator(Arrays.asList(
                new LengthRule(6, 16),
                new UppercaseCharacterRule(1),
                new LowercaseCharacterRule(1),
                new DigitCharacterRule(1),
                new SpecialCharacterRule(1)
              ));

        final RuleResult result = passwordValidator.validate(new PasswordData(password));
        if (result.isValid()) {
            return true;
        }
        constraintValidatorContext.disableDefaultConstraintViolation();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(passwordValidator.getMessages(result));
        String s = String.valueOf(stringBuilder);
        constraintValidatorContext.buildConstraintViolationWithTemplate(s)
                .addConstraintViolation();
        return false;
    }
}
