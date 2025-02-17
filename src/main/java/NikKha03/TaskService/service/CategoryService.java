package NikKha03.TaskService.service;

import NikKha03.TaskService.DTO.CategoryRequest;
import NikKha03.TaskService.model.Category;
import org.springframework.http.ResponseEntity;

public interface CategoryService {

    Category findCategoryById(Long id);

    ResponseEntity<?> createCategory(CategoryRequest request);

    ResponseEntity<?> changeCategory(Long categoryId, CategoryRequest request);

    void deleteCategory(Long categoryId);

}
