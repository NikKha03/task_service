package NikKha03.TaskService.model;

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

    @NotNull
    private String projectOwner;

    @NotNull
    @Enumerated(EnumType.STRING)
    private ProjectOwnerType projectOwnerType;

    @OneToMany(mappedBy = "project")
    private List<Tab> tabs;

    // команда, работающая над проектом
    @OneToMany(mappedBy = "project")
    private List<UserInProject> team;

}
