package com.ticketcounter.spring_boot_library.service;

import com.ticketcounter.spring_boot_library.dao.SeatRepository;
import com.ticketcounter.spring_boot_library.dao.ShowRepository;
import com.ticketcounter.spring_boot_library.dto.SeatProjection;
import com.ticketcounter.spring_boot_library.entity.Seat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SeatService {

    @Autowired
    private SeatRepository seatRepository;

    public List<SeatProjection> getSeatsForGivenShowId(Long showId){
        return seatRepository.findProjectedByShowId(showId);
    }

    public int getRateForGivenSeatIds(List<String> seatNumbers){
        List<Seat> seats = seatRepository.findBySeatNumberIn(seatNumbers);
        return seats.stream().mapToInt(seat -> Integer.parseInt(seat.getRate())).sum();
    }

}
