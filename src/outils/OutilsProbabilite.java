/*
 * LoiDiscrete.java         18/10/2019
 * IUT RODEZ aucun droit
 */

package outils;

public class OutilsProbabilite {

    /**
     * Calcul du coefficient binomial 
     * @param n nombre de fois où on réalise l'expérience
     * @param k le nombre de succès
     * @return coefficient binomial
     */
    public static long coefficientBinomial(int k, long n) {
        if ((n == k) || (k == 0)) {
            return 1;
        } else {
            return coefficientBinomial(k, n - 1) + coefficientBinomial(k - 1, n - 1);
        }
    }

    
    /**
     * Factorielle d'un nombre
     * @param n le nombre dont on veut la factorielle
     * @return la factorielle de n
     */
    public static double factorielle(int n) {
        if (n < 2) {
            return 1;
        }
        return n * factorielle(n - 1);
    }
}
