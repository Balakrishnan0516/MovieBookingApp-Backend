package com.ticketcounter.spring_boot_library.controller;

import com.ticketcounter.spring_boot_library.dto.SeatDTO;
import com.ticketcounter.spring_boot_library.dto.SeatProjection;
import com.ticketcounter.spring_boot_library.dto.ShowRequestDTO;
import com.ticketcounter.spring_boot_library.entity.Seat;
import com.ticketcounter.spring_boot_library.service.SeatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = {
        "http://balakrish-movie-booking-app.s3-website-ap-southeast-2.amazonaws.com",
        "http://localhost:3000"
}) // frontend origin
public class SeatController {

    @Autowired
    private SeatService seatService;

    @GetMapping("/show-seats/{showid}")
    public ResponseEntity<List<SeatProjection>> getSeat(@PathVariable Long showid) {

        List<SeatProjection> seats = seatService.getSeatsForGivenShowId(showid);
        if (seats == null || seats.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(seats);
    }

    @PostMapping("/seats-totalRate/{show_id}")
    public ResponseEntity<Integer> getTotalSeatRate(@RequestBody List<String> seatNumbers, @PathVariable Long show_id) {
        try {
            int totalRate = seatService.getRateForGivenSeatIds(seatNumbers, show_id);
            return ResponseEntity.ok(totalRate);
        } catch (Exception e) {
            // Handle any exceptions (e.g., invalid seat numbers or show ID)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @PostMapping("/add-seat")
    public ResponseEntity<?> addSeats(@RequestBody SeatDTO seatDTO) {
        try {
            List<Seat> seats = seatService.addSeats(seatDTO);
            return new ResponseEntity<>(seats, HttpStatus.CREATED);
        } catch (Exception e) {
            // Handle any exceptions and return an error response
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
