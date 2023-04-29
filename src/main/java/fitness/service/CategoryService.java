package fitness.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import fitness.entity.CategoryEntity;
import fitness.exception.EntityNotFoundException;
import fitness.repository.CategoryRepository;

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

    public CategoryEntity updateCategory(final CategoryEntity entityToUpdate)
            throws EntityNotFoundException {

        final CategoryEntity oldEntity = categoryRepository.findById(entityToUpdate.getId())
                .orElseThrow(() -> new EntityNotFoundException(CategoryEntity.class, "No such entity to update"));

        return categoryRepository.save(oldEntity.setId(entityToUpdate.getId())
                .setName(entityToUpdate.getName())
                .setDescription(entityToUpdate.getDescription()));
    }

    public void deleteCategory(final Long id) {
        categoryRepository.deleteById(id);
    }
}
