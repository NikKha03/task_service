package NikKha03.TaskService.service.impl;

import NikKha03.TaskService.DTO.TabRequest;
import NikKha03.TaskService.model.Tab;
import NikKha03.TaskService.model.Project;
import NikKha03.TaskService.repository.TabRepository;
import NikKha03.TaskService.repository.ProjectRepository;
import NikKha03.TaskService.service.TabService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class TabServiceImpl implements TabService {

    private final TabRepository repository;

    private final ProjectRepository projectRepository;

    @Override
    public Tab findTabById(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public ResponseEntity<?> createTab(TabRequest request) {
        Project project = projectRepository.findById(request.getProjectId()).orElse(null);
        if (project == null) return ResponseEntity.badRequest().body("Invalid projectId");

        Tab tab = new Tab();
        tab.setName(request.getName());
        tab.setProject(project);

        return ResponseEntity.ok(repository.save(tab));
    }

    @Override
    public ResponseEntity<?> changeTab(Long tabId, TabRequest request) {
        if (!repository.existsById(tabId)) {
            return ResponseEntity.badRequest().body("Invalid tabId or projectId");
        }

        Tab tab = repository.findById(tabId).orElse(null);
        tab.setName(request.getName());

        return ResponseEntity.ok(repository.save(tab));
    }

    @Override
    public void deleteTab(Long tabId) {
        repository.deleteById(tabId);
    }

}
