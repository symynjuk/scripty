package co.inventorsoft.scripty.model.dto;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
public class Password {

    String password;
    String matchingPassword;
}
