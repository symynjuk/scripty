package co.inventorsoft.scripty.model.entity;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.time.Clock;
import java.time.Instant;
import java.time.temporal.ChronoUnit;

@Entity
@Getter
@Setter
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@EqualsAndHashCode(of="id")
public class PasswordToken {
    static Long EXPIRATION_DAYS = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String passwordToken;

    @OneToOne(targetEntity = User.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "user_id")
    User user;

    Instant expiryDate;
    public PasswordToken(String passwordToken, User user){
        this.passwordToken = passwordToken;
        this.user = user;
        this.expiryDate = calculateExpiryDate(EXPIRATION_DAYS);
    }
    public PasswordToken updatePasswordToken(final String passwordToken) {
        this.passwordToken = passwordToken;
        this.expiryDate = calculateExpiryDate(EXPIRATION_DAYS);
        return this;
    }
    private Instant calculateExpiryDate(final Long expirationDate){
        return Clock.systemDefaultZone().instant().plus(expirationDate, ChronoUnit.DAYS);
    }
}
