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

    // id пользователя, полученное из keycloak
    @NotNull
    private String projectOwner;

    @OneToMany(mappedBy = "project")
    private List<Category> categories;

    // команда, работающая над проектом
    @OneToMany(mappedBy = "project")
    private List<User> team;

}
