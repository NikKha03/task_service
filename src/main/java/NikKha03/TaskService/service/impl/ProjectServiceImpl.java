package NikKha03.TaskService.service.impl;

import NikKha03.TaskService.DTO.ProjectRequest;
import NikKha03.TaskService.mappers.ProjectMapper;
import NikKha03.TaskService.model.Project;
import NikKha03.TaskService.model.TeamRole;
import NikKha03.TaskService.model.User;
import NikKha03.TaskService.repository.ProjectRepository;
import NikKha03.TaskService.repository.UserRepository;
import NikKha03.TaskService.service.ProjectService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProjectServiceImpl implements ProjectService {

    private final ProjectRepository repository;
    private final ProjectMapper mapper;

    private final UserRepository userRepository;

    public ProjectServiceImpl(ProjectRepository projectRepository, ProjectMapper projectMapper, UserRepository userRepository) {
        this.repository = projectRepository;
        this.mapper = projectMapper;
        this.userRepository = userRepository;
    }

    @Override
    public ResponseEntity<?> createProject(ProjectRequest request) {
        if (request.getProjectOwner() == null || request.getName() == null)
            return ResponseEntity.badRequest().body("Empty projectOwner or name");

        Project project = new Project();
        project.setName(request.getName());
        project.setProjectOwner(request.getProjectOwner());
        repository.save(project);

        User projectUser = new User();
        projectUser.setProject(project);
        projectUser.setUsername(request.getProjectOwner());
        projectUser.setRole(TeamRole.Participant.toString());
        userRepository.save(projectUser);

        List<User> team = new ArrayList<>();
        team.add(projectUser);
        project.setTeam(team);

        return ResponseEntity.ok(project);
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

    @Override
    public void deleteProject(Long projectId) {
        repository.deleteById(projectId);
    }

    @Override
    public ResponseEntity<?> getMyProjects(String username) {
        return ResponseEntity.ok(mapper.getMyProjects(username));
    }

    @Override
    public ResponseEntity<?> getParticipantProjects(String username) {
        return ResponseEntity.ok(mapper.getProjectsWithRole(username, TeamRole.Participant.toString()));
    }

    @Override
    public ResponseEntity<?> getObserverProjects(String username) {
        return ResponseEntity.ok(mapper.getProjectsWithRole(username, TeamRole.Observer.toString()));
    }

}
