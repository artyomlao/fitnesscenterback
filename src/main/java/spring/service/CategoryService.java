package spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spring.entity.CategoryEntity;
import spring.exception.EntityNotFoundException;
import spring.repository.CategoryRepository;

import java.util.List;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryService(final CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List<CategoryEntity> list() {
        return categoryRepository.findAll();
    }

    public CategoryEntity getById(final Long id) throws EntityNotFoundException {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(CategoryEntity.class, "No category with such id: " + id));
    }

    public CategoryEntity insertCategory(final CategoryEntity categoryEntity) {
        return categoryRepository.save(categoryEntity);
    }

    public CategoryEntity updateCategory(final CategoryEntity categoryEntity) {

    }

    public void deleteCategory(final Long id) {
        categoryRepository.deleteById(id);
    }
}
