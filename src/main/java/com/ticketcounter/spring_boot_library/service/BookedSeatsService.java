package com.ticketcounter.spring_boot_library.service;

import com.ticketcounter.spring_boot_library.dao.BookedSeatsRepository;
import com.ticketcounter.spring_boot_library.dto.SeatStatusResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookedSeatsService {

    @Autowired
    private BookedSeatsRepository bookedSeatsRepository;

    public List<SeatStatusResponse> getBookedSeats(Long showId){
        return bookedSeatsRepository.findSeatStatusByShowId(showId);
    }
}
