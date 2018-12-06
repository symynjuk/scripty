package co.inventorsoft.scripty.model.dto;
import lombok.Getter;
import lombok.Setter;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

/**
 *
 * @author Symyniuk
 *
 */
@Getter
@Setter
public class EmailDto {

    @Email
    @NotBlank(message = "Please provide your email")
    private String email;

    @Override
    public String toString() {
        return email;
    }
}
