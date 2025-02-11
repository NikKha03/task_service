package NikKha03.TaskService.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;


@Data
@Entity
@Table(name = "tasks")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long taskId;

    @NotNull
    private String header;

    private String comment;

    // кто создал задачу (username пользователя, полученный из keycloak)
    @NotNull
    private String owner;

    // кто выполнил задачу (username пользователя, полученный из keycloak)
    private String implementer;

    @ManyToOne()
    @JoinColumn(name = "category", nullable = false)
    @JsonIgnore()
    private Category category;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm", shape = JsonFormat.Shape.STRING)
    private LocalDateTime creationDate;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm", shape = JsonFormat.Shape.STRING)
    private LocalDateTime executionDate;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm", shape = JsonFormat.Shape.STRING)
    private LocalDateTime plannedImplDate;

    @Enumerated(EnumType.STRING)
    private TaskStatus taskStatus;

}