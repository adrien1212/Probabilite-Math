/*
 * LoiDiscrete.java         18/10/2019
 * IUT RODEZ aucun droit
 */


package testsV2;

import probabiliteV2.LoiDiscrete;

/**
 * Tests de la classe LoiDiscrete
 * @author MrAdrien
 *
 */
public class testLoiDiscrete {


    /** Ensembles de valeurs pour la simulation des lois */
    public static double ENSEMBLE_VALEUR[][] = {
            {1, 2, 3, 4, 5},
            {-8, -7, -3, -2, -1},
            {-8, -3, 0, -2, 3},
            {-12.3, -8, 0, 3.14, 100.6345}
    };

    /** Probabilités associées aux ensembles de valeurs */
    public static double PROBA_ASSOCIEES_INCORRECT[][] = {
            {0.5, -0.3, 0.1, 0.3, 0.4},
            {0.2, 0.1, 0.1, 0.1, 0.2},
            {0.3, 0.1, -0.4, 0.5, 0.2},
            {0.5, 0.2, Double.NaN, 0.1, 0.2}
    };   

    /** Probabilités associées aux ensembles de valeurs */
    public static double PROBA_ASSOCIEES_CORRECT[][] = {
            {0.5, 0.1, 0.1, 0.1, 0.2},
            {0.2, 0.1, 0.4, 0.1, 0.2},
            {0.3, 0.18, 0.1, 0.12, 0.3},
            {0.5, 0.2, 0.0, 0.1, 0.2}
    };
    
    /** TODO commenter le rôle du champ (attribut, rôle associatif...) */
    public static int NB_TESTS = ENSEMBLE_VALEUR.length;
    
    /**
     * TODO commenter le rôle de cette méthode
     */
    private static void testSuivant() {
        System.out.println("\n------------------\n");
    }

    /**
     * TODO commenter le rôle de cette méthode
     */
    @SuppressWarnings("unused")
    public static void testConstructeur() {
        System.out.println("Test gestion des erreurs du constructeur\n");
        int nbTestsReussis = 0;
        for (int i = 0 ; i < ENSEMBLE_VALEUR.length ; i++) {
            try {
                LoiDiscrete ld = new LoiDiscrete(ENSEMBLE_VALEUR[i], PROBA_ASSOCIEES_INCORRECT[i]);
            } catch (IllegalArgumentException e) {
                nbTestsReussis++;
            }
        }
        System.out.println(nbTestsReussis + " tests réussis sur " + ENSEMBLE_VALEUR.length);
    }
    
    /**
     * TODO commenter le rôle de cette méthode
     */
    @SuppressWarnings("unused")
    public static void testGetProbabiliteEgale() {
        System.out.println("Test de la méthode getProbabiliteEgale\n");
        int nbTestsReussis = 0;
        for (int i = 0 ; i < PROBA_ASSOCIEES_CORRECT.length ; i++) {
            
            LoiDiscrete ld = new LoiDiscrete(ENSEMBLE_VALEUR[i], PROBA_ASSOCIEES_CORRECT[i]);
            
            for (int j = 0 ; j < PROBA_ASSOCIEES_CORRECT[i].length ; j++) {
                if (ld.getProbabiliteEgale(ENSEMBLE_VALEUR[i][j]) == PROBA_ASSOCIEES_CORRECT[i][j]) {
                    nbTestsReussis++;
                }
            }
        }
        System.out.println(nbTestsReussis + " tests réussis sur 20");
    }
    
    /**
     * TODO commenter le rôle de cette méthode
     * @param args
     */
    public static void main(String[] args) {
        testConstructeur();
        testSuivant();
        testGetProbabiliteEgale();
        testSuivant();
        
    }
    
}