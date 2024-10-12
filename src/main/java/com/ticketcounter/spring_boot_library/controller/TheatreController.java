package com.ticketcounter.spring_boot_library.controller;

import com.ticketcounter.spring_boot_library.entity.Theatre;
import com.ticketcounter.spring_boot_library.service.TheatreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.ticketcounter.spring_boot_library.dto.TheatreRequestDTO;

import java.util.List;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:3000") // frontend origin
public class TheatreController {

    @Autowired
    private TheatreService theatreService;

    @PostMapping("/add-theatre")
    public ResponseEntity<String> performAddTheatre(@RequestBody TheatreRequestDTO theatreRequestDTO){
        String responseMessage = theatreService.addTheatre(theatreRequestDTO);
        return ResponseEntity.ok(responseMessage);
    }

    @GetMapping("/theatres")
    public List<Theatre> getAllTheatres() {
        return theatreService.getAllTheatres();
    }
}
