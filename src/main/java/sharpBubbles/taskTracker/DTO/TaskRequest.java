package sharpBubbles.taskTracker.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import sharpBubbles.taskTracker.model.TaskStatus;

@AllArgsConstructor
@Setter
@Getter
public class TaskRequest {

    private String header;

    private String comment;

    private String plannedImplDate;

    private TaskStatus taskStatus;

}
