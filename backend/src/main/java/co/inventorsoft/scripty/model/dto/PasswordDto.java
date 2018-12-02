package co.inventorsoft.scripty.model.dto;

import co.inventorsoft.scripty.validation.UpdatePasswordMatches;
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
@UpdatePasswordMatches
@ApiModel("Update Password Model")
public class PasswordDto {
    @ApiModelProperty(value = "Old password of user")
    String oldPassword;

    @ApiModelProperty(value = "New password of user")
    @ValidPassword
    String password;

    @ApiModelProperty(value = "Confirmation password. Must match just password")
    String cPassword;

    public boolean match(){
        return password.equals(cPassword);
    }
}
