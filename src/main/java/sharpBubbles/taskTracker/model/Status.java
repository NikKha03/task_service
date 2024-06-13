package sharpBubbles.taskTracker.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "status")
public class Status {
    public Status() {}

    @Id
    @GeneratedValue
    private Long statusId;

    @NotNull
    private String statusValue;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "status")
    private List<Task> tasks;

}
