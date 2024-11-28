package com.steraco.newbuahku.domain.repository;

import com.steraco.newbuahku.domain.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository {
    User save(User user);
    Optional<User> findByEmailAndPassword(String email, String password);
    Optional<User> findByEmail(String email);
    List<User> findAll();
    void deleteById(Long id);
}
