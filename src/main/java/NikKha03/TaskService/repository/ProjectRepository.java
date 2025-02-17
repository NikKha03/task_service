package NikKha03.TaskService.repository;

import NikKha03.TaskService.model.Project;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectRepository extends JpaRepository<Project, Long> {
}
