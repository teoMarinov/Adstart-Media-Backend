package com.teodor.backend.seeder;

import com.teodor.backend.dto.UserCredentialsDto;
import com.teodor.backend.service.AuthService;
import com.teodor.backend.service.UserService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class AdminUserSeeder {

    @Value("${admin.account.username}")
    private String adminUsername;

    @Value("${admin.account.password}")
    private String adminPassword;
    private final AuthService authService;

    private final UserService userService;

    public AdminUserSeeder(AuthService authService, UserService userService) {
        this.authService = authService;
        this.userService = userService;

        System.out.println(this.adminPassword);
    }

    @EventListener(ApplicationReadyEvent.class)
    public void createDefaultAdminUser() {
        if (userService.findByUsername(adminUsername).isEmpty()) {
            UserCredentialsDto adminDto = new UserCredentialsDto();
            adminDto.setUsername(adminUsername);
            adminDto.setPassword(adminPassword);

            authService.registerUser(adminDto);

            System.out.println("Admin user created.");
        } else {
            System.out.println("Admin user already exists.");
        }
    }
}
