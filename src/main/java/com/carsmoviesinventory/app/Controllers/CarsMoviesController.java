package com.carsmoviesinventory.app.Controllers;

import com.carsmoviesinventory.app.Entities.CarsMoviesEntity;
import com.carsmoviesinventory.app.Services.CarsMoviesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/carsmovies")
public class CarsMoviesController{

    @Autowired
    private CarsMoviesService carsMoviesService;

    @GetMapping
    public ResponseEntity<Map<String, Object>> getAllCarsMovies(){
        return carsMoviesService.getAllMovies();
    }

    @GetMapping("/{id}")
    public String getCarsMovieById(){
        return "Oye mi perro";
    }

    @PostMapping
    public String insertCarsMovie(){
        return "Oye mi perro";
    }

    @PutMapping("/{id}")
    public String updateCarsMovie(){
        return "Oye mi perro";
    }

    @DeleteMapping("/{id}")
    public String deleteCarsMovie(){
        return "Oye mi perro";
    }

}