package com.ticketcounter.spring_boot_library.dao;

import com.ticketcounter.spring_boot_library.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRepository extends JpaRepository<Movie, Long> {
}
