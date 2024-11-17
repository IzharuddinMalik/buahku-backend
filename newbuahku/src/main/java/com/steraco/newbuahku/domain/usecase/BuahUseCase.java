package com.steraco.newbuahku.domain.usecase;

import com.steraco.newbuahku.domain.entity.Buah;
import com.steraco.newbuahku.domain.repository.BuahRepository;

import java.util.List;

public class BuahUseCase {
    private final BuahRepository buahRepository;

    public BuahUseCase(BuahRepository buahRepository) {
        this.buahRepository = buahRepository;
    }

    public Buah addedBuah(Buah buah) {
        return buahRepository.addedBuah(buah);
    }

    public Buah getBuah(Long id) {
        return buahRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Buah not found"));
    }

    public List<Buah> getAllBuah() {
        return buahRepository.getAllBuah();
    }

    public Buah updateBuah(Long id, Buah buah) {
        Buah buahExisting = getBuah(id);
        // Update fields
        buahExisting.setNamaBuah(buah.getNamaBuah());
        buahExisting.setBeratBuah(buah.getBeratBuah());
        buahExisting.setHargaBuah(buah.getHargaBuah());
        return buahRepository.addedBuah(buahExisting);
    }

    public void deleteBuah(Long id) {
        buahRepository.deleteById(id);
    }
}
