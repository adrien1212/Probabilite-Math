/*
 * testLoiPoisson.java                                                  4 déc. 2019
 * IUT info1 2018-2019 groupe 1, aucun droits : ni copyright ni copyleft 
 */
package testsV2;

import probabiliteV2.LoiBinomiale;
import probabiliteV2.LoiPoisson;

/**
 * TODO commenter les responsabilités de cette classe
 * @author MrAdrienBis
 *
 */
public class testLoiPoisson {

    /**
     * TODO commenter le rôle de cette méthode
     * @param args
     */
    public static void main(String[] args) {
       LoiPoisson lP = new LoiPoisson(10, 0.1);
       System.out.println(lP.getProbabiliteEgale(5));
       System.out.println(lP.getProbabiliteCumulee(3, false, true));
          
       for(double v : lP.simuler(100)) {
           System.out.println(v);
       }
       lP.sauvegarder("poisson.csv");
       
       System.out.println("**************");
       
       LoiPoisson lB = new LoiPoisson(10, 0.1);
       System.out.println(lB.getProbabiliteEgale(5));
       System.out.println(lB.getProbabiliteCumulee(3, false, true));
       
       
       for(double v : lB.simuler(100)) {
           System.out.println(v);
       }

    }

}
