package co.inventorsoft.scripty.model.entity;
import lombok.*;
import lombok.experimental.FieldDefaults;
import javax.persistence.*;
import java.time.Clock;
import java.time.Instant;
/**
 *
 * @author Symyniuk
 *
 */
@Getter
@Setter
@EqualsAndHashCode(of="id")
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@Entity
public class VerificationToken {
    static final Long EXPIRATION = 60L;
    static final Long TO_MINUTES = 60000L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String token;

    @OneToOne(targetEntity = User.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "user_id")
    User user;

    Instant expiryDate;
    public VerificationToken(final String token, final User user) {
        super();

        this.token = token;
        this.user = user;
        this.expiryDate = calculateExpiryDate(EXPIRATION * TO_MINUTES);
    }

    private Instant calculateExpiryDate(final Long expiryTimeInMinutes) {
        return Clock.systemDefaultZone().instant().plusMillis(expiryTimeInMinutes);
    }
    public VerificationToken updateToken(final String token) {
        this.token = token;
        this.expiryDate = calculateExpiryDate(EXPIRATION * TO_MINUTES);
        return this;
    }
}
