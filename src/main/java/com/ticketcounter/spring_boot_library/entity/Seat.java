package com.ticketcounter.spring_boot_library.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name="seats")
@Data
public class Seat {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name="theatre_id",nullable = false)
    private Theatre theatre;

    @ManyToOne
    @JoinColumn(name="show_id")
    private Show show;

    @Column(name="seat_number",nullable = false)
    private String seatNumber;

    @Column(name="category",nullable = false)
    private String category;

    @Column(name="rate",nullable = false)
    private String rate;
}
