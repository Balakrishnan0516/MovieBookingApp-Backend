package com.ticketcounter.spring_boot_library.dao;

import com.ticketcounter.spring_boot_library.entity.Theatre;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TheatreRepository extends JpaRepository<Theatre, Long> {
}
