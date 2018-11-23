/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Lab2Jecka;

import java.util.Locale;
import studijosKTU.Ks;
import studijosKTU.ListKTUx;

/**
 *
 * @author Ernestas
 */
public class DialogasSuAdministratoriumi {
        KasininkuApskaita parduotuve = new KasininkuApskaita();
   void bendravimasSuKlientu() {
       
      ListKTUx<Kasininkas> atranka = null;
      int varNr;  // skaičiavimo varijantas pasirenkamas nurodant jo numerį
      String dialogMeniu = "Pasirinkimas: "
            + "1-skaityti iš failo; 2-papildyti sąrašą; 3-jaunų kasininkų atranka;    "
            + "4-atranka pagal lytį;\n 5-seniausiKasininkai; 6-atranka pagal vardą; 7-šiandien gimtadienį švenčiantys kasininkai\n    "
            + "0-baigti skaičiavimus > ";
      while ((varNr = Ks.giveInt(dialogMeniu, 0, 7)) != 0) {
         if (varNr == 1) {
            parduotuve.visiKasininkai.load(Ks.giveFileName());
            parduotuve.visiKasininkai.println("Visų kasininkų sąrašas");
         } else {
            if (varNr == 2) {
                //(String pavarde, String vardas, String gimimoData, int asmensKodas, char lytis)
               String kasininkoDuomenys = Ks.giveString("Nurodykite kasininko pavardę ir vardą, "+
                            "gimimo datą formatu(MMMM-mm-dd), asmensKodą ir lytį\n ");
               Kasininkas k = new Kasininkas();
               k.parse(kasininkoDuomenys);
               String klaidosPožymis = k.validate();
               if (klaidosPožymis.isEmpty()) // dedame tik su gerais duomenimis
                   parduotuve.visiKasininkai.add(k);
               else
                 Ks.oun("!!! Kasininkas į sąrašą nepriimtas "+klaidosPožymis);
            } else {  // toliau vykdomos atskiri atrankos metodai
               switch (varNr) {
                  case 3:
                     int nR = Ks.giveInt("Nurodykite vyriausio kasininko amžių: ");
                     atranka = parduotuve.atrinktiJaunusKasininkus(nR);
                     break;
                  case 4:
                     char lytis = Ks.giveChar("Nurodykite kurios lyties kasininkus atrinkti V/M: ");
                     atranka = parduotuve.atrinktiPagalLytį(lytis);
                     break;
                  case 5:
                     atranka = parduotuve.SeniausiKasininkai();
                     break;
                  case 6:
                     String vardas = Ks.giveString("Parašykite su kokiu vardu kasininkus atrinkti: ");
                     atranka = parduotuve.atrinktiBendravardžius(vardas);
                     break;
                  case 7:
                     atranka = parduotuve.šiandienŠvenčiaGimtadienius();
                     break;
               }
               atranka.println("Štai atrinktų kasininkų sąrašas");
               atranka.save(Ks.giveString
                     ("Kur saugoti atrinktus kasininkus (jei ne-tai ENTER) ? "));
            }
         }
      }
   }
   public static void main(String[] args) {
          // suvienodiname skaičių formatus pagal LT lokalę (10-ainis kablelis)
        Locale.setDefault(new Locale("LT"));
      new DialogasSuAdministratoriumi().bendravimasSuKlientu();
   }
}
