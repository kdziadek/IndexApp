package com.example.kasia.indexapp;

/**
 * Created by Kasia on 2015-05-27.
 */
public class Wskaznik {

    private int  id;
    private String nazwa;
    private String data;
    private String wartosc;
    public boolean selected;

    public Wskaznik(int id, String nazwa, String data, String wartosc) {
        this.id = id;
        this.nazwa = nazwa;
        this.data = data;
        this.wartosc = wartosc;
    }

    public Wskaznik() {
        this.id = id;
        this.nazwa = nazwa;
        this.data = data;
        this.wartosc = wartosc;
    }

    public Wskaznik(String nazwa, String wartosc, String data) {
        this.nazwa = nazwa;
        this.wartosc = wartosc;
        this.data = data;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNazwa() {
        return nazwa;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getWartosc() {
        return wartosc;
    }

    public void setWartosc(String wartosc) {
        this.wartosc = wartosc;
    }

    public boolean isSelected() {
        return selected;
    }
}
