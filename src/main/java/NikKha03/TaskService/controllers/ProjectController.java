package NikKha03.TaskService.controllers;

import NikKha03.TaskService.DTO.ProjectRequest;
import NikKha03.TaskService.service.ProjectService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "project_controller")
@RestController
@RequestMapping("/task_service/project")
public class ProjectController {

    private final ProjectService projectService;

    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @PostMapping("/create")
    public ResponseEntity<?> createProject(@RequestBody ProjectRequest request) {
        return ResponseEntity.ok(projectService.createProject(request));
    }

    @PutMapping("/change/{projectId}")
    public ResponseEntity<?> changeProject(@PathVariable Long projectId, @RequestBody ProjectRequest request) {
        return ResponseEntity.ok(projectService.changeProject(projectId, request));
    }

    @GetMapping("/my")
    public ResponseEntity<?> getMyProjects(@RequestParam String username) {
        return ResponseEntity.ok(projectService.getMyProjects(username));
    }

    @GetMapping("/participant")
    public ResponseEntity<?> getParticipantProjects(@RequestParam String username) {
        return ResponseEntity.ok(projectService.getParticipantProjects(username));
    }

    @GetMapping("/observer")
    public ResponseEntity<?> getObserverProjects(@RequestParam String username) {
        return ResponseEntity.ok(projectService.getObserverProjects(username));
    }

}
