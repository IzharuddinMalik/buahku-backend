package com.steraco.newbuahku.infrastructure.persistence;

import com.steraco.newbuahku.domain.entity.User;
import com.steraco.newbuahku.domain.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class JpaUserRepository implements UserRepository {

    private final SpringJpaUserRepository springJpaUserRepository;
    private final PasswordEncoder passwordEncoder;

    public JpaUserRepository(
            SpringJpaUserRepository springJpaUserRepository,
            PasswordEncoder passwordEncoder) {
        this.springJpaUserRepository = springJpaUserRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @Transactional
    public User save(User user) {
        // Encode password before saving
        UserJpaEntity userJpaEntity = toJpaEntity(user);
        if (user.getId() == null) {
            // Only encode password for new users
            userJpaEntity.setPassword(passwordEncoder.encode(user.getPassword()));
        }
        UserJpaEntity savedEntity = springJpaUserRepository.save(userJpaEntity);
        return toDomainEntity(savedEntity);
    }

    @Override
    public Optional<User> findByEmailAndPassword(String email, String rawPassword) {
        // Never search by raw password - this is insecure
        // Instead, find by email and verify password
        return springJpaUserRepository.findByEmail(email)
                .filter(entity -> passwordEncoder.matches(rawPassword, entity.getPassword()))
                .map(this::toDomainEntity);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return springJpaUserRepository.findByEmail(email)
                .map(this::toDomainEntity);
    }

    @Override
    @Transactional
    public List<User> findAll() {
        return springJpaUserRepository.findAll().stream()
                .map(this::toDomainEntity)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        if (id != null) {
            springJpaUserRepository.deleteById(id);
        }
    }

    private User toDomainEntity(UserJpaEntity entity) {
        if (entity == null) {
            return null;
        }
        return new User(
                entity.getId(),
                entity.getName(),
                entity.getEmail(),
                entity.getPassword()  // Note: Password is already encoded
        );
    }

    private UserJpaEntity toJpaEntity(User user) {
        if (user == null) {
            return null;
        }
        UserJpaEntity userJpaEntity = new UserJpaEntity();
        userJpaEntity.setId(user.getId());
        userJpaEntity.setName(user.getName());
        userJpaEntity.setEmail(user.getEmail());
        userJpaEntity.setPassword(user.getPassword());
        return userJpaEntity;
    }
}
