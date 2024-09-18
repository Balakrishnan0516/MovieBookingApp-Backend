package com.ticketcounter.spring_boot_library.controller;

import com.ticketcounter.spring_boot_library.entity.Show;
import com.ticketcounter.spring_boot_library.entity.Theatre;
import com.ticketcounter.spring_boot_library.service.ShowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:3000") // frontend origin
public class ShowController {

    @Autowired
    private ShowService showService;

    @GetMapping("/theatre-shows") //http://localhost:8080/api/theatre-shows
    public ResponseEntity<Map<Theatre, List<Show>>> getTheatresShows() {
        Map<Theatre, List<Show>> theatreShows = showService.getShowsByTheatre();
        return ResponseEntity.ok(theatreShows);
    }

    @GetMapping("/theatre-shows-by-movie/{movieid}") //http://localhost:8080/api/theatre-shows-by-movie/1
    public ResponseEntity<Map<Theatre, List<Show>>> getTheatresShows(@PathVariable Long movieid) {
        Map<Theatre, List<Show>> theatreShowsForaMovie = showService.getTheatresAndShowsByMovieId(movieid);
        return ResponseEntity.ok(theatreShowsForaMovie);
    }


}
