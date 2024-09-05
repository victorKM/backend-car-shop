package com.project.carcompany.repositories;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.project.carcompany.entities.Car;

@Repository
public interface CarRepository extends JpaRepository<Car, Long>{
  
  @Query("select c from Car c where c.name like %?1% or c.company like  %?1%")
  Car searchIgnoreCase(String name);

  @Query("select c from Car c join c.categories cat where cat.name in ?1")
  List<Car> searchByCategory(List<String> categoriesNames);

  @Query("select c from Car c")
  List<Car> findOrderByIgnoreCase(Sort sort);
}
