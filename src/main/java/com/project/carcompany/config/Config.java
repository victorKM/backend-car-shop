package com.project.carcompany.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.project.carcompany.entities.Car;
import com.project.carcompany.entities.Category;
import com.project.carcompany.repositories.CarRepository;
import com.project.carcompany.repositories.CategoryRepository;

@Configuration
public class Config implements CommandLineRunner{
  
  @Autowired
  private CarRepository carRepository;

  @Autowired 
  private CategoryRepository categoryRepository;
  
  @Override
  public void run(String... args) throws Exception {

    carRepository.deleteAll();
    categoryRepository.deleteAll();

    Category cat1 = new Category(null, "SUV");
    Category cat2 = new Category(null, "Sport");
    Category cat3 = new Category(null, "Luxury");

    Car c1 = new Car(null, "Ferrari 296 GTB", "Ferrari", 3350000.00, "");
    Car c2 = new Car(null, "Porsche Cayenne 2025", "Porsche", 1140000.00, "");
    Car c3 = new Car(null, "Ford Mustang", "Ford", 486000.00, "");

    categoryRepository.saveAll(Arrays.asList(cat1,cat2,cat3));
    carRepository.saveAll(Arrays.asList(c1,c2,c3));

    c1.getCategories().add(cat2);
    c1.getCategories().add(cat3);
    c2.getCategories().add(cat1);
    c3.getCategories().add(cat2);

    carRepository.saveAll(Arrays.asList(c1,c2,c3));
  }
}
