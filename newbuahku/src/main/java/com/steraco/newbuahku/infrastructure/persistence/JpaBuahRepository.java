package com.steraco.newbuahku.infrastructure.persistence;

import com.steraco.newbuahku.domain.entity.Buah;
import com.steraco.newbuahku.domain.repository.BuahRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class JpaBuahRepository implements BuahRepository {
    private final SpringJpaBuahRepository springJpaBuahRepository;

    public JpaBuahRepository(SpringJpaBuahRepository springJpaBuahRepository) {
        this.springJpaBuahRepository = springJpaBuahRepository;
    }

    @Override
    public Buah addedBuah(Buah buah) {
        BuahJpaEntity buahJpaEntity = toJpaEntity(buah);
        BuahJpaEntity saveEntity = springJpaBuahRepository.save(buahJpaEntity);
        return toDomainEntity(saveEntity);
    }

    @Override
    public Optional<Buah> findById(Long id) {
        return springJpaBuahRepository.findById(id)
                .map(this::toDomainEntity);
    }

    @Override
    public List<Buah> getAllBuah() {
        return springJpaBuahRepository.findAll().stream()
                .map(this::toDomainEntity)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteById(Long id) {
        springJpaBuahRepository.deleteById(id);
    }

    private Buah toDomainEntity(BuahJpaEntity jpaEntity) {
        return new Buah(
                jpaEntity.getId(),
                jpaEntity.getNamaBuah(),
                jpaEntity.getBeratBuah(),
                jpaEntity.getHargaBuah()
        );
    }

    private BuahJpaEntity toJpaEntity(Buah buah) {
        BuahJpaEntity entity = new BuahJpaEntity();
        entity.setId(buah.getId());
        entity.setNamaBuah(buah.getNamaBuah());
        entity.setBeratBuah(buah.getBeratBuah());
        entity.setHargaBuah(buah.getHargaBuah());
        return entity;
    }
}
