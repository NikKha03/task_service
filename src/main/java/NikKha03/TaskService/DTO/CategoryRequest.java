package NikKha03.TaskService.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Setter
@Getter
public class CategoryRequest {

    private String name;

    private Long projectId;

}
