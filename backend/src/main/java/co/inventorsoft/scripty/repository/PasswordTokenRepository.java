package co.inventorsoft.scripty.repository;

import co.inventorsoft.scripty.model.entity.PasswordToken;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PasswordTokenRepository extends JpaRepository<PasswordToken, Long>{
    Optional<PasswordToken> findByPasswordToken(String passwordToken);
}
