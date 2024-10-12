package com.ticketcounter.spring_boot_library.dao;

import com.ticketcounter.spring_boot_library.entity.Booking;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface BookingRepository extends JpaRepository<Booking, Long> {
    @Modifying
    @Transactional
    @Query("UPDATE Booking b SET b.status = 'Cancelled' WHERE b.id = :bookingId")
    void cancelBooking(@Param("bookingId") Long bookingId);

}
