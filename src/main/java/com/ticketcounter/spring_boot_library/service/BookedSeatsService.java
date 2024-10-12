package com.ticketcounter.spring_boot_library.service;

import com.ticketcounter.spring_boot_library.dao.BookedSeatsRepository;
import com.ticketcounter.spring_boot_library.dto.BookedSeatDTO;
import com.ticketcounter.spring_boot_library.dto.SeatStatusResponse;
import com.ticketcounter.spring_boot_library.entity.BookedSeat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookedSeatsService {

    @Autowired
    private BookedSeatsRepository bookedSeatsRepository;

    public List<SeatStatusResponse> getBookedSeats(Long showId){
        return bookedSeatsRepository.findSeatStatusByShowId(showId);
    }

    public List<BookedSeatDTO> getBookedSeatsByUserId(Long userId) {
        List<BookedSeat> bookedSeats = bookedSeatsRepository.findBookedSeatsByUserId(userId);
        return bookedSeats.stream().map(bs -> {
            BookedSeatDTO dto = new BookedSeatDTO();
            dto.setBookingId(bs.getBooking().getId());
            dto.setSeatId(bs.getSeat().getId());
            dto.setSeatNumber(bs.getSeat().getSeatNumber());
            dto.setMovieTitle(bs.getShow().getMovie().getTitle());
            dto.setTheatreName(bs.getShow().getTheatre().getName());
            dto.setShowTime(bs.getShow().getTime().toString());
            dto.setStatus(bs.getBooking().getStatus());
            return dto;
        }).collect(Collectors.toList());


    }
}
