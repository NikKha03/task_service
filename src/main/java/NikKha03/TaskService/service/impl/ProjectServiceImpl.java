package NikKha03.TaskService.service.impl;

import NikKha03.TaskService.DTO.ProjectRequest;
import NikKha03.TaskService.model.Project;
import NikKha03.TaskService.model.User;
import NikKha03.TaskService.repository.ProjectRepository;
import NikKha03.TaskService.service.ProjectService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProjectServiceImpl implements ProjectService {

    private final ProjectRepository repository;

    public ProjectServiceImpl(ProjectRepository projectRepository) {
        this.repository = projectRepository;
    }

    @Override
    public ResponseEntity<?> createProject(ProjectRequest request) {
        if (request.getProjectOwner() == null || request.getName() == null)
            return ResponseEntity.badRequest().body("Empty projectOwner or name");

        Project project = new Project();
        project.setName(request.getName());
        project.setProjectOwner(request.getProjectOwner());

        User projectUser = new User();
        projectUser.setProject(project);
        projectUser.setUsername(request.getProjectOwner());

        List<User> team = new ArrayList<>();
        team.add(projectUser);
        project.setTeam(team);

        return ResponseEntity.ok(repository.save(project));
    }

    // TODO
    @Override
    public ResponseEntity<?> changeProject(Long projectId, ProjectRequest request) {
        Project project = repository.findById(projectId).orElse(null);
        if (project == null)
            return ResponseEntity.badRequest().body("Invalid projectId");

        project.setName(request.getName());

        return ResponseEntity.ok(repository.save(project));
    }

}
