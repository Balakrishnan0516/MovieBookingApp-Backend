package com.ticketcounter.spring_boot_library.dao;

import com.ticketcounter.spring_boot_library.entity.Movie;
import com.ticketcounter.spring_boot_library.entity.Show;
import com.ticketcounter.spring_boot_library.entity.Theatre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface ShowRepository extends JpaRepository<Show, Long> {

    List<Show> findByMovieId(Long movieId);

    List<Show> findByTheatreId(Long theatreId);

    Optional<Show> findByMovieAndTheatreAndDateAndTime(Movie movie, Theatre theatre, LocalDate date, LocalTime time);



}
