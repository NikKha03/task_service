package NikKha03.TaskService.repository;

import NikKha03.TaskService.model.Project;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProjectRepository extends JpaRepository<Project, Long> {

    @Query(value = "SELECT p.* FROM projects p JOIN users_projects u ON u.project = p.project_id WHERE u.username=:username AND p.project_owner=:username", nativeQuery = true)
    List<Project> getMyProjects(@Param("username") String username);

    @Query(value = "SELECT p.* FROM projects p JOIN users_projects u ON u.project = p.project_id WHERE u.username=:username AND u.role=:role", nativeQuery = true)
    List<Project> getProjectsWithRole(@Param("username") String username, @Param("role") String role);

}
