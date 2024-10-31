package com.ticketcounter.spring_boot_library.controller;

import com.ticketcounter.spring_boot_library.dao.BookedSeatsRepository;
import com.ticketcounter.spring_boot_library.dao.BookingRepository;
import com.ticketcounter.spring_boot_library.dto.BookedSeatDTO;
import com.ticketcounter.spring_boot_library.dto.SeatStatusResponse;
import com.ticketcounter.spring_boot_library.entity.BookedSeat;
import com.ticketcounter.spring_boot_library.service.BookedSeatsService;
import com.ticketcounter.spring_boot_library.service.BookingService;
import jakarta.mail.MessagingException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = {
        "http://balakrish-movie-booking-app.s3-website-ap-southeast-2.amazonaws.com",
        "http://localhost:3000"
}) // frontend origin
public class BookedSeatsController {

    @Autowired
    private BookedSeatsService bookedSeatsService;

    @Autowired
    private BookedSeatsRepository bookedSeatsRepository;

    @Autowired
    private BookingService bookingService;

    @GetMapping("/booked-seats-status/{showId}")
    public List<SeatStatusResponse> getSeatStatusByShowId(@PathVariable Long showId) {
        return bookedSeatsService.getBookedSeats(showId);
    }

    @GetMapping("/user/{userId}/seats")
    public List<BookedSeatDTO> getBookedSeats(@PathVariable Long userId) {
        return bookedSeatsService.getBookedSeatsByUserId(userId);
    }

    @CrossOrigin(origins = "http://balakrish-movie-booking-app.s3-website-ap-southeast-2.amazonaws.com")
    @Transactional
    @PutMapping("/cancel/{bookingId}")
    public ResponseEntity<?> cancelBooking(@PathVariable Long bookingId, Authentication authentication) throws MessagingException {
        if (authentication == null || !authentication.isAuthenticated()) {
            return new ResponseEntity<>("Unauthorized", HttpStatus.UNAUTHORIZED);
        }

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String username = userDetails.getUsername();

        bookingService.performCancelBooking(bookingId);
        bookedSeatsRepository.deleteByBookingId(bookingId);

        return new ResponseEntity<>("Booking Cancelled", HttpStatus.OK);
    }

}
