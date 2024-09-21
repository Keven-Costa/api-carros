package com.example.carros_api.controller;


import com.example.carros_api.model.Car;
import com.example.carros_api.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cars")
public class CarController {

    @Autowired
    private CarService carService;

    @GetMapping
    public List<Car> getAllCars() {
        return carService.getAllCars();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Car> getCarById(@PathVariable Long id) {
        Car car = carService.getCarById(id);
        if (car != null) {
            return ResponseEntity.ok(car);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public Car createCar(@RequestBody Car car) {
        return carService.saveCar(car);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Car> updateCar(@PathVariable Long id, @RequestBody Car carDetails) {
        Car car = carService.getCarById(id);
        if (car != null) {
            car.setMarca(carDetails.getMarca());
            car.setModelo(carDetails.getModelo());
            car.setAno(carDetails.getAno());
            car.setPreco(carDetails.getPreco());
            car.setPotencia(carDetails.getPotencia());
            return ResponseEntity.ok(carService.saveCar(car));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCar(@PathVariable Long id) {
        carService.deleteCar(id);
        return ResponseEntity.noContent().build();
    }
}
