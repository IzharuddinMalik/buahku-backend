package com.steraco.newbuahku.domain.usecase;

import com.steraco.newbuahku.domain.entity.User;
import com.steraco.newbuahku.domain.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public User findByEmail(String email) {
        return repository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    public User save(User user) {
        return repository.save(user);
    }

    public User findByEmailAndPassword(String email, String password) {
        return repository.findByEmailAndPassword(email, password)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    public List<User> findAll() {
        return repository.findAll();
    }

    public User updateUser(String email, User user) {
        findByEmail(email);
        user.setEmail(email);
        return repository.save(user);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
