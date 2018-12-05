package co.inventorsoft.scripty.model.dto;
import co.inventorsoft.scripty.validation.PasswordMatches;
import co.inventorsoft.scripty.validation.ValidPassword;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

/**
 *
 * @author Symyniuk
 *
 */

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ResetPasswordDto{

    @PasswordMatches
    @ValidPassword
    Password password;

    @Email
    @NotBlank(message = "Please provide your email")
    String email;
    public String getValidPassword(){
        return password.getPassword();
    }
}
