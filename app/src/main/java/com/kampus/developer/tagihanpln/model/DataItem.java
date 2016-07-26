package com.kampus.developer.tagihanpln.model;

/**
 * Created by Dodi Rivaldi on 15/07/2016.
 */
public class DataItem {
    private String idpel;
    private int tagihan;
    private String nama;
    private String terbilang;
    private String namathblrek;
    private int diskon;
    private int beban;

    public int getDiskon() {
        return diskon;
    }

    public void setDiskon(int diskon) {
        this.diskon = diskon;
    }

    public int getBeban() {
        return beban;
    }

    public void setBeban(int beban) {
        this.beban = beban;
    }

    public String getNamathblrek() {
        return namathblrek;
    }

    public void setNamathblrek(String namathblrek) {
        this.namathblrek = namathblrek;
    }

    public String getIdpel() {
        return idpel;
    }

    public void setIdpel(String idpel) {
        this.idpel = idpel;
    }

    public int getTagihan() {
        return tagihan;
    }

    public void setTagihan(int tagihan) {
        this.tagihan = tagihan;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getTerbilang() {
        return terbilang;
    }

    public void setTerbilang(String terbilang) {
        this.terbilang = terbilang;
    }
}
