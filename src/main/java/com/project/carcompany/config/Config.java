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

    Car c1 = new Car(null, "296 GTB", "Ferrari", 3350000.00, "https://wallpapers.com/images/hd/ferrari-296-gtb-4k-laptop-car-a49rcj1e2ax2er90.jpg");
    Car c2 = new Car(null, "Cayenne 2025", "Porsche", 1140000.00, "https://cdn.motor1.com/images/mgl/8AvgvB/s1/2025-porsche-cayenne-gts.jpg");
    Car c3 = new Car(null, "Mustang", "Ford", 486000.00, "https://w.wallhaven.cc/full/zx/wallhaven-zxlm8o.jpg");

    categoryRepository.saveAll(Arrays.asList(cat1,cat2,cat3));
    carRepository.saveAll(Arrays.asList(c1,c2,c3));

    c1.getCategories().add(cat2);
    c1.getCategories().add(cat3);
    c2.getCategories().add(cat1);
    c3.getCategories().add(cat2);

    carRepository.saveAll(Arrays.asList(c1,c2,c3));
  }
}
