package com.carsmoviesinventory.app.Controllers;

import com.carsmoviesinventory.app.Entities.CarsMoviesEntity;
import com.carsmoviesinventory.app.Services.CarsMoviesService;
import jakarta.validation.Valid;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/carsmovies")
@Validated
public class CarsMoviesController {

    private final CarsMoviesService carsMoviesService;

    public CarsMoviesController(CarsMoviesService carsMoviesService) {
        this.carsMoviesService = carsMoviesService;
    }

    @GetMapping
    public ResponseEntity<?> getAllCarsMovies(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(defaultValue = "carMovieName,asc") String sort) {
        try {
            // Separamos el string recibido en dos partes: campo y dirección.
            String[] sortParams = sort.split(",");
            if(sortParams.length < 2){
                throw new IllegalArgumentException("El parámetro 'sort' debe tener el formato 'campo,direccion'.");
            }
            Pageable pageable = PageRequest.of(page, size, Sort.by(parseSort(sortParams)));
            return carsMoviesService.getAllMovies(pageable);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("Invalid sorting direction. Use 'asc' or 'desc'.");
        }
    }

    private Sort.Order parseSort(String[] sortParams) {
        String property = sortParams[0];
        String direction = sortParams[1].toLowerCase();

        List<String> validDirections = Arrays.asList("asc", "desc");
        if (!validDirections.contains(direction)) {
            throw new IllegalArgumentException("Invalid sort direction. Use 'asc' or 'desc'.");
        }
        return new Sort.Order(Sort.Direction.fromString(direction), property);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getCarsMovieById(@PathVariable UUID id){
        return carsMoviesService.getMoviesById(id);
    }

    @GetMapping("/search")
    public ResponseEntity<?> getMoviesByName(
            @RequestParam String movieName,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(defaultValue = "carMovieName,asc") String sort) {

        String[] sortParams = sort.split(",");
        Pageable pageable = PageRequest.of(page, size, Sort.by(parseSort(sortParams)));
        return carsMoviesService.getMoviesByName(movieName, pageable);
    }

    @PostMapping
    public ResponseEntity<?> insertCarsMovie(@Valid @RequestBody CarsMoviesEntity carsMoviesEntity){
        return carsMoviesService.addMovie(carsMoviesEntity);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateCarsMovie(@PathVariable UUID id, @Valid @RequestBody CarsMoviesEntity carsMoviesEntity){
        return carsMoviesService.updateMovie(id, carsMoviesEntity);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCarsMovie(@PathVariable UUID id){
        return carsMoviesService.deleteMovie(id);
    }
}
