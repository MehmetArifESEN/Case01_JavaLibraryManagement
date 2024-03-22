package com.javacases;

import java.time.LocalDate;

public class KitapRoman extends Kitap{

    private String konu;

    public KitapRoman(String konu) {
        this.konu = konu;
    }

    public KitapRoman(String ISBN, String baslik, String yazar, LocalDate yayin_yili, String konu, Durum durum) {
        super(ISBN, baslik, yazar, yayin_yili, durum);
        this.konu = konu;
    }

    public String getKonu() {
        return konu;
    }

    public void setKonu(String konu) {
        this.konu = konu;
    }


}
