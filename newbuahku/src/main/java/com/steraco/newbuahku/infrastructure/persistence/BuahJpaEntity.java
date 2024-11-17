package com.steraco.newbuahku.infrastructure.persistence;

import com.steraco.newbuahku.domain.entity.Buah;
import jakarta.persistence.*;

@Entity
@Table(name = "buahs")
public class BuahJpaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "namaBuah", nullable = false, length = 200)
    private String namaBuah;

    @Column(name = "beratBuah", nullable = false, length = 10)
    private Integer beratBuah;

    @Column(name = "hargaBuah", nullable = false)
    private Double hargaBuah;


    //Basic Constructor
    public BuahJpaEntity() {}

    // All fields constructor
    public BuahJpaEntity(Long id, String namaBuah, Integer beratBuah, Double hargaBuah) {
        this.id = id;
        this.namaBuah = namaBuah;
        this.beratBuah = beratBuah;
        this.hargaBuah = hargaBuah;
    }

    // Mapping method
    public static BuahJpaEntity fromDomain(Buah buah) {
        return new BuahJpaEntity(
                buah.getId(),
                buah.getNamaBuah(),
                buah.getBeratBuah(),
                buah.getHargaBuah()
        );
    }

    public Buah toDomain() {
        return new Buah(
                this.id,
                this.namaBuah,
                this.beratBuah,
                this.hargaBuah
        );
    }

    // Setter Getter
    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getNamaBuah() {
        return namaBuah;
    }

    public void setNamaBuah(String namaBuah) {
        this.namaBuah = namaBuah;
    }

    public Integer getBeratBuah() {
        return beratBuah;
    }

    public void setBeratBuah(Integer beratBuah) {
        this.beratBuah = beratBuah;
    }

    public Double getHargaBuah() {
        return hargaBuah;
    }

    public void setHargaBuah(Double hargaBuah) {
        this.hargaBuah = hargaBuah;
    }
}
