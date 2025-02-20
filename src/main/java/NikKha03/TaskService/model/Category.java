package NikKha03.TaskService.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "categories")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long categoryId;

    @NotNull
    private String name;

    @NotNull
    @ManyToOne()
    @JoinColumn(name = "project", nullable = false)
    @JsonIgnore()
    private Project project;

    @OneToMany(mappedBy = "category")
    private List<Task> tasks;

}
