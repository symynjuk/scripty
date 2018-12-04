package co.inventorsoft.scripty.validation;
import co.inventorsoft.scripty.exception.ApplicationException;
import co.inventorsoft.scripty.model.dto.Password;
import co.inventorsoft.scripty.model.dto.UserDto;
import org.springframework.http.HttpStatus;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 *
 * @author Symyniuk
 *
 */
public class PasswordMatchesValidator implements ConstraintValidator<PasswordMatches, Password>{

    @Override
    public void initialize(final PasswordMatches constraintAnnotation) {
    }

    @Override
    public boolean isValid(final Password password, final ConstraintValidatorContext constraintValidatorContext) {
        if(password.getPassword() == null){
            throw new ApplicationException("Please provide password", HttpStatus.BAD_REQUEST);
        }
        if(password.getMatchingPassword() == null){
            throw new ApplicationException("Please confirm your password correct", HttpStatus.BAD_REQUEST);
        }
       return password.getPassword().equals(password.getMatchingPassword());
    }
}
