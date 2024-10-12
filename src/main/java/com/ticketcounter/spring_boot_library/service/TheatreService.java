package com.ticketcounter.spring_boot_library.service;

import com.ticketcounter.spring_boot_library.dao.TheatreRepository;
import com.ticketcounter.spring_boot_library.dto.TheatreRequestDTO;
import com.ticketcounter.spring_boot_library.entity.Theatre;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

@Service
public class TheatreService {

    @Autowired
    private TheatreRepository theatreRepository;

    @Transactional
    public String addTheatre(TheatreRequestDTO theatreRequestDTO) {

        Optional<Theatre> existingTheatre = theatreRepository.findByNameAndLocation(
                theatreRequestDTO.getName(), theatreRequestDTO.getLocation());

        if (existingTheatre.isPresent()) {
            return "Theatre with the same name and location already exists.";
        }

        Theatre theatre = new Theatre();
        theatre.setName(theatreRequestDTO.getName());
        theatre.setLocation(theatreRequestDTO.getLocation());

         theatreRepository.save(theatre);
        return "Theatre added successfully.";
    }

    public List<Theatre> getAllTheatres() {
        return theatreRepository.findAll();
    }

}
