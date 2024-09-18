package com.ticketcounter.spring_boot_library.service;

import com.ticketcounter.spring_boot_library.dao.ShowRepository;
import com.ticketcounter.spring_boot_library.entity.Show;
import com.ticketcounter.spring_boot_library.entity.Theatre;
import com.ticketcounter.spring_boot_library.entity.User;
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
}
