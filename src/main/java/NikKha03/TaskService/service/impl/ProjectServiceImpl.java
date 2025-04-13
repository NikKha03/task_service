package NikKha03.TaskService.service.impl;

import NikKha03.TaskService.DTO.ProjectRequest;
import NikKha03.TaskService.mappers.ProjectMapper;
import NikKha03.TaskService.model.*;
import NikKha03.TaskService.repository.ProjectOwnerRepository;
import NikKha03.TaskService.repository.ProjectRepository;
import NikKha03.TaskService.repository.TabRepository;
import NikKha03.TaskService.repository.UserRepository;
import NikKha03.TaskService.service.ProjectService;
import jakarta.transaction.Transactional;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProjectServiceImpl implements ProjectService {

    private final ProjectRepository repository;
    private final ProjectMapper mapper;

    private final ProjectOwnerRepository projectOwnerRepository;
    private final UserRepository userRepository;
    private final TabRepository tabRepository;

    public ProjectServiceImpl(ProjectRepository repository, ProjectMapper mapper, ProjectOwnerRepository projectOwnerRepository, UserRepository userRepository, TabRepository tabRepository) {
        this.repository = repository;
        this.mapper = mapper;
        this.projectOwnerRepository = projectOwnerRepository;
        this.userRepository = userRepository;
        this.tabRepository = tabRepository;
    }

    @Override
    public ResponseEntity<?> createProject(ProjectRequest request) {
        if (request.getProjectOwner() == null || request.getName() == null)
            return ResponseEntity.badRequest().body("Empty projectOwner or name");

        // Создаем проект
        Project project = new Project();
        ProjectOwner owner = projectOwnerRepository.findById(request.getProjectOwner()).orElse(null);
        project.setName(request.getName());
        project.setProjectOwner(owner);
        project.setProjectOwnerType(request.getProjectOwnerType());
        repository.save(project);

        // Добавляем создателя к участникам проекта
        UserInProject userInProject = new UserInProject();
        userInProject.setProject(project);
        userInProject.setUsername(request.getPrincipalUser());
        userInProject.setRoleInProject(ProjectRole.CREATOR);
        userRepository.save(userInProject);

        List<UserInProject> team = new ArrayList<>();
        team.add(userInProject);
        project.setTeam(team);

        // Создаем начальную вкладку проекта
        Tab tab = new Tab();
        tab.setProject(project);
        tab.setName("Начальная вкладка");
        tabRepository.save(tab);

        return ResponseEntity.ok(project);
    }

    @Override
    public ResponseEntity<?> changeProject(Long projectId, ProjectRequest request) {
        Project project = repository.findById(projectId).orElse(null);
        if (project == null)
            return ResponseEntity.badRequest().body("Invalid projectId");
        if (request.getName().length() == 0)
            return ResponseEntity.badRequest().body("New project name is empty");

        project.setName(request.getName());

        return ResponseEntity.ok(repository.save(project));
    }

    @Override
    @Transactional
    public void deleteProject(Long projectId) {
//        userRepository.deleteByProject(projectId);
//        tabRepository.deleteByProject(projectId);
        repository.deleteById(projectId);
    }

    @Override
    public ResponseEntity<Project> getProject(Long id, String username) {
        Project project = repository.findById(id).orElse(null);
        boolean isMember = false;

        if (project != null) {
            List<UserInProject> team = project.getTeam();
            for (UserInProject userInProject : team) {
                if (userInProject.getUsername().equals(username))
                    isMember = true;
            }
        } else {
            return ResponseEntity.badRequest().body(null);
        }

        if (isMember) {
            return ResponseEntity.ok(project);
        } else {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @Override
    public ResponseEntity<List<Project>> getMyProjects(String username) {
        return ResponseEntity.ok(repository.getMyProjects(username));
    }

    @Override
    public ResponseEntity<List<Project>> getOtherProjects(String username) {
        return ResponseEntity.ok(repository.getOtherProjects(username));
    }

//    @Override
//    public ResponseEntity<?> getProjectsWithRole(String username, String role) {
//        return switch (role) {
//            case "participant" -> ResponseEntity.ok(repository.getProjectsWithRole(username, ProjectRole.Participant.toString()));
//            case "VIEWER" -> ResponseEntity.ok(repository.getProjectsWithRole(username, ProjectRole.Observer.toString()));
//            default -> null;
//        };
//    }

}
