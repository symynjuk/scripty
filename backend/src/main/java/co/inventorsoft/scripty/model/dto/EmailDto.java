package co.inventorsoft.scripty.model.dto;
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
public class EmailDto {
    @Email
    private String email;

    @Override
    public String toString() {
        return email;
    }
}
