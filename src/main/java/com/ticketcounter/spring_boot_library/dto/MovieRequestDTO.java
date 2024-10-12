package com.ticketcounter.spring_boot_library.dto;

import lombok.Data;

@Data
public class MovieRequestDTO {
    private String title;
    private String genre;
    private String director;
    private String synopsis;
    private String youtubeUrl;
    private String horizontalImage;
    private String image;  
}
