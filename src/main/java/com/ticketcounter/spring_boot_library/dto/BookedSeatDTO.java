package com.ticketcounter.spring_boot_library.dto;

import lombok.Data;

@Data
public class BookedSeatDTO {
    private Long bookingId;
    private Long seatId;
    private String seatNumber;
    private String movieTitle;
    private String theatreName;
    private String showTime;
    private String status;
}
