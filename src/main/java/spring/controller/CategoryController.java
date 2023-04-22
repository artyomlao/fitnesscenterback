package spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import spring.entity.CategoryEntity;
import spring.exception.EntityNotFoundException;
import spring.service.CategoryService;

import java.util.List;

@RestController
@RequestMapping("/api/category")
public class CategoryController {

    private final CategoryService categoryService;

    @Autowired
    public CategoryController(final CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/list")
    public ResponseEntity<List<CategoryEntity>> list() {
        return ResponseEntity.ok(categoryService.list());
    }

    @GetMapping
    public ResponseEntity<CategoryEntity> byId(final @RequestParam Long id)
            throws EntityNotFoundException {

        return ResponseEntity.ok(categoryService.getById(id));
    }

    @PostMapping
    public ResponseEntity<CategoryEntity> add(final @RequestBody CategoryEntity categoryEntity) {
        return ResponseEntity.ok(categoryService.insertCategory(categoryEntity));
    }

    @PutMapping
    public ResponseEntity<CategoryEntity> update(
            final @RequestBody CategoryEntity categoryEntity)
            throws EntityNotFoundException {

        return ResponseEntity.ok(categoryService.updateCategory(categoryEntity));
    }

    @DeleteMapping
    public ResponseEntity<CategoryEntity> delete(final @RequestParam Long id) throws EntityNotFoundException {
        final CategoryEntity entity = categoryService.getById(id);
        categoryService.deleteCategory(id);
        return ResponseEntity.ok(entity);
    }
}
