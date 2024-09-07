package com.project.carcompany.resources;

import java.net.URI;
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

import com.project.carcompany.dto.CarDTO;
import com.project.carcompany.entities.Car;
import com.project.carcompany.resources.utils.URL;
import com.project.carcompany.services.CarService;
import com.project.carcompany.services.CategoryService;

@RestController
@RequestMapping(value="/cars")
public class CarResource {
  
  @Autowired
  private CarService carService;

  @Autowired
  private CategoryService categoryService;

  @GetMapping
  public ResponseEntity<List<Car>> findAll(){
    List<Car> cars = carService.findAll();
    return ResponseEntity.ok().body(cars);
  }

  @GetMapping(value="/searchall")
  public ResponseEntity<List<Car>> search(@RequestParam(value="text", defaultValue="") String text, @RequestParam(value="categoriesNames", defaultValue="") String categoriesNames, 
          @RequestParam(value="direction", defaultValue="") String direction, @RequestParam(value="parameter", defaultValue="") String parameter)
  {
    text = URL.decodeParam(text);
    List<Car> cars = carService.searchAll(text, categoriesNames, direction, parameter);
    return ResponseEntity.ok().body(cars);
  }

  @GetMapping(value="/{id}")
  public ResponseEntity<Car> findById(@PathVariable Long id){
    Car car = carService.findById(id);
    return ResponseEntity.ok().body(car);
  }

  @PostMapping
  public ResponseEntity<Car> insert(@RequestBody CarDTO carDto) {
    Car car = carService.fromDTO(carDto);
    Car newCar = carService.insert(car, carDto.getCategoriesIds());
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
