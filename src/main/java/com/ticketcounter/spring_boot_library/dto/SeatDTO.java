package com.ticketcounter.spring_boot_library.dto;

import lombok.Data;

import java.util.List;

@Data
public class SeatDTO {
    private Long theatreId; // Field for Theatre ID
    private Long showId;    // Field for Show ID
    private List<SeatDetail> seatDetails; // Field for List of SeatDetails

    // Getter for theatreId
    public Long getTheatreId() {
        return theatreId;
    }

    // Setter for theatreId
    public void setTheatreId(Long theatreId) {
        this.theatreId = theatreId;
    }

    // Getter for showId
    public Long getShowId() {
        return showId;
    }

    // Setter for showId
    public void setShowId(Long showId) {
        this.showId = showId;
    }

    // Getter for seatDetails
    public List<SeatDetail> getSeatDetails() {
        return seatDetails;
    }

    // Setter for seatDetails
    public void setSeatDetails(List<SeatDetail> seatDetails) {
        this.seatDetails = seatDetails;
    }

    @Data
    public static class SeatDetail {
        private String seatNumber; // Field for Seat Number
        private String category;    // Field for Seat Category
        private String rate;        // Field for Seat Rate

        // Getter for seatNumber
        public String getSeatNumber() {
            return seatNumber;
        }

        // Setter for seatNumber
        public void setSeatNumber(String seatNumber) {
            this.seatNumber = seatNumber;
        }

        // Getter for category
        public String getCategory() {
            return category;
        }

        // Setter for category
        public void setCategory(String category) {
            this.category = category;
        }

        // Getter for rate
        public String getRate() {
            return rate;
        }

        // Setter for rate
        public void setRate(String rate) {
            this.rate = rate;
        }
    }
}
