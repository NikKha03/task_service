package NikKha03.TaskService.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import NikKha03.TaskService.model.TaskStatus;

@AllArgsConstructor
@Setter
@Getter
public class TaskRequest {

    private Long categoryId;

    private String header;

    private String comment;

    private String plannedImplDate;

    private TaskStatus taskStatus;

    public TaskRequest() {
    }

    public TaskRequest(Long categoryId, String header) {
        this.categoryId = categoryId;
        this.header = header;
    }
}
