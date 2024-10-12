package com.ticketcounter.spring_boot_library.dto;

import lombok.Data;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;

@Data
public class ShowRequestDTO {

    private Long movieId;
    private Long theatreId;
    private LocalDate date;
    private LocalTime time;
}
