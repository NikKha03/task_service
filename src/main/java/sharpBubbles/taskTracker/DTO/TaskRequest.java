package sharpBubbles.taskTracker.DTO;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

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

    @NotNull
    private Long owner;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm", shape = JsonFormat.Shape.STRING)
    private LocalDateTime dateTimeOfTask;

}
