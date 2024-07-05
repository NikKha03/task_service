package sharpBubbles.taskTracker.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;


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

    @Temporal(TemporalType.DATE)
    private LocalDate dateOfTask;

    @Temporal(TemporalType.TIME)
    private LocalTime timeOfTask;

}
