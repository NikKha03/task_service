package NikKha03.TaskService.service.impl;

import NikKha03.TaskService.model.Category;
import NikKha03.TaskService.repository.CategoryRepository;
import NikKha03.TaskService.service.CategoryService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository repository;

    @Override
    public Category findCategoryById(Long id) {
        return repository.findById(id).orElse(null);
    }

}
