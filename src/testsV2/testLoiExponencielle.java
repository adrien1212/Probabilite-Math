/*
 * testLoiExponencielle.java                                                  30 oct. 2019
 * IUT info1 2018-2019 groupe 1, aucun droits : ni copyright ni copyleft 
 */
package testsV2;

import outils.OutilsTableaux;
import probabiliteV2.LoiExponentielle;

/**
 * Test de la loi exponentielle
 * @author groupe
 *
 */
public class testLoiExponencielle {

    
    /** Jeux de valeurs pour le parametre lambda */
    public static double lambda[] = {1,0.2,0.3,0.5};
    
    
    /**
     * Test unitaire de la méthode getProbabiliteCumulee
     */
    public static void testGetProbabiliteCumulee() {
        System.out.println("Test pour une probabilité du type P(X>t)");
        System.out.println("----------------------------------------");
        
        for (int i = 0 ; i < lambda.length ; i++) {
            LoiExponentielle lE = new LoiExponentielle(lambda[i]);
            System.out.println(lE.getProbabiliteCumulee(7, false));
        }
        
        System.out.println("\nTest pour une probabilité du type P(X<t)");
        System.out.println("----------------------------------------");
        for (int i = 0 ; i < lambda.length ; i++) {
            LoiExponentielle lE = new LoiExponentielle(lambda[i]);
            System.out.println(lE.getProbabiliteCumulee(7, true));
        }
    }
    
    /**
     * Test unitaire de la méthode getProbabiliteIntervalle
     */
    public static void testGetProbabiliteIntervalle() {
        System.out.println("Test pour une probabilité du type P(a<X<b)");
        System.out.println("------------------------------------------");
        for (int i = 0 ; i < lambda.length ; i++) {
            try {
                LoiExponentielle lE = new LoiExponentielle(lambda[i]);
                System.out.println(lE.getProbabiteIntervalle(3, 5));
            } catch(IllegalArgumentException err) {
                System.out.println(err.getMessage());
            }
        }
       
        for (int i = 0 ; i < lambda.length ; i++) {
            try {
                LoiExponentielle lE = new LoiExponentielle(lambda[i]);
                System.out.println(lE.getProbabiteIntervalle(-5, 5));
            } catch(IllegalArgumentException err) {
                System.out.println(err.getMessage());
            }
        }
        
        for (int i = 0 ; i < lambda.length ; i++) {
            try {
                LoiExponentielle lE = new LoiExponentielle(lambda[i]);
                System.out.println(lE.getProbabiteIntervalle(5, 3));
            } catch(IllegalArgumentException err) {
                System.out.println(err.getMessage());
            }
        }
    }
    
    /**
     * Test unitaire de la methode simuler
     */
    public static void testSimuler() {
        
        try {
            LoiExponentielle lE = new LoiExponentielle(lambda[2]);
            OutilsTableaux.tabAfficher(lE.simuler(50));
        } catch(IllegalArgumentException err) {
            System.out.println(err.getMessage());
        }
        

    }
    
    
    /**
     * Lancement des tests
     * @param args non utilisé
     */
    public static void main(String[] args) {
        //testGetProbabiliteCumulee();  test ok
        //testGetProbabiliteIntervalle(); test ok
        testSimuler();
    }
}
