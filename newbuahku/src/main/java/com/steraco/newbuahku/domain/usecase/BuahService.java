package com.steraco.newbuahku.domain.usecase;

import com.steraco.newbuahku.domain.entity.Buah;
import com.steraco.newbuahku.domain.repository.BuahRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BuahService {
    private final BuahRepository repository;

    public BuahService(BuahRepository repository) {
        this.repository = repository;
    }

    public Buah addedBuah(Buah buah) {
        return repository.addedBuah(buah);
    }

    public Buah findBuahById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Buah not found"));
    }

    public List<Buah> findAll() {
        return repository.getAllBuah();
    }

    public Buah updateBuah(Long id, Buah buah) {
        findBuahById(id);
        buah.setId(id);
        return repository.addedBuah(buah);
    }

    public void deleteBuah(Long id) {
        repository.deleteById(id);
    }
}
