package NikKha03.TaskService.repository;

import NikKha03.TaskService.model.Project;
import NikKha03.TaskService.model.UserInProject;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<UserInProject, Long> {

    @Modifying
    @Query(value = "DELETE FROM projects_users WHERE project=:projectId", nativeQuery = true)
    void deleteByProject(@Param("projectId") Long projectId);

}
