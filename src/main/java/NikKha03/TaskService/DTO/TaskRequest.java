package NikKha03.TaskService.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import NikKha03.TaskService.model.TaskStatus;

@AllArgsConstructor
@Setter
@Getter
public class TaskRequest {

    private Long tabId;

    private String header;

    private String comment;

    private String deadline;

    private TaskStatus taskStatus;

    private String implementer;

    public TaskRequest() {
    }

    public TaskRequest(Long tabId, String header) {
        this.tabId = tabId;
        this.header = header;
    }
}
