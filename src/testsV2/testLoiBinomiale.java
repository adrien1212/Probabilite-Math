package testsV2;

import probabiliteV2.LoiBinomiale;
import probabiliteV2.LoiDiscrete;
import outils.OutilsTableaux;

/**
 * Une loi Binomiale se d�finie par la r�p�tition d'une m�me exp�rience de facon
 * identique est ind�pendante 
 * @author Groupe
 *
 */
public class testLoiBinomiale {

    /** Jeux de valeurs pour le parametre p de la loi Binomiale  */
    public static double ENSEMBLE_VALEUR[] = {0.2, 0.2, -0.2, 2.2, -0.2, 2.2, 0.5, 0.3};
    
    /** Jeux de valeurs pour le parametre n de la loi Binomiale */
    public static int ENSEMBLE_VALEUR2[] = {10, -10, 10, 10, -10, -10, 20, 100};
    
    /**
     * Tests unitaires pour le constructeur
     */
    public static void testBinomiale() {
        int nbTestsReussi = 0;
        
        for (int i = 0 ; i < ENSEMBLE_VALEUR2.length ; i++) {
            try {
                LoiBinomiale lB = new LoiBinomiale(ENSEMBLE_VALEUR2[i], ENSEMBLE_VALEUR[i]);
            } catch(IllegalArgumentException err) {
            System.out.println(err.getMessage());
            }
            nbTestsReussi++;
        }
        System.out.println("\nIl y a " + nbTestsReussi + " tests r�ussis sur les 6 tests.");
    }
    
    /**
     * tests unitaires de la m�thode simuler
     */
    public static void testSimuler() {
        
        System.out.println("Test de la simulation de la loi Binomiale");
        System.out.println("-----------------------------------------\n");
        
        try {
            LoiBinomiale lB = new LoiBinomiale(ENSEMBLE_VALEUR2[0], ENSEMBLE_VALEUR[0]);
            OutilsTableaux.tabAfficher(lB.simuler(3000));
            OutilsTableaux.tabAfficher(lB.simuler(2000));
        } catch(IllegalArgumentException err) {
            System.out.println(err.getMessage()); 
        }
    }



    /**
     * tests unitaires de la m�thode getProbabiliteEgale
     */
    public static void testGetProbabiliteEgale() {
        LoiBinomiale lB = new LoiBinomiale(ENSEMBLE_VALEUR2[0], ENSEMBLE_VALEUR[0]);
        
        for (int i = 0; i <= ENSEMBLE_VALEUR2[0]; i++) {
            System.out.println(lB.getProbabiliteEgale(i));
        }
    }

    /**
     * tests unitaires de la m�thode testGetProbabiliteContinue
     */
    public static void testGetProbabiliteContinue() {
    
        for (int i = 0 ; i < ENSEMBLE_VALEUR2.length ; i++) {
            try {
                LoiBinomiale lB = new LoiBinomiale(ENSEMBLE_VALEUR2[i], ENSEMBLE_VALEUR[i]);
                for (int j = 0; j <= 5; j++) {
                    System.out.println(lB.getProbabiliteCumulee(i, true, false));
                }
            } catch(IllegalAccessError err) {
                System.out.println(err.getMessage());
            }  
        }     
    }


    /**
     * Lancement des tests
     * @param args non utilis�
     */
    public static void main(String[] args) {
        //testBinomiale();
        //testSimuler();
        //testGetProbabiliteEgale();
        //testGetProbabiliteContinue(); 
        
        /* Pour adrien - sauvegarde */
        LoiBinomiale lB = new LoiBinomiale(10, 0.2);
        lB.simuler(50);
        lB.simuler(100);
        lB.simuler(1000);
        lB.sauvegarder("testSaveBinomiale.csv");
    }

}
