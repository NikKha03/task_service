package NikKha03.TaskService.service.impl;

import NikKha03.TaskService.DTO.CategoryRequest;
import NikKha03.TaskService.model.Category;
import NikKha03.TaskService.model.Project;
import NikKha03.TaskService.repository.CategoryRepository;
import NikKha03.TaskService.repository.ProjectRepository;
import NikKha03.TaskService.service.CategoryService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository repository;

    private final ProjectRepository projectRepository;

    @Override
    public Category findCategoryById(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public ResponseEntity<?> createCategory(CategoryRequest request) {
        Project project = projectRepository.findById(request.getProjectId()).orElse(null);
        if (project == null) return ResponseEntity.badRequest().body("Invalid projectId");

        Category category = new Category();
        category.setName(request.getName());
        category.setProject(project);

        return ResponseEntity.ok(repository.save(category));
    }

    @Override
    public ResponseEntity<?> changeCategory(Long categoryId, CategoryRequest request) {
        if (!repository.existsById(categoryId) || !projectRepository.existsById(request.getProjectId())) {
            return ResponseEntity.badRequest().body("Invalid categoryId or projectId");
        }

        Category category = repository.findById(categoryId).orElse(null);
        Project project = projectRepository.findById(request.getProjectId()).orElse(null);

        category.setName(request.getName());
        category.setProject(project);

        return ResponseEntity.ok(repository.save(category));
    }

    @Override
    public void deleteCategory(Long categoryId) {
        repository.deleteById(categoryId);
    }

}
