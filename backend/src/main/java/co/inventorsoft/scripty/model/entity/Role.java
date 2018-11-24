package co.inventorsoft.scripty.model.entity;

import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import javax.persistence.*;
import java.util.List;
/**
 *
 * @author Symyniuk
 *
 */
@Getter
@Setter
@Entity
@EqualsAndHashCode(of="id")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(nullable = false)
    String name;

    @ManyToMany(mappedBy = "roles")
    List <User> users;

    public Role(){}

    public Role(String name, List<User> users) {
        this.name = name;
        this.users = users;
    }
}
