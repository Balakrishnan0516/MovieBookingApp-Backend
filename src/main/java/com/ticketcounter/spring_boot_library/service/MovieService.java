package com.ticketcounter.spring_boot_library.service;

import com.ticketcounter.spring_boot_library.dao.MovieRepository;
import com.ticketcounter.spring_boot_library.dto.MovieRequestDTO;
import com.ticketcounter.spring_boot_library.entity.Movie;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Base64;
import java.util.Optional;

@Service
public class MovieService {

    @Autowired
    private MovieRepository movieRepository;

    @Transactional
    public Movie addMovie(MovieRequestDTO movierequestDTO) {
        Movie movie = new Movie();
        movie.setTitle(movierequestDTO.getTitle());
        movie.setDirector(movierequestDTO.getDirector());
        movie.setGenre(movierequestDTO.getGenre());
        movie.setSynopsis(movierequestDTO.getSynopsis());
        movie.setYoutubeUrl(movierequestDTO.getYoutubeUrl());

        if (movierequestDTO.getImage() != null) {
            byte[] imageBytes = Base64.getDecoder().decode(movierequestDTO.getImage());
            movie.setImage(imageBytes);
        }

        return movieRepository.save(movie);
    }

    @Transactional
    public Movie patchMovie(Long id, MovieRequestDTO movierequestDTO) {
        Optional<Movie> optionalMovie = movieRepository.findById(id);
        if (optionalMovie.isPresent()) {
            Movie movie = optionalMovie.get();

            if (movierequestDTO.getTitle() != null) {
                movie.setTitle(movierequestDTO.getTitle());
            }
            if (movierequestDTO.getGenre() != null) {
                movie.setGenre(movierequestDTO.getGenre());
            }
            if (movierequestDTO.getDirector() != null) {
                movie.setDirector(movierequestDTO.getDirector());
            }
            if (movierequestDTO.getSynopsis() != null) {
                movie.setSynopsis(movierequestDTO.getSynopsis());
            }
            if (movierequestDTO.getYoutubeUrl() != null) {
                movie.setYoutubeUrl(movierequestDTO.getYoutubeUrl());
            }
            if (movierequestDTO.getImage() != null) {
                byte[] imageBytes = Base64.getDecoder().decode(movierequestDTO.getImage());
                movie.setImage(imageBytes);
            }
            if (movierequestDTO.getHorizontalImage() != null) {
                byte[] horizontalImageBytes = Base64.getDecoder().decode(movierequestDTO.getHorizontalImage());
                movie.setHorizontalImage(horizontalImageBytes);
            }

            return movieRepository.save(movie);
        }
        return null; // or throw an exception
    }

}
