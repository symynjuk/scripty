package co.inventorsoft.scripty.repository;

import co.inventorsoft.scripty.model.entity.PasswordToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PasswordTokenRepository extends JpaRepository<PasswordToken, Long>{
}
