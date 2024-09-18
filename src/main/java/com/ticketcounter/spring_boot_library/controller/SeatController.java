package com.ticketcounter.spring_boot_library.controller;

import com.ticketcounter.spring_boot_library.dto.SeatProjection;
import com.ticketcounter.spring_boot_library.entity.Seat;
import com.ticketcounter.spring_boot_library.service.SeatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:3000") // frontend origin
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

    @PostMapping("/seats-totalRate")
    public int getTotalSeatRate(@RequestBody List<String> seatNumbers) {
        return seatService.getRateForGivenSeatIds(seatNumbers);
    }

}
