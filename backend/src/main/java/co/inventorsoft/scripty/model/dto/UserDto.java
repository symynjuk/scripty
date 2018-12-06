package co.inventorsoft.scripty.model.dto;
import co.inventorsoft.scripty.validation.PasswordMatches;
import co.inventorsoft.scripty.validation.ValidPassword;
import lombok.*;
import lombok.experimental.FieldDefaults;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 *
 * @author Symyniuk
 *
 */

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserDto {
    @Size(min = 2, max = 20)
    @NotBlank(message = "Please provide your first name")
    String firstName;
    @Size(min = 2, max = 20)
    @NotBlank(message = "Please provide your last name")
    String lastName;
    @ValidPassword
    @PasswordMatches
    Password password;
    @Email
    @NotBlank(message = "Please provide your email")
    String email;
    public String getValidPassword(){
        return password.getPassword();
    }
}
