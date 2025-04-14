package NikKha03.TaskService.controllers;

import NikKha03.TaskService.DTO.UserRequest;
import NikKha03.TaskService.model.User;
import NikKha03.TaskService.repository.ProjectOwnerRepository;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

@Tag(name = "project_owner_controller")
@RestController
@RequestMapping("/task_service/projectOwner/")
public class ProjectOwnerController {

    ProjectOwnerRepository repository;

    public ProjectOwnerController(ProjectOwnerRepository repository) {
        this.repository = repository;
    }

    @PostMapping("/user")
    public void addUser(@RequestBody UserRequest userRequest) {
        User user = new User();
        user.setUsername(userRequest.getUsername());
        user.setUserId(userRequest.getUserId());
        repository.save(user);
    }

    @GetMapping("/user/{username}")
    public boolean isUserDataHave(@PathVariable("username") String keycloakUsername) {
        System.out.println(keycloakUsername);
        System.out.println(repository.findByUsername(keycloakUsername));
        return repository.findByUsername(keycloakUsername) != null;
    }

}
