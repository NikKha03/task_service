package NikKha03.TaskService.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Entity
@Table(name = "projects_users")
public class UserInProject {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // username пользователя, полученное из keycloak
    @NotNull
    private String username;

    @ManyToOne()
    @JoinColumn(name = "project", nullable = false)
    @JsonIgnore()
    private Project project;

    @NotNull
    @Enumerated(EnumType.STRING)
    private ProjectRole roleInProject;

//    @ManyToMany(fetch = FetchType.EAGER)
//    @JsonIgnore()
//    @JoinTable(
//            name = "user_projects",
//            joinColumns = @JoinColumn(name = "user_id"),
//            inverseJoinColumns = @JoinColumn(name = "project_id"))
//    private List<Project> projects = new ArrayList<>();

}
