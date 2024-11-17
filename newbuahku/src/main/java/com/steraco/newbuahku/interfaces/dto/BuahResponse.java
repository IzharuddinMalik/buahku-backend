package com.steraco.newbuahku.interfaces.dto;

public class BuahResponse {
    private Long id;
    private String namaBuah;
    private Integer beratBuah;
    private Double hargaBuah;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
