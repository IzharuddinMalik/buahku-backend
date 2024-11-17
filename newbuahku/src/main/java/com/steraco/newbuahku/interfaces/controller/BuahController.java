package com.steraco.newbuahku.interfaces.controller;

import com.steraco.newbuahku.domain.entity.Buah;
import com.steraco.newbuahku.domain.usecase.BuahService;
import com.steraco.newbuahku.interfaces.dto.BuahRequest;
import com.steraco.newbuahku.interfaces.dto.BuahResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/buah")
public class BuahController {
    private final BuahService buahService;

    public BuahController(BuahService buahService) {
        this.buahService = buahService;
    }

    @PostMapping
    public ResponseEntity<BuahResponse> addedBuah(@RequestBody BuahRequest buahRequest) {
        Buah buah = new Buah(null, buahRequest.getNamaBuah(), buahRequest.getBeratBuah(), buahRequest.getHargaBuah());
        Buah created = buahService.addedBuah(buah);
        return ResponseEntity.ok(toBuahResponse(created));
    }

    @GetMapping("/{id}")
    public ResponseEntity<BuahResponse> findBuahById(@PathVariable Long id) {
        Buah buah = buahService.findBuahById(id);
        return ResponseEntity.ok(toBuahResponse(buah));
    }

    @GetMapping
    public ResponseEntity<List<BuahResponse>> findAllBuah() {
        List<BuahResponse> responses = buahService.findAll().stream()
                .map(this::toBuahResponse)
                .collect(Collectors.toList());

        return ResponseEntity.ok(responses);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BuahResponse> updateBuah(@PathVariable Long id, @RequestBody BuahRequest buahRequest) {
        Buah buah = new Buah(id, buahRequest.getNamaBuah(), buahRequest.getBeratBuah(), buahRequest.getHargaBuah());
        Buah updated = buahService.updateBuah(id, buah);
        return ResponseEntity.ok(toBuahResponse(updated));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<BuahResponse> deleteBuah(@PathVariable Long id) {
        buahService.deleteBuah(id);
        return ResponseEntity.noContent().build();
    }

    private BuahResponse toBuahResponse(Buah buah) {
        BuahResponse buahResponse = new BuahResponse();
        buahResponse.setId(buah.getId());
        buahResponse.setNamaBuah(buah.getNamaBuah());
        buahResponse.setBeratBuah(buah.getBeratBuah());
        buahResponse.setHargaBuah(buah.getHargaBuah());
        return buahResponse;
    }
}
