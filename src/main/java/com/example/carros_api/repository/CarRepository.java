package com.example.carros_api.repository;

import com.example.carros_api.model.Car;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarRepository extends JpaRepository<Car, Long> {
}

