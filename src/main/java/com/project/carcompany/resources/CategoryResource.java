package com.project.carcompany.resources;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.project.carcompany.entities.Category;
import com.project.carcompany.services.CategoryService;

@RestController
@RequestMapping("/categories")
public class CategoryResource {
  
  @Autowired
  private CategoryService categoryService;

  @GetMapping
  public ResponseEntity<List<Category>> findAll() {
    List<Category> categories = categoryService.findAll();
    return ResponseEntity.ok().body(categories);
  }

  @GetMapping(value="/{id}")
  public ResponseEntity<Category> findById(@PathVariable Long id) {
    Category category = categoryService.findById(id);
    return ResponseEntity.ok().body(category);
  }

  @PostMapping
  public ResponseEntity<Category> insert(@RequestBody Category category){
    Category newCategory = categoryService.insert(category);
    URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newCategory.getId()).toUri();
    return ResponseEntity.created(uri).body(newCategory);
  }

}
