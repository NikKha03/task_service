package NikKha03.TaskService.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.util.List;

@Entity
public class Company extends ProjectOwner {

    @NotNull
    String companyName;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "employees_companies",
            joinColumns = @JoinColumn(name = "company_id"),
            inverseJoinColumns = @JoinColumn(name = "employee_id"))
    List<User> employees;

}
