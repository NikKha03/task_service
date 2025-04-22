package NikKha03.TaskService.repository;

import NikKha03.TaskService.model.Project;
import NikKha03.TaskService.model.RolesInProject;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RolesInProjectRepository extends JpaRepository<RolesInProject, Long> {
}
