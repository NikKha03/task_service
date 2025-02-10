package NikKha03.TaskService.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import NikKha03.TaskService.model.TaskStatus;

@AllArgsConstructor
@Setter
@Getter
public class TaskRequest {

    private String header;

    private String comment;

    private String plannedImplDate;

    private TaskStatus taskStatus;

    private String category;

}
