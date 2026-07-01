package org.example.t1academyhome.service;

import lombok.RequiredArgsConstructor;
import org.example.t1academyhome.dto.UserRequestDto;
import org.example.t1academyhome.dto.UserResponseDto;
import org.example.t1academyhome.entity.User;
import org.example.t1academyhome.exception.ResourceNotFoundException;
import org.example.t1academyhome.repository.ProductRepository;
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
    private final ProductRepository productRepository;

    @Transactional
    public UserResponseDto createUser(UserRequestDto dto) {
        User user = new User(dto.getUsername());
        User savedUser = userRepository.save(user);
        return new UserResponseDto(savedUser);
    }

    public List<UserResponseDto> getAllUsers() {
        return userRepository.findAll().stream()
                .map(UserResponseDto::new)
                .collect(Collectors.toList());
    }

    public UserResponseDto getUserById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Пользователь с id " + id + " не найден"));
        return new UserResponseDto(user);
    }
    public UserResponseDto getUserByUsername(String username) {
        User user = userRepository.findByUsernameCustom(username)
                .orElseThrow(() -> new ResourceNotFoundException("Пользователь с  username " + username + " не найден"));
        return new UserResponseDto(user);
    }

    @Transactional
    public UserResponseDto updateUser(Long id, UserRequestDto dto) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Пользователь с id " + id + " не найден"));
        user.setUsername(dto.getUsername());
        return new UserResponseDto(user);
    }

    @Transactional
    public void deleteUser(Long id) {
        if (!userRepository.existsById(id)) {
            throw new ResourceNotFoundException("Пользователь с id " + id + " не найден");
        }

        productRepository.deleteByUserId(id);
        userRepository.deleteById(id);
    }

}