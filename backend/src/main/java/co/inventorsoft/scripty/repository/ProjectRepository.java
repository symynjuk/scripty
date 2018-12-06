package co.inventorsoft.scripty.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.inventorsoft.scripty.model.entity.Project;
import co.inventorsoft.scripty.model.entity.User;

/**
 * @author lzabidovsky 
 */
@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {
	
	Optional<Project> findById(Long id);
	List<Project> findByName(String name);
	List<Project> findByArchive(Boolean archive);
	List<Project> findByVisibility(Boolean visibility);
	List<Project> findByUser(User user);
	List<Project> findByUserAndArchive(User user, Boolean archive);
	List<Project> findByUserAndVisibility(User user, Boolean visibility);
	List<Project> findByUserAndVisibilityAndArchive(User user, Boolean visibility, Boolean archive);
	Project findByNameAndUser(String name, User user);

}
