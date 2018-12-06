package co.inventorsoft.scripty.repository;

import co.inventorsoft.scripty.model.entity.PasswordToken;
import co.inventorsoft.scripty.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PasswordTokenRepository extends JpaRepository<PasswordToken, Long>{
    Optional<PasswordToken> findByPasswordToken(String passwordToken);
    Optional<PasswordToken> findByUser(User user);
}
