package com.ticketcounter.spring_boot_library.dto;

import lombok.Data;

import java.util.List;

@Data
public class SeatDTO {
    private Long theatreId;
    private Long showId;
    private List<SeatDetail> seatDetails;

    @Data
    public static class SeatDetail {
        private String seatNumber;
        private String category;
        private String rate;
    }
}
