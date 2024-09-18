package com.ticketcounter.spring_boot_library.dto;

import java.util.List;

public interface SeatStatusResponse {
     String getStatus();
     List<String> getSeatNumbers();

}
