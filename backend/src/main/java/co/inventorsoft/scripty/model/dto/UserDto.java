package co.inventorsoft.scripty.model.dto;
import co.inventorsoft.scripty.validation.PasswordMatches;
import co.inventorsoft.scripty.validation.ValidPassword;
import lombok.*;
import lombok.experimental.FieldDefaults;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

/**
 *
 * @author Symyniuk
 *
 */

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserDto {

    @NotBlank(message = "Please provide your last name")
    String firstName;
    @NotBlank(message = "Please provide your last name")
    String lastName;
    @ValidPassword
    @PasswordMatches
    Password password;
    @NotBlank
    @Email(message = "Please provide your email")
    String email;
    public String getValidPassword(){
        return password.getPassword();
    }
}
