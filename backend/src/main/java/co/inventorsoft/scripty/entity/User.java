package co.inventorsoft.scripty.entity;
import lombok.*;
import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "first_name")
    String firstName;

    @Column(name = "last_name")
    String lastName;

    @Column(nullable = false, unique = true)
    String email;
    @Column(length = 60)
    String password;

    @Lob
    Byte picture;

    @Column(nullable = false)
    boolean enabled;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_roles",
            joinColumns = {@JoinColumn(name = "user_id")},
            inverseJoinColumns = {@JoinColumn (name = "role_id")})
    List<Role> roles;
}
