package com.degrace.creditcalc.models;

public class CreditRequest {

    private final float summa;
    private final int srok;
    private final float procent;
    private final float platezh;
    private final float pereplata;
    private final float summa_itogo;



    public CreditRequest(float summa, int srok, float procent, float platezh, float pereplata, float summa_itogo) {
        this.summa = summa;
        this.srok = srok;
        this.procent = procent;
        this.platezh = platezh;
        this.pereplata = pereplata;
        this.summa_itogo = summa_itogo;

    }

    public float getSumma() {
        return summa;
    }

    public int getSrok() {
        return srok;
    }

    public float getProcent() {
        return procent;
    }
    public float getPlatezh() {
        return platezh;
    }

    public float getPereplata() {
        return pereplata;
    }

    public float getSumma_itogo() {
        return summa_itogo;
    }

}
