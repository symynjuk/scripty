package co.inventorsoft.scripty.validation;

import co.inventorsoft.scripty.model.dto.UpdatePasswordDto;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class OldPasswordMatchesValidator implements ConstraintValidator<OldPasswordMatches, Object> {
    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        UpdatePasswordDto updatePasswordDto = (UpdatePasswordDto)value;

        return !updatePasswordDto.getOldPassword().equals(updatePasswordDto.getNewPassword().getPassword());
    }
}
