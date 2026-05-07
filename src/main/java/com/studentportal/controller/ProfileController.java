package com.studentportal.controller;

import com.studentportal.dto.GradeDto;
import com.studentportal.dto.UserDto;
import com.studentportal.entity.Grade;
import com.studentportal.entity.User;
import com.studentportal.repository.GradeRepository;
import com.studentportal.repository.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/profile")
public class ProfileController {

    private final UserRepository userRepository;
    private final GradeRepository gradeRepository;

    public ProfileController(UserRepository userRepository, GradeRepository gradeRepository) {
        this.userRepository = userRepository;
        this.gradeRepository = gradeRepository;
    }

    @GetMapping("/me")
    public ResponseEntity<UserDto> getMyProfile(Authentication authentication) {
        String email = authentication.getName();
        User user = userRepository.findByEmail(email).orElseThrow();
        return ResponseEntity.ok(new UserDto(user.getId(), user.getEmail(), user.getRole().getName()));
    }

    @GetMapping("/grades")
    public ResponseEntity<List<GradeDto>> getMyGrades(Authentication authentication) {
        // IDOR Protection: We rely on the Authentication object (backend info) instead of path parameters!
        String email = authentication.getName();
        User user = userRepository.findByEmail(email).orElseThrow();

        List<Grade> grades = gradeRepository.findByStudentId(user.getId());
        List<GradeDto> dtos = grades.stream()
                .map(g -> new GradeDto(g.getCourse().getName(), g.getScore()))
                .collect(Collectors.toList());

        return ResponseEntity.ok(dtos);
    }
}

