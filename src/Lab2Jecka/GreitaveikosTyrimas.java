/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Lab2Jecka;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Locale;
import java.util.Random;
import studijosKTU.*;
/*
 */
public class GreitaveikosTyrimas {
    Kasininkas[] parduotuve;
    ListKTU<Kasininkas> aSeries = new ListKTU<>();
    Random ag = new Random();  // atsitiktinių generatorius
//    int[] tiriamiKiekiai = {2_000, 4_000, 8_000, 16_000};
//    pabandykite, gal Jūsų kompiuteris įveiks šiuos eksperimentus
//    paieškokite ekstremalaus apkrovimo be burbuliuko metodo
    static int[] tiriamiKiekiai = {10_000, 20_000, 40_000, 80_000};
    void generuotiKasininkus(int kiekis){
       String[][] km = { // galimų automobilių markių ir jų modelių masyvas
          {"Jonas", "Antanaitis", "Petraitis", "Kumanaitis", "Lenkaitis", "V"},
          {"Antanas", "Rigauskas", "Kirobavicius", "Inkilaitis", "Morkavicus", "V"},
          {"Kazys", "Petraitonis", "Ignavicius", "Mondeonaitis", "Grigaitis", "V"},
          {"Ignas", "Jablonskis", "Koranovas", "Ignanovasi", "Bonavičius", "V"},
          {"Inga", "Kamoriene", "Lumaniene", "Lenkiene", "Ignaciene", "M"},
          {"Janina", "Komanoviene", "Lambardiene", "Lomanoviene", "Lodarova", "M"}
       };
        parduotuve= new Kasininkas[kiekis];
        String[] datos = {"1958-05-04", "1968-10-12", "1980-05-16", "1995-05-28", "1997-09-01", 
            "1955-01-04", "1977-02-15", "1977-11-15"};
        ag.setSeed(2017);
        for(int i=0;i<kiekis;i++){
            int ka = ag.nextInt(km.length);        // Vardo indeksas  0..
            int pav = ag.nextInt(km[ka].length-2)+1;// pavardes indeksas 1..
            parduotuve[i]= new Kasininkas(km[ka][pav], km[ka][0],
                datos[ag.nextInt(datos.length)],           // gimimo datos
                 30000000 + ag.nextInt(9999999),      // asmens kodai
                km[ka][km[ka].length - 1].charAt(0)); // lytis
        }
        Collections.shuffle(Arrays.asList(parduotuve));
        aSeries.clear();
        for(Kasininkas k: parduotuve) aSeries.add(k);
    }
    void paprastasTyrimas(int elementųKiekis){
        // Paruošiamoji tyrimo dalis
        long t0=System.nanoTime();
        generuotiKasininkus(elementųKiekis);
        ListKTU<Kasininkas> aSeries2 = aSeries.clone();
        ListKTU<Kasininkas> aSeries3 = aSeries.clone();
        ListKTU<Kasininkas> aSeries4 = aSeries.clone();
        long t1=System.nanoTime();
        System.gc(); System.gc(); System.gc();
        long t2=System.nanoTime();
//  Greitaveikos bandymai ir laiko matavimai
        aSeries.sortSystem();
        long t3=System.nanoTime();
        aSeries2.sortSystem(Kasininkas.pagalAmžių);
        long t4=System.nanoTime();
        aSeries3.sortBuble();
        long t5=System.nanoTime();
        aSeries4.sortBuble(Kasininkas.pagalVardusPavardes);
        long t6=System.nanoTime();
        Ks.ouf("%7d %7.4f %7.4f %7.4f %7.4f %7.4f %7.4f \n", elementųKiekis,
                (t1-t0)/1e9, (t2-t1)/1e9, (t3-t2)/1e9,
                (t4-t3)/1e9, (t5-t4)/1e9, (t6-t5)/1e9 );
    }

    
// sekančio tyrimo metu gaunama normalizuoti įvertinimai su klase TimeKeeper
    void sisteminisTyrimas(){
    // Paruošiamoji tyrimo dalis
        Timekeeper tk = new Timekeeper(tiriamiKiekiai);
        for (int kiekis : tiriamiKiekiai) {
           generuotiKasininkus(kiekis);
           ListKTU<Kasininkas> aSeries2 = aSeries.clone();
           ListKTU<Kasininkas> aSeries3 = aSeries.clone();
           ListKTU<Kasininkas> aSeries4 = aSeries.clone();

    //  Greitaveikos bandymai ir laiko matavimai
            tk.start();
            aSeries.sortSystem();
            tk.finish("SysBeCom");
            aSeries2.sortSystem(Kasininkas.pagalAmžių);
            tk.finish("SysSuCom");
            aSeries3.sortBuble();
            tk.finish("BurBeCom");
            aSeries4.sortBuble(Kasininkas.pagalVardusPavardes);
            tk.finish("BurSuCom");
            tk.seriesFinish();
        }
    }
    
