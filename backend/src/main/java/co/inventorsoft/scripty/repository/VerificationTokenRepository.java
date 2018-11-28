package co.inventorsoft.scripty.repository;

import co.inventorsoft.scripty.model.entity.User;
import co.inventorsoft.scripty.model.entity.VerificationToken;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface VerificationTokenRepository extends JpaRepository<VerificationToken, Long> {
    Optional<VerificationToken> findByToken(String token);
    Optional<VerificationToken> findByUser(User user);
}
