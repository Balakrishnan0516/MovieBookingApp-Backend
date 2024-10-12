package com.ticketcounter.spring_boot_library.service;

import com.ticketcounter.spring_boot_library.dao.MovieRepository;
import com.ticketcounter.spring_boot_library.dao.ShowRepository;
import com.ticketcounter.spring_boot_library.dao.TheatreRepository;
import com.ticketcounter.spring_boot_library.dto.ShowRequestDTO;
import com.ticketcounter.spring_boot_library.entity.Movie;
import com.ticketcounter.spring_boot_library.entity.Show;
import com.ticketcounter.spring_boot_library.entity.Theatre;
import com.ticketcounter.spring_boot_library.entity.User;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ShowService {

    @Autowired
    private ShowRepository showRepository;

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private TheatreRepository theatreRepository;

    public List<Show> getShowsByMovieId(Long movieId) {
        return showRepository.findByMovieId(movieId);
    }

    public List<Show> getShowsByTheatreId(Long theatreId) {
        return showRepository.findByTheatreId(theatreId);
    }

    public Map<Theatre, List<Show>> getShowsByTheatre(){
        List<Show> shows = showRepository.findAll();

        return shows.stream()
                .collect(Collectors.groupingBy(Show::getTheatre));
    }

    public Map<Theatre, List<Show>> getTheatresAndShowsByMovieId(Long movieId) {
        List<Show> shows = showRepository.findByMovieId(movieId);
        return shows.stream()
                .collect(Collectors.groupingBy(Show::getTheatre));
    }

    public Show getShowById(Long id) {
        Optional<Show> showOptional = showRepository.findById(id);
        return showOptional.orElseThrow(() -> new RuntimeException("Show not found"));
    }

    @Transactional
    public String addShow(ShowRequestDTO showRequestDTO) {
        Movie movie = movieRepository.findById(showRequestDTO.getMovieId()).orElse(null);
        Theatre theatre = theatreRepository.findById(showRequestDTO.getTheatreId()).orElse(null);

        if (movie == null || theatre == null) {
            return "Invalid movie ID or theatre ID.";
        }

        // Check if a show already exists for the same movie, theatre, date, and time
        Optional<Show> existingShow = showRepository.findByMovieAndTheatreAndDateAndTime(
                movie, theatre, showRequestDTO.getDate(), showRequestDTO.getTime());

        if (existingShow.isPresent()) {
            return "A show for this movie at this theatre on the specified date and time already exists.";
        }

        Show show = new Show();
        show.setMovie(movie);
        show.setTheatre(theatre);
        show.setDate(showRequestDTO.getDate());
        show.setTime(showRequestDTO.getTime());

        showRepository.save(show);
        return "Show added successfully.";
    }

    public List<Show> getAllShows() {
        return showRepository.findAll();
    }

    public Map<Movie, List<Show>> getMoviesAndShowsByTheatreId(Long theatreId) {
        // Fetch all shows for the given theatre
        List<Show> shows = showRepository.findByTheatreId(theatreId);

        // Group the shows by movie
        return shows.stream()
                .collect(Collectors.groupingBy(Show::getMovie));
    }

}