    void RikiavimoTyrimai(){
        long memTotal = Runtime.getRuntime().totalMemory();
        Ks.oun("memTotal= "+memTotal);
        // Pasižiūrime kaip generuoja automobilius (20) vienetų)
        generuotiKasininkus(20);
        for(Kasininkas a: aSeries) Ks.oun(a);
        Ks.oun("1 - Pasiruošimas tyrimui - duomenų generavimas");
        Ks.oun("2 - Pasiruošimas tyrimui - šiukšlių surinkimas");
        Ks.oun("3 - Rūšiavimas sisteminiu greitu būdu be Comparator");
        Ks.oun("4 - Rūšiavimas sisteminiu greitu būdu su Comparator");
        Ks.oun("5 - Rūšiavimas List burbuliuku be Comparator");
        Ks.oun("6 - Rūšiavimas List burbuliuku su Comparator");
        Ks.ouf("%6d %7d %7d %7d %7d %7d %7d \n", 0,1,2,3,4,5,6);
        for(int n: tiriamiKiekiai)
           paprastasTyrimas(n);
        // sekančio tyrimo metu gaunama normalizuoti įvertinimai
        sisteminisTyrimas();
    }
    //ArrayList<Integer><-->LinkedList<Integer> metodas containsAll(Collection<?> c)
        void paprastasTyrimas15Tyr(int elementųKiekis){
// Paruošiamoji tyrimo dalis
        long t0=System.nanoTime();
        ArrayList<Integer> skaičiai = new ArrayList<Integer>();
        LinkedList<Integer> skaičiai2 = new LinkedList<Integer>();
        for (int i = 0; i < elementųKiekis; i++) {
            skaičiai.add(i);
            skaičiai2.add(i);
        }
        long t1=System.nanoTime();
        System.gc(); System.gc(); System.gc();
        long t2=System.nanoTime();
//  Greitaveikos bandymai ir laiko matavimai
        skaičiai.containsAll(skaičiai);
        long t3=System.nanoTime();
        skaičiai2.containsAll(skaičiai2);
        long t4=System.nanoTime();
        Ks.ouf("%7d %7.4f %7.4f %7.4f %7.4f \n", elementųKiekis,
                (t1-t0)/1e9, (t2-t1)/1e9, (t3-t2)/1e9,
                (t4-t3)/1e9);
    }

    
// sekančio tyrimo metu gaunama normalizuoti įvertinimai su klase TimeKeeper
    void sisteminisTyrimas15Tyr(){
    // Paruošiamoji tyrimo dalis
        Timekeeper tk = new Timekeeper(tiriamiKiekiai);
        for (int kiekis : tiriamiKiekiai) {
        ArrayList<Integer> skaičiai = new ArrayList<Integer>();
        LinkedList<Integer> skaičiai2 = new LinkedList<Integer>();
        for (int i = 0; i < kiekis; i++) {
            skaičiai.add(i);
            skaičiai2.add(i);
        }

    //  Greitaveikos bandymai ir laiko matavimai
            tk.start();
            skaičiai.containsAll(skaičiai);
            tk.finish("ArrayList");
            skaičiai2.containsAll(skaičiai);
            tk.finish("LinkedList");
            tk.seriesFinish();
        }
    }
    
