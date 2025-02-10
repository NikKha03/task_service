package NikKha03.TaskService.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "projects")
public class Project {

    @Id
    @GeneratedValue()
    private Long projectId;

    private String name;

    // id пользователя, полученное из keycloak
    private String projectOwner;

    @OneToMany(mappedBy = "projectId")
    private List<ProjectMember> team;

    @OneToMany()
    private List<Task> tasks;

}
