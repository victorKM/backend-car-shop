package com.project.carcompany.repositories;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.project.carcompany.entities.Car;

@Repository
public interface CarRepository extends JpaRepository<Car, Long>{
  
  
  @Query("SELECT c FROM Car c LEFT JOIN c.categories cat WHERE (c.name LIKE %?1% OR c.company LIKE  %?1%) AND (cat.name IN ?2)")
  List<Car> searchAllIgnoreCase(String text, List<String> categoriesNames, Sort sort);
  
  // @Query(value = "SELECT * FROM tb_car WHERE name LIKE ?1 AND company LIKE ?2 AND image_url LIKE ?3 AND price = ?4 LIMIT 1",nativeQuery = true)
  // Car existCar(String name, String company, String imageUrl, Double price);

  @Query(value = "SELECT car.*, cc.*, cat.* FROM tb_car car LEFT JOIN tb_car_category cc ON car.id = cc.car_id LEFT JOIN tb_category cat ON cc.category_id = cat.id WHERE (car.name LIKE CONCAT('%', ?1, '%') OR car.company LIKE CONCAT('%', ?1, '%')) AND (cat.name IN (?2))", nativeQuery = true)
  Car existCar(String name, String company, String imageUrl, Double price);
}