    void ArrayListIrLinkedListPalyginimas(){
        long memTotal = Runtime.getRuntime().totalMemory();
        Ks.oun("memTotal= "+memTotal);
        // Pasižiūrime kaip generuoja automobilius (20) vienetų)
        Ks.oun("1 - Pasiruošimas tyrimui - duomenų generavimas");
        Ks.oun("2 - Pasiruošimas tyrimui - šiukšlių surinkimas");
        Ks.oun("3 - ArrayList containsAll metodas");
        Ks.oun("4 - LinkedList containsAll metodas");
        Ks.ouf("%6d %7d %7d %7d %7d \n", 0,1,2,3,4);
        for(int n: tiriamiKiekiai)
           paprastasTyrimas15Tyr(n);
        // sekančio tyrimo metu gaunama normalizuoti įvertinimai
        sisteminisTyrimas15Tyr();
    }
    
    // Math.sqrt(x*x+y*y)<-->Math.hypot(x, y)
    // sekančio tyrimo metu gaunama normalizuoti įvertinimai su klase TimeKeeper
    void sisteminisTyrimas6Tyr(){
    // Paruošiamoji tyrimo dalis
        Timekeeper tk = new Timekeeper(tiriamiKiekiai);
        for (int kiekis : tiriamiKiekiai) {

    //  Greitaveikos bandymai ir laiko matavimai
            tk.start();
            for (int i = 0; i < kiekis; i++) {
                Math.sqrt(i*i+i*i);
            }
            tk.finish("Math.sqrt");
            for (int i = 0; i < kiekis; i++) {
                Math.hypot(i, i);
            }
            tk.finish("Math.hypot");
            tk.seriesFinish();
        }
    }
    
        void paprastasTyrimas6Tyr(int elementųKiekis){
        //Šiukšlių surinkimas
        long t0=System.nanoTime();
        System.gc(); System.gc(); System.gc();
        //  Greitaveikos bandymai ir laiko matavimai
        long t1=System.nanoTime();
            for (int i = 0; i < elementųKiekis; i++) {
                Math.sqrt(i*i+i*i);
            }
        long t2=System.nanoTime();
            for (int i = 0; i < elementųKiekis; i++) {
                Math.hypot(i, i);
            }
        long t3=System.nanoTime();
        Ks.ouf("%7d %7.4f %7.4f %7.4f \n", elementųKiekis,
                (t1-t0)/1e9, (t2-t1)/1e9, (t3-t2)/1e9);
    }
    
        void SkaiciavimoAtstumoTarpTaskuTyrimas(){
        long memTotal = Runtime.getRuntime().totalMemory();
        Ks.oun("memTotal= "+memTotal);
        //for(Automobilis a: aSeries) Ks.oun(a);
        Ks.oun("1 - Pasiruošimas tyrimui - šiukšlių surinkimas");
        Ks.oun("2 - Math.sqrt");
        Ks.oun("3 - Math.hypot");
        Ks.ouf("%6d %7d %7d %7d \n", 0,1,2,3);
        for(int n: tiriamiKiekiai)
           paprastasTyrimas6Tyr(n);
        // sekančio tyrimo metu gaunama normalizuoti įvertinimai
        sisteminisTyrimas6Tyr();
    }
    
    void TyrimoPasirinkimas(){
        RikiavimoTyrimai();
        //SkaiciavimoAtstumoTarpTaskuTyrimas();
        //ArrayListIrLinkedListPalyginimas();
    }
   public static void main(String[] args){
        //suvienodiname skaičių formatus pagal LT lokalę (10-ainis kablelis)
        Locale.setDefault(new Locale("LT"));
        new GreitaveikosTyrimas().TyrimoPasirinkimas();
   }    
}
