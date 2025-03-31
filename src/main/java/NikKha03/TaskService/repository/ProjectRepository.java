package NikKha03.TaskService.repository;

import NikKha03.TaskService.model.Project;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProjectRepository extends JpaRepository<Project, Long> {

    @Query(value = "SELECT p.* FROM projects p JOIN projects_users pu ON pu.project = p.project_id WHERE p.project_owner_type='INDIVIDUAL_USER' AND pu.username=:username AND pu.role_in_project='CREATOR'", nativeQuery = true)
    List<Project> getMyProjects(@Param("username") String username);

    @Query(value = "SELECT p.* FROM projects p JOIN projects_users pu ON pu.project = p.project_id WHERE p.project_owner_type='INDIVIDUAL_USER' AND pu.username=:username AND pu.role_in_project!='CREATOR'", nativeQuery = true)
    List<Project> getOtherProjects(@Param("username") String username);

    @Query(value = "SELECT p.* FROM projects p JOIN users_projects u ON u.project = p.project_id WHERE u.username=:username AND u.role=:role", nativeQuery = true)
    List<Project> getProjectsWithRole(@Param("username") String username, @Param("role") String role);

}
