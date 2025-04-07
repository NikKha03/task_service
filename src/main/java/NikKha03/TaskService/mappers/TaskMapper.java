package NikKha03.TaskService.mappers;


import NikKha03.TaskService.model.Task;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface TaskMapper {

    @Select("SELECT * FROM tasks WHERE tab=#{tabId} AND task_status=#{status}")
    List<Task> getTasksByTabAndStatus(@Param("tabId") Long tabId, @Param("status") String status);

    @Select("SELECT * FROM tasks WHERE implementer=#{implementer} AND task_status=#{status}")
    List<Task> getTasksByImplementerAndStatus(@Param("implementer") String implementer, @Param("status") String status);

}
