/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Lab2Jecka;

import java.text.Collator;
import java.util.Date;
import java.util.Locale;
import studijosKTU.Ks;
import studijosKTU.ListKTU;
import studijosKTU.ListKTUx;

/**
 *
 * @author Ernestas
 */
public class KasininkuApskaita {
        public ListKTUx<Kasininkas> visiKasininkai = new ListKTUx<>(new Kasininkas());
    private static final Kasininkas bazinisEgz = new Kasininkas();

    // Suformuojamassąrašas kasininku jaunesniu, nei nurodytas amžius
    public ListKTUx<Kasininkas> atrinktiJaunusKasininkus(int riba) {
        ListKTUx<Kasininkas> jauniKLasininkai = new ListKTUx<>(new Kasininkas());
        for (Kasininkas k : visiKasininkai) {
            if (k.getAmžius() <= riba) {
                jauniKLasininkai.add(k);
            }
        }
        return jauniKLasininkai;
    }
    // suformuojamas vienos lyties kasininku sąrašas
    public ListKTUx<Kasininkas> atrinktiPagalLytį(char lytis) {
        ListKTUx<Kasininkas> vienosLytiesKasininkai = new ListKTUx(bazinisEgz);
        for (Kasininkas k : visiKasininkai) {
            if (k.getLytis() == Character.toUpperCase(lytis)) {
                vienosLytiesKasininkai.add(k);
            }
        }
        return vienosLytiesKasininkai;
    }
    // Suformuojamas seniausių kasininkų sąrašas
    public ListKTUx<Kasininkas> SeniausiKasininkai() {
        ListKTUx<Kasininkas> SeniausiKasininkai = new ListKTUx(bazinisEgz);
        // formuojamas sąrašas su maksimalia reikšme vienos peržiūros metu
        int maxAmžius = 0;
        for (Kasininkas k : visiKasininkai) {
            int amžius = k.getAmžius();
            if (amžius >= maxAmžius) {
                if (amžius > maxAmžius) {
                    SeniausiKasininkai.clear();
                    maxAmžius = amžius;
                }
                SeniausiKasininkai.add(k);
            }
        }
        return SeniausiKasininkai;
    }
    // suformuojamas sąrašas kasininku turinčių tą patį vardą
    public ListKTUx<Kasininkas> atrinktiBendravardžius(String Vardas) {
        Locale.setDefault(new Locale("LT"));
        ListKTUx<Kasininkas> kasininkaiSuTuopačiuVardu = new ListKTUx(bazinisEgz);
        for (Kasininkas k : visiKasininkai) {
            String vardas = k.getVardas().trim();
            if (vardas.equals(Vardas)) {
                kasininkaiSuTuopačiuVardu.add(k);
            }
        }
        return kasininkaiSuTuopačiuVardu;
    }
    //atrenka kasininkus, kurių šiandien gimtadienis
    public ListKTUx<Kasininkas> šiandienŠvenčiaGimtadienius() {
        Date šiandien = new Date();
        ListKTUx<Kasininkas> jubilijatai = new ListKTUx(bazinisEgz);
        for (Kasininkas k : visiKasininkai) {
            šiandien.setYear(k.getGimimoData().getYear());
            if (k.getGimimoData().getMonth() == šiandien.getMonth() 
                    && k.getGimimoData().getDay() == šiandien.getDay()) {
                jubilijatai.add(k);
            }
        }
        return jubilijatai;
    }
}
