package NikKha03.TaskService.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NonNull;

@Data
@Entity
@Table(name = "users_projects")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // id пользователя, полученное из keycloak
    @NotNull
    private String username;

    @NotNull
    @Enumerated(EnumType.STRING)
    private TeamRole role;

    @ManyToOne()
    @JoinColumn(name = "project", nullable = false)
    @JsonIgnore()
    private Project project;

}
