package NikKha03.TaskService.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "projects")
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long projectId;

    @NotNull
    private String name;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "owner_id", nullable = false)
    private ProjectOwner projectOwner;

    @NotNull
    @Enumerated(EnumType.STRING)
    private ProjectType projectType;

    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL)
    private List<Tab> tabs;

    // команда, работающая над проектом
    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL)
    private List<UserInProject> team;

}
