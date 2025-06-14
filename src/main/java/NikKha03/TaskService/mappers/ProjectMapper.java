package NikKha03.TaskService.mappers;

import NikKha03.TaskService.model.Project;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ProjectMapper {

    @Select("SELECT p.* FROM projects p JOIN users_projects u ON u.project = p.project_id WHERE u.username=#{username} AND p.project_owner=#{username}")
    List<Project> getMyProjects(@Param("username") String username);

    @Select("SELECT p.* FROM projects p JOIN users_projects u ON u.project = p.project_id WHERE u.username=#{username} AND u.role=#{role}")
    List<Project> getProjectsWithRole(@Param("username") String username, @Param("role") String role);

    // Удаление записей из project_member_roles по пользователю и проекту
    @Delete("""
                DELETE FROM project_member_roles 
                WHERE user_in_project_id IN (
                    SELECT uip.id 
                    FROM users_in_projects uip 
                    WHERE uip.project = #{projectId} AND uip.username = #{username}
                )
            """)
    void deleteUserRolesFromProject(@Param("projectId") Long projectId, @Param("username") String username);

    // Удаление пользователя из users_in_projects
    @Delete("""
                DELETE FROM users_in_projects 
                WHERE project = #{projectId} AND username = #{username}
            """)
    void deleteUserFromProject(@Param("projectId") Long projectId, @Param("username") String username);

}
