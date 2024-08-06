package com.telran.libraryapp.service;

import com.telran.libraryapp.entity.Category;
import com.telran.libraryapp.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    private final CategoryRepository repository;

    @Autowired
    public CategoryService(CategoryRepository repository) {
        this.repository = repository;
    }

    public List<Category> getAll() {
        return repository.findAll();
    }

    public Optional<Category> getByName(String name) {
        return repository.findCategoriesByName(name);
    }

    public Category updateCategory(Category category) {
        Optional<Category> optional = repository.findById(category.getId());
        if (optional.isPresent()) {
            return repository.save(category);
        } else {
            return null;
        }
    }

    public void deleteCategory(Long id) {
        repository.deleteById(id);
    }

    public Category add(Category category) {
        return repository.save(category);
    }

}
