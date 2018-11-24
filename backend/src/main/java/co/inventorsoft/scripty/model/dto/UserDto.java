package co.inventorsoft.scripty.model.dto;
import co.inventorsoft.scripty.validation.PasswordMatches;
import co.inventorsoft.scripty.validation.ValidPassword;
import lombok.*;
import lombok.experimental.FieldDefaults;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Symyniuk
 *
 */
@Getter
@Setter
@EqualsAndHashCode(of="id")
@PasswordMatches
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserDto {

    @NotNull(message = "Please provide your first name")
    String firstName;
    @NotNull(message = "Please provide your last name")
    String lastName;
    @ValidPassword
    String password;
    @NotNull
    String matchingPassword;
    @Email(message = "Please provide your email")
    String email;
}
