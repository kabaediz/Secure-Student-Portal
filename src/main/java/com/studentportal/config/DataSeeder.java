package com.studentportal.config;

import com.studentportal.entity.Role;
import com.studentportal.entity.User;
import com.studentportal.repository.RoleRepository;
import com.studentportal.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class DataSeeder {

    @Bean
    public CommandLineRunner initData(RoleRepository roleRepository, UserRepository userRepository, PasswordEncoder passwordEncoder) {
        return args -> {
            if (roleRepository.count() == 0) {
                Role adminRole = new Role();
                adminRole.setName("ROLE_ADMIN");
                roleRepository.save(adminRole);

                Role studentRole = new Role();
                studentRole.setName("ROLE_STUDENT");
                roleRepository.save(studentRole);

                User admin = new User();
                admin.setEmail("admin@portal.local");
                admin.setPasswordHash(passwordEncoder.encode("Admin!123"));
                admin.setRole(adminRole);
                userRepository.save(admin);

                User student = new User();
                student.setEmail("student1@portal.local");
                student.setPasswordHash(passwordEncoder.encode("Student!123"));
                student.setRole(studentRole);
                userRepository.save(student);
            }
        };
    }
}

