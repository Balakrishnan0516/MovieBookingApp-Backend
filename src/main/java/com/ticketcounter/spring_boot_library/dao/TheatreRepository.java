package com.ticketcounter.spring_boot_library.dao;

import com.ticketcounter.spring_boot_library.entity.Theatre;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TheatreRepository extends JpaRepository<Theatre, Long> {
    Optional<Theatre> findByNameAndLocation(String name, String location);
}
