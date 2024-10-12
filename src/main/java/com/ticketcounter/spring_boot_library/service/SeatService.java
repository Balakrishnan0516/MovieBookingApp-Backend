package com.ticketcounter.spring_boot_library.service;

import com.ticketcounter.spring_boot_library.dao.MovieRepository;
import com.ticketcounter.spring_boot_library.dao.SeatRepository;
import com.ticketcounter.spring_boot_library.dao.ShowRepository;
import com.ticketcounter.spring_boot_library.dao.TheatreRepository;
import com.ticketcounter.spring_boot_library.dto.SeatDTO;
import com.ticketcounter.spring_boot_library.dto.SeatProjection;
import com.ticketcounter.spring_boot_library.entity.Seat;
import com.ticketcounter.spring_boot_library.entity.Show;
import com.ticketcounter.spring_boot_library.entity.Theatre;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SeatService {

    @Autowired
    private SeatRepository seatRepository;

    @Autowired
    private ShowRepository showRepository;

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private TheatreRepository theatreRepository;

    public List<SeatProjection> getSeatsForGivenShowId(Long showId){
        return seatRepository.findProjectedByShowId(showId);
    }

    public int getRateForGivenSeatIds(List<String> seatNumbers, Long showId) {
        // Fetch seats by seat numbers and show ID
        List<Seat> seats = seatRepository.findBySeatNumberInAndShowId(seatNumbers, showId);

        // Sum up the rates
        return seats.stream()
                .mapToInt(seat -> Integer.parseInt(seat.getRate()))
                .sum();
    }


    @Transactional
    public List<Seat> addSeats(SeatDTO seatDTO) {
        // Find the theatre and show by their IDs
        Theatre theatre = theatreRepository.findById(seatDTO.getTheatreId()).orElseThrow(() -> new RuntimeException("Theatre not found"));
        Show show = showRepository.findById(seatDTO.getShowId()).orElseThrow(() -> new RuntimeException("Show not found"));

        List<Seat> seatsToAdd = new ArrayList<>();

        // Loop through the seat details and create Seat entities if they don't already exist
        for (SeatDTO.SeatDetail seatDetail : seatDTO.getSeatDetails()) {
            Optional<Seat> existingSeat = seatRepository.findByTheatreIdAndShowIdAndSeatNumber(seatDTO.getTheatreId(), seatDTO.getShowId(), seatDetail.getSeatNumber());

            if (existingSeat.isPresent()) {
                // If the seat exists, skip or update the details
                System.out.println("Seat already exists: " + seatDetail.getSeatNumber() + " for show " + seatDTO.getShowId() + " and theatre " + seatDTO.getTheatreId());
                // Optionally update the existing seat
                Seat seat = existingSeat.get();
                seat.setCategory(seatDetail.getCategory());
                seat.setRate(seatDetail.getRate());
                seatsToAdd.add(seat); // Updating the existing seat details
            } else {
                // If the seat does not exist, create a new one
                Seat seat = new Seat();
                seat.setTheatre(theatre);
                seat.setShow(show);
                seat.setSeatNumber(seatDetail.getSeatNumber());
                seat.setCategory(seatDetail.getCategory());
                seat.setRate(seatDetail.getRate());

                seatsToAdd.add(seat);
            }
        }

        // Save all new and updated seats to the database
        return seatRepository.saveAll(seatsToAdd);
    }
}
