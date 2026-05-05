package com.codominator.userservice;

import com.codominator.userservice.entity.User;
import com.codominator.userservice.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
class UserServiceApplicationTests {
    @Autowired
    private UserRepository userRepository;
    public static final int NUMBER_OF_USERS = 200;

    @Test
    void contextLoads() {
    }

    @Test
    @Disabled
    void createUser() {
        for (int i = 1; i<= NUMBER_OF_USERS; i++) {
            User user = User.builder()
                    .firstname("User" + 1)
                    .lastname("lastname" + i)
                    .email("user" + i + "@gmail.com")
                    .alerting(i % 2 == 0)
                    .energyAlertThreshold(1000.0 + 1)
                    .build();
            userRepository.save(user);
        }
        log.info("User Repository created:");
    }

}
