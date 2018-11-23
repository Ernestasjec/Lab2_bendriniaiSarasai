/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Lab2Jecka;

import java.util.Locale;
import studijosKTU.Ks;
import studijosKTU.ListKTU;
import studijosKTU.ListKTUx;

/**
 *
 * @author Ernestas
 */
public class TikrintiKasininka {
    ListKTUx<Kasininkas> bandomieji = new ListKTUx<Kasininkas>(new Kasininkas());
    
    void formuotiAutoSąrašą() {
        Kasininkas k1 = new Kasininkas("Jonas","Kumanas","1992-12-01",383092400,'V');
        Kasininkas k2 = new Kasininkas("Petras","Lumanas","1986-05-02",343092400,'V');
        Kasininkas k3 = new Kasininkas("Angele","Bagienė","1990-12-01",333294400,'M');
        Kasininkas k4 = new Kasininkas();
        Kasininkas k5 = new Kasininkas();
        Kasininkas k6 = new Kasininkas();
        k4.parse("Angelė Petraitienė 1986-10-02 33444400 M");
        k5.parse("Jonas Petraitis 1984-09-24 33442500 V");
        k6.parse("Kasparas Domeika 1976-07-25 33444400 V");
        bandomieji.add(k1);
        bandomieji.add(k2);
        bandomieji.add(k3);
        bandomieji.add(k4);
        bandomieji.add(k5);
        bandomieji.add(k6);
        
//        Ks.oun(k1);
//        Ks.oun(k2);
//        Ks.oun(k3);
//        Ks.oun("Pirmų 3 kasininku amžių vidurkis= "+
//                (k1.getAmžius()+k2.getAmžius()+k3.getAmžius())/3);
//        Ks.oun(k4);
//        Ks.oun(k5);
//        Ks.oun(k6);
//        Ks.oun("Kitų 3 kasininku amžiai: "+ " " + 
//                k4.getAmžius()+ " " + k5.getAmžius()+ " " + k6.getAmžius());
    }
    
    void įterptiĮSąrašą(){
        Kasininkas naujas = new Kasininkas("Jonas","Pridėtasis","1992-12-01",383092400,'V');
        Ks.oun("===Pradinis sąrašas===");
        for(Kasininkas bandomasis: bandomieji){
            Ks.oun(bandomasis);
        }
        bandomieji.add(0, naujas);
        
        Ks.oun("===Po įterpimo į pradžią===");
            for(Kasininkas bandomasis: bandomieji){
            Ks.oun(bandomasis);
        }
        bandomieji.add(2, naujas);
        Ks.oun("===Po įterpimo į vidurį===");
            for(Kasininkas bandomasis: bandomieji){
            Ks.oun(bandomasis);
        }
        
        bandomieji.add(bandomieji.size(), naujas);
        Ks.oun("===Po įterpimo į pabaigą===");
            for(Kasininkas bandomasis: bandomieji){
            Ks.oun(bandomasis);
        }
    }
    
        void Šalinimas(){
        Ks.oun("===Pradinis sąrašas===");
        for(Kasininkas bandomasis: bandomieji){
            Ks.oun(bandomasis);
        }
        Ks.oun("Pašalintas elementas " + "Nr. 1 " + bandomieji.remove(0));
        Ks.oun("Pašalintas elementas " + "Nr. 3 " + bandomieji.remove(2));
        Ks.oun("Pašalintas elementas " + "paskutinis " + bandomieji.remove(bandomieji.size()-1));
        Ks.oun("===Po šąlinimo===");
            for(Kasininkas bandomasis: bandomieji){
            Ks.oun(bandomasis);
        }
            
    }
        
    void Pakeitimas(){
        Kasininkas naujas = new Kasininkas("Jonas","Pridėtasis","1992-12-01",383092400,'V');
        Ks.oun("===Pradinis sąrašas===");
        for(Kasininkas bandomasis: bandomieji){
            Ks.oun(bandomasis);
        }
        Ks.oun("Pakeistas elementas " + "Nr. 2 " + bandomieji.set(2, naujas));
        
        Ks.oun("===Po Pakeitimo===");
            for(Kasininkas bandomasis: bandomieji){
            Ks.oun(bandomasis);
        }      
    }
    
