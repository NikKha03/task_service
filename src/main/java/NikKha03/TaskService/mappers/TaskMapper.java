package NikKha03.TaskService.mappers;


import NikKha03.TaskService.model.Task;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface TaskMapper {

    @Select("SELECT * FROM tasks WHERE category=#{categoryId}")
    List<Task> getTasksByCategory(@Param("categoryId") Long categoryId);

}
