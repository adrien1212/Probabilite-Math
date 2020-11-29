/*
 * testLoiUniforme.java                                                  10 déc. 2019
 * IUT info1 2018-2019 groupe 1, aucun droits : ni copyright ni copyleft 
 */
package testsV2;

import probabiliteV2.LoiUniforme;

/**
 * TODO commenter les responsabilités de cette classe
 * @author MrAdrienBis
 *
 */
public class testLoiUniforme {

    public static void main(String args[] ) {
        double ensembleValeur[] = {1,2,3,4,5,6};
        
        LoiUniforme l = new LoiUniforme(ensembleValeur);
        
        for(int i = 0; i < 20000; i++)
            l.simuler(50);
        
        l.sauvegarder("1000LU.csv");
    }
}
