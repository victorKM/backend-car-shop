package com.project.carcompany.services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.project.carcompany.entities.Car;
import com.project.carcompany.entities.Category;
import com.project.carcompany.repositories.CarRepository;
import com.project.carcompany.repositories.CategoryRepository;
import com.project.carcompany.services.exceptions.ResourceAlreadyExists;
import com.project.carcompany.services.exceptions.ResourceNotFoundException;

import jakarta.persistence.EntityNotFoundException;

@Service
public class CarService {
  
  @Autowired
  private CarRepository carRepository;

  @Autowired CategoryRepository categoryRepository;

  public List<Car> findAll() {
    return carRepository.findAll();
  }

  public List<Car> searchAll(String text, String categoriesNames, String direction, String parameter) {
    List<String> categoriesNamesList = new ArrayList<>();

    if("".equals(categoriesNames)) {
      List<Category> categories = categoryRepository.findAll();
      for (Category c : categories) {
        categoriesNamesList.add(c.getName());
      }
    }
    else {
      String[] fields = categoriesNames.split(",");
      categoriesNamesList.addAll(Arrays.asList(fields));
    }

    if("asc".equalsIgnoreCase(direction)) {
      return carRepository.searchAllIgnoreCase(text, categoriesNamesList, Sort.by(Direction.ASC, parameter.toLowerCase()));
    } 
    else if ("desc".equalsIgnoreCase(direction)) {
      return carRepository.searchAllIgnoreCase(text, categoriesNamesList, Sort.by(Direction.DESC, parameter.toLowerCase()));
    } else {
      return carRepository.searchAllIgnoreCase(text, categoriesNamesList, Sort.unsorted());
    }
  }

  public Car findById(Long id) {
    try {
      Optional<Car> car = carRepository.findById(id);
      return car.get();
    } catch (NoSuchElementException e) {
      throw new ResourceNotFoundException(id);
    }
  }

  public Car insert(Car car) {
    Car existingCar = carRepository.existCar(car.getName(), car.getCompany(), car.getImageUrl(),  car.getPrice());
    if (existingCar != null) {
      throw new ResourceAlreadyExists(existingCar.getName());
    } else {
      return carRepository.save(car);
    }
  }

  public Car update(Long id, Car car) {
    try {
      Car returnedCar = carRepository.getReferenceById(id);
      update(returnedCar, car);
      return carRepository.save(returnedCar);
    } catch (EntityNotFoundException e) {
      throw new ResourceNotFoundException(id);
    }
  }

  public void update(Car returnedCar, Car car) {
    returnedCar.setName(car.getName());
    returnedCar.setPrice(car.getPrice());
    returnedCar.setCompany(car.getCompany());
    returnedCar.setImageUrl(car.getImageUrl());
  }

  public void deleteById(Long id) {
    carRepository.deleteById(id);
  }
}
