package NikKha03.TaskService.repository;

import NikKha03.TaskService.model.ProjectOwner;
import NikKha03.TaskService.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ProjectOwnerRepository extends JpaRepository<ProjectOwner, Long> {
    @Query(value = "SELECT * FROM projects_owners WHERE username=:username", nativeQuery = true)
    User findByUsername(String username);
}
