package com.project.carcompany.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.carcompany.entities.Category;
import com.project.carcompany.repositories.CategoryRepository;

@Service
public class CategoryService {
  
  @Autowired
  private CategoryRepository categoryRepository;

  public List<Category> findAll(){
    return categoryRepository.findAll();
  }

  public Category findById(Long id){
    Optional<Category> category = categoryRepository.findById(id);
    return category.get();
  }

  public Category insert(Category category) {
    return categoryRepository.save(category);
  }
}
