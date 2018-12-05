package co.inventorsoft.scripty.model.entity;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import javax.persistence.*;
/**
 *
 * @author Symyniuk
 *
 */
@Getter
@Setter
@EqualsAndHashCode(of="id")
@Entity
@Table(name = "users")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "first_name", length = 60)
    String firstName;

    @Column(name = "last_name", length = 60)
    String lastName;

    @Column(nullable = false, unique = true)
    String email;
    @Column(length = 60)
    String password;

    @Lob
    @OneToOne(cascade=CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    @JoinColumn(name="PICTURE_ID")
    Picture picture;

    @Column(nullable = false)
    boolean enabled;

    @Column(nullable = false, length = 10)
    String role;
}
