package com.shabri.navbar1.model;

public class TempatTerdekat {

        String nama;
        String waktu;
        String rating;
        String harga;
        Integer gambarUrl;


    public TempatTerdekat(String nama, String waktu, String rating, String harga, Integer gambarUrl) {
        this.nama = nama;
        this.waktu = waktu;
        this.rating = rating;
        this.harga = harga;
        this.gambarUrl = gambarUrl;
    }

    public Integer getGambarUrl() {
        return gambarUrl;
    }

    public void setGambarUrl(Integer gambarUrl) {
        this.gambarUrl = gambarUrl;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getWaktu() {
        return waktu;
    }

    public void setWaktu(String waktu) {
        this.waktu = waktu;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getHarga() {
        return harga;
    }

    public void setHarga(String harga) {
        this.harga = harga;
    }
}
