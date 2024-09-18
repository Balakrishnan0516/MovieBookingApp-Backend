package com.ticketcounter.spring_boot_library.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name="shows")
@Data
public class Show {

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "movie_id", nullable = false)
    private Movie movie;

    @ManyToOne
    @JoinColumn(name="theatre_id", nullable = false)
    private Theatre theatre;

    @Column(name="date")
    private LocalDate date;

    @Column(name="time")
    private LocalTime time;

}
