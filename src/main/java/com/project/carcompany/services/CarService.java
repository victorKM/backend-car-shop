package com.project.carcompany.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.project.carcompany.entities.Car;
import com.project.carcompany.repositories.CarRepository;
import com.project.carcompany.services.exceptions.ResourceNotFoundException;

import jakarta.persistence.EntityNotFoundException;

@Service
public class CarService {
  
  @Autowired
  private CarRepository carRepository;

  public List<Car> findAll() {
    return carRepository.findAll();
  }

  public Car search(String text) {
    return carRepository.searchIgnoreCase(text);
  }

  public List<Car> searchByCategory(List<String> categoriesNames) {
    return carRepository.searchByCategory(categoriesNames);
  }

  public List<Car> findOrderByName() {
    return carRepository.findOrderByIgnoreCase(Sort.by(Direction.ASC, "name"));
  }

  public List<Car> findOrderByPrice() {
    return carRepository.findOrderByIgnoreCase(Sort.by(Direction.DESC, "price"));
  }

  public Car findById(Long id) {
    Optional<Car> car = carRepository.findById(id);
    return car.get();
  }

  public Car insert(Car car) {
    return carRepository.save(car);
  }

  public Car update(Long id, Car car) {
    try {
      Car returnedCar = carRepository.getReferenceById(id);
      update(returnedCar, car);
      return carRepository.save(returnedCar);
    } catch (EntityNotFoundException e) {
      throw new ResourceNotFoundException(e);
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
