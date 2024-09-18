package com.ticketcounter.spring_boot_library.dao;

import com.ticketcounter.spring_boot_library.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);
    boolean existsByUsername(String username);

    User findById(long id);
}