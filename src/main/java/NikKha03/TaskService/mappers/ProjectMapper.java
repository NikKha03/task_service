package NikKha03.TaskService.mappers;

import NikKha03.TaskService.model.Project;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ProjectMapper {

    @Select("SELECT p.* FROM projects p JOIN users_projects u ON u.project = p.project_id WHERE u.username=#{username} AND p.project_owner=#{username}")
    List<Project> getMyProjects(@Param("username") String username);

    @Select("SELECT p.* FROM projects p JOIN users_projects u ON u.project = p.project_id WHERE u.username=#{username} AND u.role=#{role}")
    List<Project> getProjectsWithRole(@Param("username") String username, @Param("role") String role);

}
