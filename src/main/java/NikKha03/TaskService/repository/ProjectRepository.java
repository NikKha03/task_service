package NikKha03.TaskService.repository;

import NikKha03.TaskService.model.Project;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProjectRepository extends JpaRepository<Project, Long> {

    // TODO: фильтрацию по project_owner_type нужно исправить
    @Query(value =
            "SELECT p.* FROM projects p JOIN users_in_projects pu ON pu.project = p.project_id WHERE p.project_type != 'COMPANY' AND pu.username = :username AND EXISTS (SELECT 1 FROM project_member_roles pmr JOIN roles_in_project r ON r.id = pmr.role_in_project_id WHERE pmr.user_in_project_id = pu.id AND r.name = 'CREATOR');"
            , nativeQuery = true)
    List<Project> getMyProjects(@Param("username") String username);

    @Query(value =
            "SELECT p.* FROM projects p JOIN users_in_projects pu ON pu.project = p.project_id WHERE p.project_type != 'COMPANY' AND pu.username = :username AND NOT EXISTS (SELECT 1 FROM project_member_roles pmr JOIN roles_in_project r ON r.id = pmr.role_in_project_id WHERE pmr.user_in_project_id = pu.id AND r.name = 'CREATOR');"
            , nativeQuery = true)
    List<Project> getOtherProjects(@Param("username") String username);

    @Query(value = "SELECT p.* FROM projects p JOIN users_in_projects u ON u.project = p.project_id WHERE u.username=:username AND u.role=:role", nativeQuery = true)
    List<Project> getProjectsWithRole(@Param("username") String username, @Param("role") String role);

}
