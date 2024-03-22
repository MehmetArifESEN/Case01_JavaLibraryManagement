package com.javacases;

import java.time.LocalDate;


public abstract class Kitap {

    private String ISBN;
    private String baslik;
    private String yazar;
    private LocalDate yayin_yili;
    private Durum durum;

    public Kitap() {
    }

    public Durum getDurum() {
        return durum;
    }

    public void setDurum(Durum durum) {
        this.durum = durum;
    }

    public Kitap(String ISBN, String baslik, String yazar, LocalDate yayin_yili,Durum durum) {
        this.ISBN = ISBN;
        this.baslik = baslik;
        this.yazar = yazar;
        this.yayin_yili = yayin_yili;
        this.durum=durum;
    }

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public String getBaslik() {
        return baslik;
    }

    public void setBaslik(String baslik) {
        this.baslik = baslik;
    }

    public String getYazar() {
        return yazar;
    }

    public void setYazar(String yazar) {
        this.yazar = yazar;
    }

    public LocalDate getYayin_yili() {
        return yayin_yili;
    }

    public void setYayin_yili(LocalDate yayin_yili) {
        this.yayin_yili = yayin_yili;
    }

    @Override
    public String toString() {
        return "Kitap{" +
                "ISBN=" + ISBN +
                ", baslik='" + baslik + '\'' +
                ", yazar='" + yazar + '\'' +
                ", yayin_yili=" + yayin_yili +
                '}';
    }
}
