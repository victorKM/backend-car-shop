package com.project.carcompany.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.carcompany.entities.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long>{
  
}
