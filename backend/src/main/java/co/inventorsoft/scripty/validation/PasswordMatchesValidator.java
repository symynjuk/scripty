package co.inventorsoft.scripty.validation;
import co.inventorsoft.scripty.model.dto.Password;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 *
 * @author Symyniuk
 *
 */
public class PasswordMatchesValidator implements ConstraintValidator<PasswordMatches, Password>{

    @Override
    public boolean isValid(final Password password, final ConstraintValidatorContext constraintValidatorContext) {

        if(password.getPassword() == null){
            constraintValidatorContext.disableDefaultConstraintViolation();
            constraintValidatorContext.buildConstraintViolationWithTemplate("Please provide your password")
                    .addConstraintViolation();
            return false;
        }
        if(password.getMatchingPassword() == null){
            constraintValidatorContext.disableDefaultConstraintViolation();
            constraintValidatorContext.buildConstraintViolationWithTemplate("Please provide your confirmation password")
                    .addConstraintViolation();
            return false;
        }
       return password.getPassword().equals(password.getMatchingPassword());
    }
}
