package org.example.t1academyhome05.service;

import org.example.t1academyhome05.entity.User;
import org.example.t1academyhome05.repository.UserRepository;
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
            userRepository.save(user); // На самом деле в транзакции save() вызывать необязательно из-за dirty checking, но оставим для наглядности
        });
    }

    @Transactional(readOnly = true)
    public Optional<User> getUserByUsername(String username) {
        return userRepository.findByUsernameCustom(username);
    }
}

