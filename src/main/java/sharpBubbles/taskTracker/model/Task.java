package sharpBubbles.taskTracker.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;


@Data
@Entity
@Table(name = "tasks")
public class Task {
    public Task() {}

    @Id
    @GeneratedValue
    private Long taskId;

    private String header;

    private String comment;

    private Long owner;

    private LocalDateTime dateTimeOfTask;

    private LocalDateTime deadLineOfTask;

}