        void ElementoIeškojimas(){
        Kasininkas naujas = new Kasininkas("Jonas","Pridėtasis","1992-12-01",383092400,'V');
        Ks.oun("===Pradinis sąrašas===");
        for(Kasininkas bandomasis: bandomieji){
            Ks.oun(bandomasis);
        }
        Ks.oun("Ieškomas elementas - " + bandomieji.get(4));
        Ks.oun("Elementas yra - " + bandomieji.contains(bandomieji.get(4)));
        Ks.oun("Ieškomas elementas - " + naujas);
        Ks.oun("Elemento nėra - " + bandomieji.contains(naujas));
            
    }
    
        
    void PaskutinioŠalinimas(){
        Kasininkas naujas = new Kasininkas("Jonas","Pridėtasis","1992-12-01",383092400,'V');
        Ks.oun("===Pradinis sąrašas===");
        for(Kasininkas bandomasis: bandomieji){
            Ks.oun(bandomasis);
        }
        Ks.oun("pašalintas elementas " + bandomieji.removeLast());
        
        Ks.oun("===Po šalinimo===");
            for(Kasininkas bandomasis: bandomieji){
            Ks.oun(bandomasis);
        }      
    }
    
        void PašalintiIntervalą(){
        Ks.oun("===Pradinis sąrašas===");
        for(Kasininkas bandomasis: bandomieji){
            Ks.oun(bandomasis);
        }
        bandomieji.removeRange(2, 4);
        Ks.oun("Pašalina elementas nuo 3 iki 5");
        
        Ks.oun("===Po šąlinimo===");
            for(Kasininkas bandomasis: bandomieji){
            Ks.oun(bandomasis);
        }
            
    }
        
    void patikrintiKasininkuApskaitą(){
        KasininkuApskaita apskaita = new KasininkuApskaita();
        
        apskaita.visiKasininkai.load("kas1.txt");
        apskaita.visiKasininkai.println("Bandomasis rinkinys");

        bandomieji = apskaita.atrinktiJaunusKasininkus(25);
        bandomieji.println("Pradedant nuo kasininkus gimusius nuo 1992");

        bandomieji = apskaita.atrinktiPagalLytį('M');
        bandomieji.println("Išrenka tik moteris");

        bandomieji = apskaita.SeniausiKasininkai();
        bandomieji.println("Atrenka pačius seniausius");

        bandomieji = apskaita.atrinktiBendravardžius("Petras");
        bandomieji.println("Atrenka Petrus");

        bandomieji = apskaita.šiandienŠvenčiaGimtadienius();

        bandomieji.println("Turi būti kasininkai tik šiandien švenčiantys gimtadienius.");
        int sk=0;
        for (Kasininkas bandomasis: bandomieji){
                sk++;    // testuojame ciklo veikimą
        }
        Ks.oun("švenčiantys gimtadienius = "+sk);
    }
    
    void patikrintiRikiavimą(){
        KasininkuApskaita aps = new KasininkuApskaita();

        aps.visiKasininkai.load("kas1.txt");
        aps.visiKasininkai.println("Bandomasis rinkinys");
        aps.visiKasininkai.sortBuble(Kasininkas.pagalVardusPavardes);
        aps.visiKasininkai.println("Rūšiavimas pagal vardus ir pavardes");
        aps.visiKasininkai.sortBuble(Kasininkas.pagalAmžių);
        aps.visiKasininkai.println("Rūšiavimas pagal amžių");
    }
    
    void peržiūrėtiSąrašą(){
        int sk=0;
        bandomieji.println("Tikrinama sąrašas");
        for (Kasininkas k: bandomieji){
            if (k.getVardas().compareTo("Jonas")==0)
                sk++;
        }
        Ks.oun("Jonų yra = "+sk);
    }
        
    void metodoParinkimas(){
        formuotiAutoSąrašą();
        //įterptiĮSąrašą();
        //Šalinimas();
        //Pakeitimas();
        //ElementoIeškojimas();
        //PaskutinioŠalinimas();
        PašalintiIntervalą();
        //patikrintiKasininkuApskaitą();
        //patikrintiRikiavimą();
        //peržiūrėtiSąrašą();
        
    }
    public static void main(String... args){
        Locale.setDefault(new Locale("LT"));
        new TikrintiKasininka().metodoParinkimas();
    } 
}
