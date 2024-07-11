package sharpBubbles.taskTracker.DTO;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import sharpBubbles.taskTracker.model.Task;

import java.time.LocalDateTime;

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

    private String dateTimeOfTask;

    private Task.Status status;

}
