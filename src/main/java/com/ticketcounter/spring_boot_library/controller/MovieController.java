package com.ticketcounter.spring_boot_library.controller;

import com.ticketcounter.spring_boot_library.dto.MovieRequestDTO;
import com.ticketcounter.spring_boot_library.entity.Movie;
import com.ticketcounter.spring_boot_library.service.MovieService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins ={
        "http://balakrish-movie-booking-app.s3-website-ap-southeast-2.amazonaws.com",
        "http://localhost:3000"
}) // frontend origin
public class MovieController {

    @Autowired
    private MovieService movieService;

    @Transactional
    @PostMapping("/add-movie")
    public ResponseEntity<Movie> performAddMovie(@RequestBody MovieRequestDTO movierequestDTO) {
        Movie movie = movieService.addMovie(movierequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(movie);
    }

    @PatchMapping("/patch/{id}")
    public ResponseEntity<Movie> patchMovie(@PathVariable Long id, @RequestBody MovieRequestDTO movieRequestDTO) {
        Movie updatedMovie = movieService.patchMovie(id, movieRequestDTO);
        if (updatedMovie != null) {
            return ResponseEntity.ok(updatedMovie);
        }
        return ResponseEntity.notFound().build();
    }
}
