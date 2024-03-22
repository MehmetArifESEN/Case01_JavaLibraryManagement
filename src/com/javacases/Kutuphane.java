package com.javacases;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class Kutuphane {
    private List<Kitap> kitaplar;
    private List<Uye> uyeler;

    public Kutuphane() {
        kutuphaneOlustur();
    }

    public Kutuphane(List<Kitap> kitaplar, List<Uye> uyeler) {
        this.kitaplar = kitaplar;
        this.uyeler = uyeler;
    }


    public List<Kitap> getKitaplar() {
        return kitaplar;
    }

    public void setKitaplar(List<Kitap> kitaplar) {
        this.kitaplar = kitaplar;
    }

    public List<Uye> getUyeler() {
        return uyeler;
    }

    public void setUyeler(List<Uye> uyeler) {
        this.uyeler = uyeler;
    }

    public void kutuphaneOlustur() {
        List <Kitap> kitapList=new ArrayList<>();

        kitapList.add(new KitapBilim("1000","KOZMOZ","Carl Sagan", LocalDate.of(1980,01,01),"Bilim kitabı",Durum.ODUNC_ALINABILIR ));
        kitapList.add(new KitapBilim("1001","Zamanın Kısa Tarihi","Stephen Hawking",LocalDate.of(1988,01,01),"Bilim kitabı",Durum.ODUNC_ALINABILIR ));
        kitapList.add(new KitapBilim("1002","Türlerin Kökeni","Charles Darwin",LocalDate.of(1859,01,01),"Bilim kitabı",Durum.ODUNC_ALINABILIR ));
        kitapList.add(new KitapBilim("1003","Gen Bencildir","Richard Dawkins",LocalDate.of(1976,01,01),"Bilim kitabı",Durum.MEVCUT_DEGIL ));
        kitapList.add(new KitapRoman("1004","Dune","Frank Herbert",LocalDate.of(1965,01,01),"Roman kitabı",Durum.ODUNC_ALINABILIR));
        kitapList.add(new KitapRoman("1005","Şeker Portakalı","Jose Vasconcelos",LocalDate.of(1968,01,01),"Roman kitabı",Durum.ODUNC_ALINABILIR));
        kitapList.add(new KitapRoman("1006","LOTR: Yüzük Kardeşliği","JRR Tolkien",LocalDate.of(1954,07,29),"Roman kitabı",Durum.ODUNC_ALINABILIR));
        kitapList.add(new KitapTarih("1007","Savaş Sanatı","Sun Tzu",LocalDate.of(2010,01,01),"Tarih Kitabı",Durum.ODUNC_ALINABILIR));
        kitapList.add(new KitapTarih("1008","Nutuk","Mustafa Kemal Atatürk",LocalDate.of(1927,01,01),"Tarih Kitabı",Durum.ODUNC_ALINABILIR));
        kitapList.add(new KitapTarih("1009","Şu Çılgın Türkler","Turgut Özakman",LocalDate.of(2005,01,01),"Tarih Kitabı",Durum.ODUNC_ALINABILIR));

        setKitaplar(kitapList);

        List<Uye> uyeler= new ArrayList<>();
        uyeler.add(new Uye("Osman","0533669988","OsmanTarkan","12345",Gender.MALE));
        uyeler.add(new Uye("Admin","0533611188","AdminUser","111333",Gender.MALE,ERole.ADMIN));
        setUyeler(uyeler);
    }

    public void uyeEkle(Uye uye){
        getUyeler().add(uye);
    }


    public void kitapListele(Uye uye) {
        if (uye.getUyeKitaplar().isEmpty()){
            System.out.println("Odunc aldiginiz kitap bulunmamaktadir");
        }else {
            uye.getUyeKitaplar().forEach(System.out::println);
        }
    }

    public void kitapOduncAl(Uye uye) {
        System.out.println("Mevcut kitaplar:");
        getKitaplar().stream()
                .filter(kitap -> kitap.getDurum() == Durum.ODUNC_ALINABILIR)
                .forEach(System.out::println);
        System.out.println("Ödünç almak istediğiniz kitabın ISBN numarasını giriniz:");
        Scanner scanner = new Scanner(System.in);
        String isbn = scanner.nextLine();

        Optional<Kitap> oduncAlinanKitap = getKitaplar().stream()
                .filter(kitap -> kitap.getISBN().equals(isbn))
                .findFirst();
        if (oduncAlinanKitap.isPresent()) {
            Kitap kitap = oduncAlinanKitap.get();
            uye.kitapAl(kitap);
            kitap.setDurum(Durum.ODUNC_VERILDI);
            System.out.println("Kitap başarıyla ödünç alındı.");
        } else {
            System.out.println("Belirtilen ISBN numarasına sahip bir kitap bulunamadı.");
        }
    }

    public void kitapDurumDegistir() {
        System.out.println("Durumunu degistirmek istediğiniz kitabın ISBN numarasını giriniz:");
        Scanner scanner = new Scanner(System.in);
        String isbn = scanner.nextLine();
        Optional<Kitap> durumKitap = getKitaplar().stream()
                .filter(kitap -> kitap.getISBN().equals(isbn))
                .findFirst();
        if (durumKitap.isPresent()) {
            Kitap kitap = durumKitap.get();
            System.out.println("Guncel durumunu seciniz");
            System.out.println("1-ODUNC_VERILDI   2-ODUNC_ALINABILIR  3-MEVCUT_DEGIL");
            String secim = scanner.nextLine();
            switch (Integer.valueOf(secim)){
                case 1:
                    kitap.setDurum(Durum.ODUNC_VERILDI);
                    break;
                case 2:
                    kitap.setDurum(Durum.ODUNC_ALINABILIR);
                    break;
                case 3:
                    kitap.setDurum(Durum.MEVCUT_DEGIL);
                    break;
                default:
                    System.out.println("Lutfen gecerli bir sayi giriniz");
                    kitapDurumDegistir();
            }

            System.out.println("Kitabin durumu başarıyla  degistirildi.");
        } else {
            System.out.println("Belirtilen ISBN numarasına sahip bir kitap bulunamadı.");
        }
    }

    public void kitapTeslimEt(Uye uye) {
        System.out.println("Teslim edilebilir kitaplar:");
        uye.getUyeKitaplar().forEach(System.out::println);
        System.out.println("Teslim etmek istediğiniz kitabın ISBN numarasını giriniz:");
        Scanner scanner = new Scanner(System.in);
        String isbn = scanner.nextLine();

        Optional<Kitap> teslimEdilecekKitap = getKitaplar().stream()
                .filter(kitap -> kitap.getISBN().equals(isbn))
                .findFirst();
        if (teslimEdilecekKitap.isPresent()) {
            Kitap kitap = teslimEdilecekKitap.get();
            uye.kitapIadeEt(kitap);
            kitap.setDurum(Durum.ODUNC_ALINABILIR);
            System.out.println("Kitap başarıyla teslim edildi.");
        } else {
            System.out.println("Belirtilen ISBN numarasına sahip bir kitap bulunamadı.");
        }
    }

    public void uyeKitaplariListele() {
        System.out.println("Listelemek istediginiz uye kullanici adini giriniz");
        Scanner scanner = new Scanner(System.in);
        String kullaniciAd = scanner.nextLine();

        Optional<Uye> uyeler = getUyeler().stream()
                .filter(uye -> uye.getKullaniciAdi().equals(kullaniciAd))
                .findFirst();
        if (uyeler.isPresent()) {
            if(uyeler.get().getUyeKitaplar().isEmpty()){
                System.out.println("Uyenin kitabi bulunmamaktadir");
            }else{
                uyeler.get().getUyeKitaplar().forEach(System.out::println);
            }
        } else {
            System.out.println("Belirtilen kullanici adina sahip bir kullanici bulunamadı.");
        }
    }

    public void mevcutKitaplar() {
        System.out.println("-----Mevcut Kitaplar-----");
        getKitaplar().forEach(kitap -> System.out.println(kitap + "       Durumu -> " + kitap.getDurum().toString()));
    }

    public void kitapEkle() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Eklemek istediginiz kitabin ISBN numarasini giriniz");
        String isbn = scanner.nextLine();
        System.out.println("Kitabin adini giriniz");
        String ad = scanner.nextLine();
        System.out.println("Yazari giriniz");
        String yazarAdi= scanner.nextLine();
        System.out.println("Yayin Yilini giriniz (yyyy-aa-gg");
        String yayinYili = scanner.nextLine();
        System.out.println("Kitabin aciklamasini giriniz");
        String aciklama = scanner.nextLine();
        System.out.println("Kitabin turunu giriniz (ROMAN - TARIH - BILIM )");
        String tur = scanner.nextLine();
        LocalDate yayinYiliDate = LocalDate.parse(yayinYili);
        if (tur.equals("ROMAN")){
            Kitap kitap = new KitapRoman(isbn,ad,yazarAdi,yayinYiliDate,aciklama,Durum.ODUNC_ALINABILIR);
            getKitaplar().add(kitap);
            System.out.println("Basarili sekilde eklendi");
        } else if (tur.equals("TARIH")) {
            Kitap kitap = new KitapTarih(isbn,ad,yazarAdi,yayinYiliDate,aciklama,Durum.ODUNC_ALINABILIR);
            getKitaplar().add(kitap);
            System.out.println("Basarili sekilde eklendi");
        }else if (tur.equals("BILIM")){
            Kitap kitap = new KitapBilim(isbn,ad,yazarAdi,yayinYiliDate,aciklama,Durum.ODUNC_ALINABILIR);
            getKitaplar().add(kitap);
            System.out.println("Basarili sekilde eklendi");
        }else{
            System.out.println("Yanlis Tur girdiniz");
            kitapEkle();
        }
    }

    public void uyeleriListele() {
        System.out.println("-----Mevcut Uyeler-----");
        getUyeler().forEach(System.out::println);
    }
}
