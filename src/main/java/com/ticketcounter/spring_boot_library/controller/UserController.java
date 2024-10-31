package com.ticketcounter.spring_boot_library.controller;

import com.ticketcounter.spring_boot_library.authorization.JWTUtil;
import com.ticketcounter.spring_boot_library.entity.User;
import com.ticketcounter.spring_boot_library.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = {
        "http://balakrish-movie-booking-app.s3-website-ap-southeast-2.amazonaws.com",
        "http://localhost:3000"
}) // frontend origin
public class UserController {

    private final AuthenticationManager authenticationManager;
    private final UserService userService;
    private final JWTUtil jwtUtil;

    @Autowired
    public UserController(@Lazy AuthenticationManager authenticationManager, UserService userService, JWTUtil jwtUtil) {
        this.authenticationManager = authenticationManager;
        this.userService = userService;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody User user) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
            final UserDetails userDetails = userService.loadUserByUsername(userService.getUserByUsername(user.getUsername()).getUsername());
            final String jwt = jwtUtil.generateToken(userDetails.getUsername());
            return ResponseEntity.ok(jwt);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials"); // Ensure appropriate status
        }
    }

    @PostMapping("/signup")
    public ResponseEntity<String> signup(@Valid @RequestBody User user) {
        if(userService.existsByUsername(user.getUsername())) {
            return ResponseEntity.badRequest().body("Username is already taken");
         }
        userService.saveUser(user);
        return ResponseEntity.ok("User created successfully");
        }

    @GetMapping("/users/{username}")
    public Long getUserIdByUsername(@PathVariable String username) {
        User user = userService.getUserByUsername(username);
        if (user != null) {
            return user.getId();
        } else {
            throw new RuntimeException("User not found");
        }
    }
}