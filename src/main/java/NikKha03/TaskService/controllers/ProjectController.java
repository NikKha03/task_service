package NikKha03.TaskService.controllers;

import NikKha03.TaskService.DTO.ProjectRequest;
import NikKha03.TaskService.service.ProjectService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.transaction.Transactional;
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

    @Transactional
    @DeleteMapping("/delete/{projectId}")
    public void deleteProject(@PathVariable Long projectId) {
        projectService.deleteProject(projectId);
    }

    @GetMapping("/my")
    public ResponseEntity<?> getMyProjects(@RequestParam String username) {
        return ResponseEntity.ok(projectService.getMyProjects(username));
    }

    @GetMapping("/getWithRole")
    public ResponseEntity<?> getProjectsWithRole(@RequestParam String username, @RequestParam String role) {
        return ResponseEntity.ok(projectService.getProjectsWithRole(username, role));
    }

}
