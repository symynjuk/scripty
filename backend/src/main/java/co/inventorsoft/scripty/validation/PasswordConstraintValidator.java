package co.inventorsoft.scripty.validation;
import co.inventorsoft.scripty.model.dto.Password;
import org.passay.*;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;

/**
 *
 * @author Symyniuk
 *
 */
public class PasswordConstraintValidator implements ConstraintValidator<ValidPassword, Password> {

    @Override
    public boolean isValid(Password password, ConstraintValidatorContext constraintValidatorContext) {
        if(password.getPassword() == null){
            constraintValidatorContext.disableDefaultConstraintViolation();
            constraintValidatorContext.buildConstraintViolationWithTemplate("Please provide your password")
                    .addConstraintViolation();
            return false;
        }
        PasswordValidator passwordValidator = new PasswordValidator(Arrays.asList(
                new LengthRule(6, 16),
                new UppercaseCharacterRule(1),
                new LowercaseCharacterRule(1),
                new DigitCharacterRule(1),
                new SpecialCharacterRule(1)
              ));

        final RuleResult result = passwordValidator.validate(new PasswordData(password.getPassword()));
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
