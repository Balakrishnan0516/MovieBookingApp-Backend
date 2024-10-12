package com.ticketcounter.spring_boot_library.dao;

import com.ticketcounter.spring_boot_library.dto.SeatStatusResponse;
import com.ticketcounter.spring_boot_library.entity.BookedSeat;
import com.ticketcounter.spring_boot_library.entity.Seat;
import com.ticketcounter.spring_boot_library.entity.Show;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BookedSeatsRepository extends JpaRepository<BookedSeat, Integer> {

    @Query("SELECT b.status AS status, GROUP_CONCAT(DISTINCT bs.seat.seatNumber) AS seatNumbers " +
            "FROM BookedSeat bs " +
            "JOIN Booking b ON bs.show.id = b.show.id AND bs.user.id = b.user.id " +
            "WHERE bs.show.id = :showId " +
            "GROUP BY b.status")
        List<SeatStatusResponse> findSeatStatusByShowId(@Param("showId") Long showId);

    @Query("SELECT bs FROM BookedSeat bs JOIN FETCH bs.booking WHERE bs.user.id = :userId")
    List<BookedSeat> findBookedSeatsByUserId(@Param("userId") Long userId);

    @Transactional
    @Modifying
    @Query("DELETE FROM BookedSeat bs WHERE bs.booking.id = :bookingId")
    void deleteByBookingId(@Param("bookingId") Long bookingId);

    boolean existsBySeatAndShow(Seat seat, Show show);

}
