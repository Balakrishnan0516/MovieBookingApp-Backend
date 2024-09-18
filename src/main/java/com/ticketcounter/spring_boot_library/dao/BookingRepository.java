package com.ticketcounter.spring_boot_library.dao;

import com.ticketcounter.spring_boot_library.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingRepository extends JpaRepository<Booking, Long> {
}
