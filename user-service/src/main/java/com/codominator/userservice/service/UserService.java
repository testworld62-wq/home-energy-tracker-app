package com.codominator.userservice.service;

import com.codominator.userservice.dto.UserDto;
import com.codominator.userservice.entity.User;
import com.codominator.userservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public UserDto createUser(UserDto userDto) {



        final User user = User.builder()
                .firstname(userDto.getFirstName())
                .lastname(userDto.getLastName())
                .email(userDto.getEmail())
                .alerting(userDto.isAlerting())
                .energyAlertThreshold(userDto.getEnergyAlertThreshold())
                .address(userDto.getAddress())
                .build();


        userRepository.save(user);
        return toDto(user);
    }
// method 1
//    public UserDto getUserById(Long id) {
//        if (id == null) {
//            log.info("id should not be null");
//            throw new IllegalArgumentException("id is null");
//        }
//        User user = userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("user not found"));
//        return toDto(user);
//
//    }

    // method 2 for finding user
    public UserDto findUserById(Long id) {
        if(id == null) {
            throw new IllegalArgumentException("id is null");
        }

       return userRepository.findById(id).map(this::toDto).orElseThrow();
    }

    public UserDto updateUser(Long id, UserDto userDto) {
        User user = userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("id is null"));
        user.setFirstname(userDto.getFirstName());
        user.setLastname(userDto.getLastName());
        user.setEmail(userDto.getEmail());
        user.setAlerting(userDto.isAlerting());
        user.setEnergyAlertThreshold(userDto.getEnergyAlertThreshold());
        user.setAddress(userDto.getAddress());

        userRepository.save(user);
        return toDto(user);
    }

    public List<UserDto> findAllUsers() {
        return userRepository.findAll().stream().map(this::toDto).toList();
    }

    private UserDto toDto(User user) {
        return UserDto.builder()
                .id(user.getId())
                .firstName(user.getFirstname())
                .lastName(user.getLastname())
                .email(user.getEmail())
                .alerting(user.isAlerting())
                .energyAlertThreshold(user.getEnergyAlertThreshold())
                .address(user.getAddress())
                .build();
    }
}
