package com.ticketcounter.spring_boot_library.entity;
import lombok.Data;
import jakarta.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="movies")
@Data //lombok automatic getters and setters handler
public class Movie implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @Column(nullable = false, length = 255,name="title")
    private String title;

    @Column(length = 255,name="genre")
    private String genre;

    @Column(length = 255,name="director")
    private String director;

    @Lob //Large Object
    @Column(length = 255,name="synopsis")
    private String synopsis;

    @Lob
    @Column(length = 255,name="image")
    private byte[] image;

    @Lob
    @Column(length = 255,name="horizontal_image")
    private byte[] horizontalImage;

    @Column(length = 255,name="youtube_url")
    private String youtubeUrl;

}
