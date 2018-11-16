package co.inventorsoft.academy.model.entity;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "first_name")
    @NotNull(message = "Please provide your first name")
    String firstName;

    @Column(name = "last_name")
    @NotNull(message = "Please provide your last name")
    String lastName;

    @Column(nullable = false, unique = true)
    @Email(message = "Please provide your email")
    String email;

    @Transient
    @Size(min = 6, max = 16)
    String password;

    @Lob
    Byte picture;

    @Column(nullable = false)
    boolean enabled;

    @Column(name = "confirmation_token")
    String confirmationToken;

    @ManyToMany(mappedBy = "users")
    List<Role> roles;

    public void addRole(Role role){
        roles.add(role);
    }
}
