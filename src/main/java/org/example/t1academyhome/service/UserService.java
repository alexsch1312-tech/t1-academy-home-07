package org.example.t1academyhome.service;

import lombok.RequiredArgsConstructor;
import org.example.t1academyhome.dto.UserRequestDto;
import org.example.t1academyhome.dto.UserResponseDto;
import org.example.t1academyhome.entity.User;
import org.example.t1academyhome.exception.ResourceNotFoundException;
import org.example.t1academyhome.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {

    private final UserRepository userRepository;

    // Create
    @Transactional
    public UserResponseDto createUser(UserRequestDto dto) {
        User user = new User(dto.getUsername());
        User savedUser = userRepository.save(user);
        return new UserResponseDto(savedUser);
    }

    // Read (All)
    public List<UserResponseDto> getAllUsers() {
        return userRepository.findAll().stream()
                .map(UserResponseDto::new)
                .collect(Collectors.toList());
    }

    // Read (One)
    public UserResponseDto getUserById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Пользователь с id " + id + " не найден"));
        return new UserResponseDto(user);
    }

    // Update
    @Transactional
    public UserResponseDto updateUser(Long id, UserRequestDto dto) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Пользователь с id " + id + " не найден"));
        user.setUsername(dto.getUsername());
        return new UserResponseDto(user); // Изменения сохранятся автоматически благодаря @Transactional
    }

    // Delete
    @Transactional
    public void deleteUser(Long id) {
        if (!userRepository.existsById(id)) {
            throw new ResourceNotFoundException("Пользователь с id " + id + " не найден");
        }
        userRepository.deleteById(id);
    }
}

/*
package org.example.t1academyhome.service;

import org.example.t1academyhome.entity.User;
import org.example.t1academyhome.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    public User createUser(String username) {
        return userRepository.save(new User(username));
    }

    @Transactional
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    @Transactional
    public void deleteALL() {
        userRepository.deleteAll();
    }

    @Transactional(readOnly = true)
    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    @Transactional(readOnly = true)
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Transactional
    public void updateUser(Long id, String newUsername) {
        userRepository.findById(id).ifPresent(user -> {
            user.setUsername(newUsername);
            userRepository.save(user);
        });
    }

    @Transactional(readOnly = true)
    public Optional<User> getUserByUsername(String username) {
        return userRepository.findByUsernameCustom(username);
    }
}

 */

