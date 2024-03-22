package com.javacases;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class Main {
    static Kutuphane kutuphane = new Kutuphane();
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        anaMenu();
    }

    static void anaMenu() {
        boolean durum = true;
        System.out.println("----------------------------------------------");
        System.out.println("----------Kutuphaneye Hos Geldiniz-----------");
        System.out.println("---------------------------------------------");
        System.out.println("1-Uye Ol");
        System.out.println("2-Uye Girisi");
        System.out.println("3-Yetkili Girisi");
        String secim = scanner.nextLine();
        int secim1 = Integer.valueOf(secim);
        switch (secim1) {
            case 1:
                kutuphane.uyeEkle(uyeOl());
                System.out.println("Basari ile kayit oldunuz");
                anaMenu();
                break;
            case 2:
                Optional<Uye> uyeOptional = girisYap();
                if (uyeOptional.isPresent()) {
                    Uye uye = uyeOptional.get();
                    uyeIslemleri(uye);
                } else {
                    System.out.println("Kullanıcı adı veya şifre yanlış!");
                    anaMenu();
                }
                break;
            case 3:
                Optional<Uye> admin = girisYap();
                if (admin.isPresent()) {
                    if(admin.get().getRole() == ERole.ADMIN){
                        yetkiliIslemleri();
                    }
                } else {
                    System.out.println("Kullanıcı adı veya şifre yanlış!");
                    anaMenu();
                }
                break;
            default:
                System.out.println("Geçersiz seçim! Lütfen tekrar deneyin.");
                anaMenu();
                break;
        }
    }

    private static void yetkiliIslemleri() {
        System.out.println("------Yetkili islemleri----------");
        System.out.println("1-Kitabin mevcut durumunu degistir");
        System.out.println("2-Uyenin aldigi kitaplari gorunule");
        System.out.println("3-Mevcut kitaplari goruntule");
        System.out.println("4-Kitap Ekle");
        System.out.println("5-Uyeleri listele");
        System.out.println("0-Ana menu");
        String secim = scanner.nextLine();
        switch (Integer.valueOf(secim)){
            case 1:
                kutuphane.kitapDurumDegistir();
                yetkiliIslemleri();
                break;
            case 2:
                kutuphane.uyeKitaplariListele();
                yetkiliIslemleri();
                break;
            case 3:
                kutuphane.mevcutKitaplar();
                yetkiliIslemleri();
                break;
            case 4:
                kutuphane.kitapEkle();
                yetkiliIslemleri();
                break;
            case 5:
                kutuphane.uyeleriListele();
                yetkiliIslemleri();
                break;
            case 0 :
                anaMenu();
            default:
                System.out.println("Geçersiz seçim! Lütfen tekrar deneyin.");
                anaMenu();
                break;
        }

    }

    static void uyeIslemleri(Uye uye){
        System.out.println("------Kullanici islemleri----------");
        System.out.println("1-Aldigin kitaplari goruntule");
        System.out.println("2-Kitap Odunc al");
        System.out.println("3-Kitap teslim et");
        System.out.println("4-Ana menu");
        String secim = scanner.nextLine();
        switch (Integer.valueOf(secim)){
            case 1:
                kutuphane.kitapListele(uye);
                uyeIslemleri(uye);
                break;
            case 2:
                kutuphane.kitapOduncAl(uye);
                uyeIslemleri(uye);
                break;
            case 3:
                kutuphane.kitapTeslimEt(uye);
                uyeIslemleri(uye);
                break;
            case 0 :
                anaMenu();
            default:
                System.out.println("Geçersiz seçim! Lütfen tekrar deneyin.");
                anaMenu();
                break;
        }

    }

    static Optional<Uye> girisYap() {
        System.out.println("-------Kullanici Girisi-----------");
        System.out.println("Kullanici Adinizi Giriniz:");
        String kullaniciAdi = scanner.nextLine();
        System.out.println("Sifrenizi Giriniz:");
        String sifre = scanner.nextLine();
        List<Uye> uyeler = kutuphane.getUyeler();
        Optional<Uye> girisYapanUye = uyeler.stream()
                .filter(uye -> uye.getKullaniciAdi().equals(kullaniciAdi) && uye.getPassword().equals(sifre))
                .findFirst();
        return girisYapanUye;
    }

    static Uye uyeOl() {
        System.out.println("-------Uye Ol-------");
        System.out.println("Adinizi giriniz");
        String ad = scanner.nextLine();
        System.out.println("Kullanici adi yaziniz");
        String kullaniciAdi = scanner.nextLine();
        System.out.println("sifre belirleyin");
        String password = scanner.nextLine();
        System.out.println("Telefon numaranizi Giriniz");
        String telefon = scanner.nextLine();
        System.out.println("Cinsiyetinizi Seciniz");
        System.out.println("Erkek=MALE   Kadin =FEMALE ");
        String cinsiyet = scanner.nextLine();
        return new Uye(ad, telefon, kullaniciAdi, password, Gender.valueOf(cinsiyet));
    }
}