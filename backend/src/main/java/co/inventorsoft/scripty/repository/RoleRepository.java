package co.inventorsoft.scripty.repository;
import co.inventorsoft.scripty.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findById(Long id);
    Role findByName(String name);
}
