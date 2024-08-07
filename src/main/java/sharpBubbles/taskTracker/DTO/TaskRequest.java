package sharpBubbles.taskTracker.DTO;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import sharpBubbles.taskTracker.model.TaskStatus;

@AllArgsConstructor
@Setter
@Getter
public class TaskRequest {

    @Id
    @GeneratedValue
    private Long taskId;

    private String header;

    private String comment;

    private Long owner;

    private String datePlannedImplementation;

    private TaskStatus taskStatus;

}
