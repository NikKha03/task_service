package NikKha03.TaskService.DTO;

import NikKha03.TaskService.model.ProjectOwnerType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Setter
@Getter
public class ProjectRequest {

    private String name;

    private Long projectOwner;

    private ProjectOwnerType projectOwnerType;

    // username пользователя из keycloak, который создает проект
    private String principalUser;

}
