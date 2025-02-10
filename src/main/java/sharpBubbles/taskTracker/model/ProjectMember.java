package sharpBubbles.taskTracker.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "projects_members")
public class ProjectMember {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne()
    @JoinColumn(name = "project_id", nullable = false)
    @JsonIgnore()
    private Project projectId;

    // id пользователя, полученное из keycloak
    private String memberId;

}
