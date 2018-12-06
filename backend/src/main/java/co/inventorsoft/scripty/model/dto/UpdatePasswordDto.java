package co.inventorsoft.scripty.model.dto;

import co.inventorsoft.scripty.validation.OldPasswordMatches;
import co.inventorsoft.scripty.validation.PasswordMatches;
import co.inventorsoft.scripty.validation.ValidPassword;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@OldPasswordMatches
@ApiModel("Update Password Model")
public class UpdatePasswordDto {
    @ApiModelProperty(value = "Old password of user")
    String oldPassword;

    @PasswordMatches
    @ValidPassword
    Password newPassword;
}
