package com.studentportal.controller;

import com.studentportal.dto.UserDto;
import com.studentportal.entity.User;
import com.studentportal.repository.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/admin")
@PreAuthorize("hasRole('ADMIN')")
public class AdminController {

    private final UserRepository userRepository;

    public AdminController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/students")
    public ResponseEntity<List<UserDto>> getAllStudents() {
        List<User> students = userRepository.findAll();
        List<UserDto> dtos = students.stream()
                .map(u -> new UserDto(u.getId(), u.getEmail(), u.getRole().getName()))
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }
}

