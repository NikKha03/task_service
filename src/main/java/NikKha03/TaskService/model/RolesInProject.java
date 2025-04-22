package NikKha03.TaskService.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
@Table(name = "roles_in_project")
public class RolesInProject {
    //    CREATOR, ADMIN, MEMBER, VIEWER;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToMany(mappedBy = "roles")
    @JsonIgnore()
    private List<UserInProject> userInProjectId;

    private String name;
}
