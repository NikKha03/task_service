package NikKha03.TaskService.controllers;

import NikKha03.TaskService.DTO.CategoryRequest;
import NikKha03.TaskService.service.CategoryService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@Tag(name = "category_controller")
@RestController
@RequestMapping("/task_service/category")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping("/create")
    public ResponseEntity<?> createCategory(@RequestBody CategoryRequest request) {
        return categoryService.createCategory(request);
    }

    @Transactional
    @PutMapping("/change/{categoryId}")
    public ResponseEntity<?> changeCategory(@PathVariable("categoryId") Long categoryId, @RequestBody CategoryRequest request) {
        return categoryService.changeCategory(categoryId, request);
    }

    @Transactional
    @DeleteMapping("/delete/{categoryId}")
    public void deleteCategory(@PathVariable("categoryId") Long categoryId) {
        categoryService.deleteCategory(categoryId);
    }


}
