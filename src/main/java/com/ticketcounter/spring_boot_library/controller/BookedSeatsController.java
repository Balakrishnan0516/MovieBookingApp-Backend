package com.ticketcounter.spring_boot_library.controller;

import com.ticketcounter.spring_boot_library.dto.SeatStatusResponse;
import com.ticketcounter.spring_boot_library.service.BookedSeatsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:3000") // frontend origin
public class BookedSeatsController {

    @Autowired
    private BookedSeatsService bookedSeatsService;

    @GetMapping("/booked-seats-status/{showId}")
    public List<SeatStatusResponse> getSeatStatusByShowId(@PathVariable Long showId) {
        return bookedSeatsService.getBookedSeats(showId);
    }
}
