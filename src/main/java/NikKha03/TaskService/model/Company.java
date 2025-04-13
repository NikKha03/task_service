package NikKha03.TaskService.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

@Entity
public class Company extends ProjectOwner {

    @NotNull
    String name;

}
