/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Lab2Jecka;

import Lab2Jecka.Kasininkas;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import studijosKTU.KTUable;
import studijosKTU.Ks;

/**
 *
 * @author Ernestas
 */
public class Kasininkas implements KTUable<Kasininkas> {
    
    final static private int priimtinųMetųRiba = LocalDate.now().getYear() - 64;
    final static private int esamiMetai  = LocalDate.now().getYear();
    SimpleDateFormat formatuoti = new SimpleDateFormat("yyyy-MM-dd");
    
    private String pavarde;
    private String vardas;
    private Date gimimoData;
    private int asmensKodas;
    private char lytis;
    
    public Kasininkas(){
    }
    
    public Kasininkas(String vardas, String pavarde, String gimimoData, int asmensKodas, char lytis){
        try{
            this.pavarde = pavarde;
            this.vardas = vardas;
            this.gimimoData = formatuoti.parse(gimimoData);
            this.asmensKodas = asmensKodas;
            this.lytis = lytis;
        }catch(ParseException e){
            e.printStackTrace();
        }
    }

        @Override
    public String toString(){  // surenkama visa reikalinga informacija
        return String.format("%-8s %-8s %4s %7d %8c",
               pavarde, vardas, formatuoti.format(gimimoData), asmensKodas, lytis);
    };
    
    public String getPavarde(){
        return pavarde;
    }
    public String getVardas(){
        return vardas;
    }
    public Date getGimimoData(){
        return gimimoData;
    }
    public int getAsmensKodas(){
        return asmensKodas;
    }
    public int getLytis(){
        return lytis;
    }
    
    public int getAmžius(){
        Date Šiandien = new Date();
        long skirtumasMiliSekundemis = Math.abs(Šiandien.getTime() - this.gimimoData.getTime());
        long skirtumasDienomis = TimeUnit.DAYS.convert(skirtumasMiliSekundemis, TimeUnit.MILLISECONDS);
        double skirtumasMetais = skirtumasDienomis / 365.25;
        return (int)skirtumasMetais;
    }
    
    @Override
    public Kasininkas create(String duomenuEilute) {
         Kasininkas a = new Kasininkas();
        a.parse(duomenuEilute);
        return a;
    }

    @Override
    public String validate() {
        String klaidosTipas = "";
        if (gimimoData.getYear() < priimtinųMetųRiba || gimimoData.getYear() > esamiMetai - 18)
           klaidosTipas = "Kasininkas negali buti jaunesnis nei 18 ir vyresnis nei 64 metai";
        return klaidosTipas;
    }

    @Override
    public void parse(String duomenuEilute) {
            try {   // ed - tai elementarūs duomenys, atskirti tarpais
                SimpleDateFormat formatuoti = new SimpleDateFormat("yyyy-MM-dd");
                Scanner ed = new Scanner(duomenuEilute);
                vardas = ed.next();
                pavarde   = ed.next();
                gimimoData = formatuoti.parse(ed.next());
                asmensKodas = ed.nextInt();
                lytis = ed.next().charAt(0);
        } catch (InputMismatchException  e) {
            Ks.ern("Blogas duomenų formatas apie auto -> " + duomenuEilute);
        } catch (NoSuchElementException e) {
            Ks.ern("Trūksta duomenų apie auto -> " + duomenuEilute);
        } catch (ParseException e){
            e.printStackTrace();
        }
    }

    @Override
    public int compareTo(Kasininkas e) {
        if(this.gimimoData.before(e.getGimimoData())) {
            return 1;
        }else if(this.gimimoData.equals(e.getGimimoData())){
            return 0;
        }
        return -1;
    }
    
    public final static Comparator<Kasininkas> pagalVardusPavardes =
              new Comparator<Kasininkas>() {
       @Override
       public int compare(Kasininkas k1, Kasininkas k2) {
          int cmp = k1.getVardas().compareTo(k2.getVardas());
          if(cmp != 0) return cmp;
          return k1.getPavarde().compareTo(k2.getPavarde());
       }
    };
    public final static Comparator pagalAmžių = new Comparator() {
       // sarankiškai priderinkite prie generic interfeiso ir Lambda funkcijų
       @Override
       public int compare(Object o1, Object o2) {
          double k1 = ((Kasininkas) o1).getAmžius();
          double k2 = ((Kasininkas) o2).getAmžius();
          // didėjanti tvarka, pradedant nuo mažiausios
          if(k1<k2) return -1;
          if(k1>k2) return 1;
          return 0;
       }
    };
    
     public static void main(String... args){
    } 
    
}
