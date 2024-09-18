package com.ticketcounter.spring_boot_library.dao;

import com.ticketcounter.spring_boot_library.dto.SeatStatusResponse;
import com.ticketcounter.spring_boot_library.entity.BookedSeat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BookedSeatsRepository extends JpaRepository<BookedSeat, Integer> {

    @Query("SELECT b.status AS status, GROUP_CONCAT(bs.seat.seatNumber) AS seatNumbers " +
            "FROM BookedSeat bs " +
            "JOIN Booking b ON bs.show.id = b.show.id AND bs.user.id = b.user.id " +
            "WHERE bs.show.id = :showId " +
            "GROUP BY b.status")
        List<SeatStatusResponse> findSeatStatusByShowId(@Param("showId") Long showId);

}