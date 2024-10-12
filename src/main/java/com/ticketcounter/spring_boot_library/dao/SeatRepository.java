package com.ticketcounter.spring_boot_library.dao;

import com.ticketcounter.spring_boot_library.dto.SeatProjection;
import com.ticketcounter.spring_boot_library.entity.Seat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SeatRepository extends JpaRepository<Seat, Long> {

        @Query("SELECT DISTINCT s AS seatProjection FROM Seat s WHERE s.show.id = :showId")
        List<SeatProjection> findProjectedByShowId(@Param("showId") Long showId);

        List<Seat> findBySeatNumberIn(List<String> seatNumbers);
        Optional<Seat> findById(Long id);
        Optional<Seat> findBySeatNumber(String seatNumber);

        @Query("SELECT s FROM Seat s WHERE s.seatNumber IN :seatNumbers AND s.show.id = :showId")
        List<Seat> findBySeatNumberInAndShowId(@Param("seatNumbers") List<String> seatNumbers, @Param("showId") Long showId);

        Optional<Seat> findByTheatreIdAndShowIdAndSeatNumber(Long theatreId, Long showId, String seatNumber);
}
