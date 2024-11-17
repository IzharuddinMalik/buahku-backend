package com.steraco.newbuahku.infrastructure.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SpringJpaBuahRepository extends JpaRepository<BuahJpaEntity, Long> {
}
