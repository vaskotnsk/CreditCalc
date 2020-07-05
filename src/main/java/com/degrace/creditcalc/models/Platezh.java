package com.degrace.creditcalc.models;

//  Ежемесячный платеж
public class Platezh {

    private final String data;
    private final int nomer;
    private final double summa;
    private final double procent;
    private final double dolg;
    private final double ostatok;

    public Platezh(String data, int nomer, double summa, double procent, double dolg, double ostatok) {
        this.data = data;
        this.nomer = nomer;
        this.summa = summa;
        this.procent = procent;
        this.dolg = dolg;
        this.ostatok = ostatok;
    }

    public String getData() {
        return data;
    }

    public int getNomer() {
        return nomer;
    }

    public double getSumma() {
        return summa;
    }

    public double getProcent() {
        return procent;
    }

    public double getDolg() {
        return dolg;
    }

    public double getOstatok() {
        return ostatok;
    }

}
