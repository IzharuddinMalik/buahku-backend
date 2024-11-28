package com.steraco.newbuahku.domain.usecase;

import com.steraco.newbuahku.domain.entity.User;
import com.steraco.newbuahku.domain.repository.UserRepository;

import java.util.List;

public class UserUseCase {

    private final UserRepository userRepository;

    public UserUseCase(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User save(User user) {
        return userRepository.save(user);
    }

    public User findByEmailAndPassword(String email, String password) {
        return userRepository.findByEmailAndPassword(email, password)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    public User findByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User update(Long id,User user) {
        User userExisting = findByEmail(user.getEmail());
        // Update fiedls
        userExisting.setName(user.getName());
        userExisting.setEmail(user.getEmail());
        return userRepository.save(userExisting);
    }

    public void delete(Long id) {
        userRepository.deleteById(id);
    }
}
