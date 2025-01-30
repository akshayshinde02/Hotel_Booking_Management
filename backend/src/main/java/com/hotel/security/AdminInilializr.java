package com.hotel.security;
import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import com.hotel.entity.User;


@Component
public class AdminInilializr implements CommandLineRunner {

    private com.hotel.repo.UserRepository userRepository;

    @Value("${admin.email}")
    private String adminEmail;

    @Value("${admin.password}")
    private String adminPassword;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public AdminInilializr(com.hotel.repo.UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        Optional<User> user = userRepository.findByEmail(adminEmail);
       
        if(user.isEmpty()){

            User admin = new User();
            admin.setEmail(adminEmail);
            admin.setName("admin");
            admin.setPhoneNumber("8686858585");
            admin.setPassword(passwordEncoder.encode(adminPassword));
            admin.setRole("ADMIN");

            userRepository.save(admin);
            System.out.println("Admin user created successfully!");
        }
        //  else{
        //     throw new Exception("Something went wrong while creating admin");
        // }
    }

}
