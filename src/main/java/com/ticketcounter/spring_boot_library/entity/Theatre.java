package com.ticketcounter.spring_boot_library.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name="theatres")
@Data
public class Theatre {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @Column(nullable = false,name = "name")
    private String name;

    @Column(name="location")
    private String location;


}
