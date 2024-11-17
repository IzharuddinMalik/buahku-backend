package com.steraco.newbuahku.domain.repository;

import com.steraco.newbuahku.domain.entity.Buah;

import java.util.List;
import java.util.Optional;

public interface BuahRepository {
    Buah addedBuah(Buah buah);
    Optional<Buah> findById(Long id);
    List<Buah> getAllBuah();
    void deleteById(Long id);
}
