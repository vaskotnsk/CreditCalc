package com.degrace.creditcalc.models;

import java.util.List;

public class CreditResult {
    private double platezh_sum;
    private double pereplata;
    private double summa_itogo;
    private List<Platezh> platezh_list;

    public List<Platezh> getPlatezh_list() {
        return platezh_list;
    }

    public void setPlatezh_list(List<Platezh> platezh_list) {
        this.platezh_list = platezh_list;
    }

    public void addToPlatezhList(Platezh platezh) {
        this.platezh_list.add(platezh);
    }

    public double getPlatezh_sum() {
        return platezh_sum;
    }

    public void setPlatezh_sum(double platezh_sum) {
        this.platezh_sum = platezh_sum;
    }

    public double getPereplata() {
        return pereplata;
    }

    public void setPereplata(double pereplata) {
        this.pereplata = pereplata;
    }

    public double getSumma_itogo() {
        return summa_itogo;
    }

    public void setSumma_itogo(double summa_itogo) {
        this.summa_itogo = summa_itogo;
    }
}
