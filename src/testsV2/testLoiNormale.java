/*
 * testLoiNormale.java                                                  16 déc. 2019
 * IUT info1 2018-2019 groupe 1, aucun droits : ni copyright ni copyleft 
 */
package testsV2;

import probabiliteV2.LoiBinomiale;
import probabiliteV2.LoiNormale;

/**
 * TODO commenter les responsabilités de cette classe
 * @author adrien.caubel
 *
 */
public class testLoiNormale {

    public static void main(String[] args) {
        LoiNormale loiN = new LoiNormale(20, 4);
        
        System.out.println(loiN.getProbabiliteCumulee(12, true));
        System.out.println(loiN.getProbabiliteCumulee(13, true));
        
        System.out.println(loiN.getProbabiteIntervalle(12.5, 13.5));
    }
}
