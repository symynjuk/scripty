package co.inventorsoft.scripty.model.dto;
import co.inventorsoft.scripty.validation.PasswordMatches;
import co.inventorsoft.scripty.validation.ValidPassword;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import javax.validation.constraints.Email;

/**
 *
 * @author Symyniuk
 *
 */

@Getter
@Setter
@PasswordMatches
@AllArgsConstructor
public class ResetPasswordDto{
    @ValidPassword
    @PasswordMatches
    Password password;
    @Email(message = "Please provide your email")
    String email;
}
