package com.project.carcompany.resources;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.project.carcompany.entities.Car;
import com.project.carcompany.entities.Category;
import com.project.carcompany.services.CarService;

@RestController
@RequestMapping(value="/cars")
public class CarResource {
  
  @Autowired
  private CarService carService;

  @GetMapping
  public ResponseEntity<List<Car>> findAll(){
    List<Car> cars = carService.findAll();
    return ResponseEntity.ok().body(cars);
  }

  @GetMapping(value="/searchbycategory")
  public ResponseEntity<List<Car>> searchByCategory(@RequestBody List<Category> categories){
    List<String> categoriesNames = new ArrayList<>();
    for(Category c : categories){
      categoriesNames.add(c.getName());
    }
    List<Car> cars = carService.searchByCategory(categoriesNames);
    return ResponseEntity.ok().body(cars);
  }

  @GetMapping(value="/orderbyname")
  public ResponseEntity<List<Car>> findOrderByName(){
    List<Car> cars = carService.findOrderByName();
    return ResponseEntity.ok().body(cars);
  }

  @GetMapping(value="/orderbyprice")
  public ResponseEntity<List<Car>> findOrderByPrice(){
    List<Car> cars = carService.findOrderByPrice();
    return ResponseEntity.ok().body(cars);
  }

  @GetMapping(value="/search")
  public ResponseEntity<Car> search(@RequestParam(value="text", defaultValue="") String text){
    Car cars = carService.search(text);
    return ResponseEntity.ok().body(cars);
  }

  @GetMapping(value="/{id}")
  public ResponseEntity<Car> findById(@PathVariable Long id){
    Car car = carService.findById(id);
    return ResponseEntity.ok().body(car);
  }

  @PostMapping
  public ResponseEntity<Car> insert(@RequestBody Car car) {
    Car newCar =  carService.insert(car);
    URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newCar.getId()).toUri();
    return ResponseEntity.created(uri).body(newCar);
  }

  @PutMapping(value="/{id}")
  public ResponseEntity<Car> update(@PathVariable Long id, @RequestBody Car car) {
    Car updatedCar = carService.update(id, car);
    return ResponseEntity.ok().body(updatedCar);
  }

  @DeleteMapping(value="/{id}")
  public ResponseEntity<Car> deleteById(@PathVariable Long id) {
    carService.deleteById(id);
    return ResponseEntity.noContent().build();
  }
}
