package com.javacases;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Uye implements IUye{

    private String ad;
    private String telefon;
    private String kullaniciAdi;
    private String password;
    private Gender gender;
    private ERole role = ERole.UYE;
    private List<Kitap> uyeKitaplar;

    public Uye(String ad, String telefon, String kullaniciAdi, String password, Gender gender, List<Kitap> uyeKitaplar,ERole role) {
        this.ad = ad;
        this.telefon = telefon;
        this.kullaniciAdi = kullaniciAdi;
        this.password = password;
        this.gender = gender;
        this.uyeKitaplar = uyeKitaplar;
        this.role =role;
    }



    public ERole getRole() {
        return role;
    }

    public void setRole(ERole role) {
        this.role = role;
    }

    public List<Kitap> getUyeKitaplar() {
        if (uyeKitaplar == null) {
            uyeKitaplar = new ArrayList<>();
        }
        return uyeKitaplar;
    }

    public void setUyeKitaplar(List<Kitap> uyeKitaplar) {
        this.uyeKitaplar = uyeKitaplar;
    }

    public Uye(String ad, String telefon, String kullaniciAdi, String password, Gender gender,ERole role) {
        this.ad = ad;
        this.telefon = telefon;
        this.kullaniciAdi = kullaniciAdi;
        this.password = password;
        this.gender = gender;
        this.role = role;
    }
    public Uye(String ad, String telefon, String kullaniciAdi, String password, Gender gender) {
        this.ad = ad;
        this.telefon = telefon;
        this.kullaniciAdi = kullaniciAdi;
        this.password = password;
        this.gender = gender;
    }

    public String getAd() {
        return ad;
    }

    public String getKullaniciAdi() {
        return kullaniciAdi;
    }

    public void setKullaniciAdi(String kullaniciAdi) {
        this.kullaniciAdi = kullaniciAdi;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setAd(String ad) {
        this.ad = ad;
    }

    public String getTelefon() {
        return telefon;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public List<Kitap> getKitaplar() {
        return uyeKitaplar;
    }

    public void setKitaplar(List<Kitap> kitaplar) {
        this.uyeKitaplar = kitaplar;
    }

    @Override
    public String toString() {
        return "Uye{" +
                "ad='" + ad + '\'' +
                ", kullaniciAdi='" + kullaniciAdi + '\'' +
                ", password='" + password + '\'' +
                ", telefon='" + telefon + '\'' +
                ", gender=" + gender +
                ", uyeKitaplar=" + uyeKitaplar +
                '}';
    }

    @Override
    public void kitapAl(Kitap kitap) {
        getUyeKitaplar().add(kitap);
    }

    @Override
    public void kitapIadeEt(Kitap kitap) {
        getUyeKitaplar().remove(kitap);
    }
}
