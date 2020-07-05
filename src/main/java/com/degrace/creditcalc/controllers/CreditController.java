package com.degrace.creditcalc.controllers;

import com.degrace.creditcalc.models.CreditRequest;
import com.degrace.creditcalc.models.Platezh;
import com.degrace.creditcalc.models.CreditResult;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@RestController
@RequestMapping(value="/api")
public class CreditController {

    @RequestMapping(value = "/calc_credit",
            produces = "application/json",
            method=RequestMethod.POST)
    public CreditResult calcCredit(@RequestBody CreditRequest credit) {
        CreditResult result = new CreditResult();

        //  Сумма кредита
        double summa = credit.getSumma();
        //  Срок кредита в месяцах
        int srok = credit.getSrok();
        //  Процентная ставка годовая
        double procent = credit.getProcent();

        //  Ежемесячная процентная ставка
        double i_procent = procent/100/12;
        //  Сумма ежемесячного платежа
        double platezh_sum = (summa * (i_procent+(i_procent/(Math.pow((1+i_procent), srok)-1))));
        //  Переплата по кредиту
        double pereplata = platezh_sum*srok-summa;
        //  Итоговая сумма выплат
        double summa_itogo = summa+pereplata;

        //  Вывод результатов с округлением до двух знаков
        result.setPlatezh_sum(round(platezh_sum,2));
        result.setPereplata(round(pereplata,2));
        result.setSumma_itogo(round(summa_itogo,2));

        //  Список платежей
        List<Platezh> platezh_list = new ArrayList<>();

        //  Календарь и формат вывода даты платежа
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM.yyyy");

        //  Итого платежей
        double itogo_pletezhey = 0;
        //  Итого выплат по процентам
        double itogo_procent = 0;
        //  Итого выплат по основному долгу
        double itogo_dolg = 0;
        //  Сумма основного долга
        double summa_dolga = summa;
        for (int nomer = 1; nomer <= srok; nomer++){
            //  Прибавление месяца для календаря
            calendar.add(Calendar.MONTH, 1);
            //  Процентная часть текущего платежа
            double pl_procent = summa_dolga * i_procent;
            //  Часть основного долга текущего платежа
            double pl_dolg = platezh_sum - pl_procent;
            //  Остаток после оплаты текущего платежа
            double pl_ostatok = summa_dolga - pl_dolg;
            //  Расчет итоговых показателей после текущего платежа
            itogo_pletezhey = itogo_pletezhey + platezh_sum;
            itogo_procent = itogo_procent + pl_procent;
            itogo_dolg = itogo_dolg + pl_dolg;
            summa_dolga = pl_ostatok;
            //  Добавление текущего платежа в список платежей с округлением параметров до одного знака
            platezh_list.add(new Platezh(dateFormat.format(calendar.getTime()),nomer,round(platezh_sum,1),round(pl_procent,1),round(pl_dolg,1),round(pl_ostatok,1)));
        }
        //  Добавление итоговых показателей в список платежей
        platezh_list.add(new Platezh("ИТОГО",srok,round(itogo_pletezhey,1),round(itogo_procent,1),round(itogo_dolg,1),0));
        result.setPlatezh_list(platezh_list);

        return result;
    }

    //  Округление до необходимого количества знаков
    private static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        long factor = (long) Math.pow(10, places);
        value = value * factor;
        long tmp = Math.round(value);
        return (double) tmp / factor;
    }

}