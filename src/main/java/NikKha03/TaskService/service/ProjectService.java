package NikKha03.TaskService.service;

import NikKha03.TaskService.DTO.ProjectRequest;
import org.springframework.http.ResponseEntity;

public interface ProjectService {

    ResponseEntity<?> createProject(ProjectRequest request);

    ResponseEntity<?> changeProject(Long projectId, ProjectRequest request);

    void deleteProject(Long projectId);

    ResponseEntity<?> getMyProjects(String username);

    ResponseEntity<?> getProjectsWithRole(String username, String role);

}
