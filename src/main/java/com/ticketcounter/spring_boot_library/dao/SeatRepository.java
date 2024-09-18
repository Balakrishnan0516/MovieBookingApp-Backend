package com.ticketcounter.spring_boot_library.dao;

import com.ticketcounter.spring_boot_library.dto.SeatProjection;
import com.ticketcounter.spring_boot_library.entity.Seat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SeatRepository extends JpaRepository<Seat, Long> {

        List<SeatProjection> findProjectedByShowId(Long showId);
        List<Seat> findBySeatNumberIn(List<String> seatNumbers);
        Optional<Seat> findById(Long id);
        Optional<Seat> findBySeatNumber(String seatNumber);
}
