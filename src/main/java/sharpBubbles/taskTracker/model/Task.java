package sharpBubbles.taskTracker.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import java.time.LocalDateTime;


@Data
@Entity
@Table(name = "tasks")

public class Task {
    public Task(Long taskId, String header, String comment, Long owner, LocalDateTime dateTime, Status status) {}

    @Id
    @GeneratedValue
    private Long taskId;

    private String header;

    private String comment;

    @NotNull
    private Long owner;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime dateTimeOfTask;

    private Status status;

    public enum Status {
        IN_PROGRESS,
        COMPLETED;
    }
}