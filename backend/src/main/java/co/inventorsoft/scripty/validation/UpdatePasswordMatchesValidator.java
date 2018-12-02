package co.inventorsoft.scripty.validation;

import co.inventorsoft.scripty.model.dto.PasswordDto;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UpdatePasswordMatchesValidator implements ConstraintValidator<UpdatePasswordMatches, Object> {
    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        PasswordDto passwordDto = (PasswordDto)value;
        if (passwordDto.getOldPassword().equals(passwordDto.getPassword())) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("New Password should not be the same as old one").addConstraintViolation();
            return false;
        }
        return passwordDto.match();
    }
}
