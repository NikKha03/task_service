package NikKha03.TaskService.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // id пользователя, полученное из keycloak
    private String username;

    @ManyToOne()
    @JoinColumn(name = "project", nullable = false)
    @JsonIgnore()
    private Project project;

}
