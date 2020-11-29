/*
 * LoiPoisson.java                                                  4 déc. 2019
 * IUT info1 2018-2019 groupe 1, aucun droits : ni copyright ni copyleft 
 */
package probabiliteV2;

import outils.OutilsProbabilite;

/**
 * On préfèrera utilise rune loi de Poisson pour diminuer le coup calcul
 * lorsqu'une loi binomiale à un n grand et un p petit
 * @author Groupe
 *
 */
public class LoiPoisson extends LoiDiscrete {

    private double esperance; // paramètre d'un loi de Poisson
    private int n;
    private double p;
    
    public LoiPoisson(int n, double p) {
        super(construireValeurPossible(n),construireProbabilite(n, p));
        esperance = n*p;
        this.n = n;
        this.p = p;
    }
    
    /**
     * @param n le nombre de succès
     * @return un tableau de double avec les valeurs possible de n
     */
    private static double[] construireValeurPossible(int n) {
        double[] aRetourner = new double[n+1];
        
        for(int i = 0; i <= n; i++) {
            aRetourner[i] = i;
        }
        return aRetourner;
    }
    
    /**
     * @param n le nombre de répétition
     * @param p la probabilité d'un succès
     * @return un tableau de double avec l'emsemble des probabilité associées aux valeurs
     */
    private static double[] construireProbabilite(int n, double p) {
        double[] aRetourner = new double[n+1];
        double esperance = n*p;
        for(int i = 0; i <= n; i++) {
            aRetourner[i] = Math.exp(-esperance) * ( (Math.pow(esperance, i)) / OutilsProbabilite.factorielle(i) );
        }
       
        return aRetourner;
    }
    
    
    /**
     * Approximation du résultat par une loi de Poisson
     * @param k valeur dont on veut savoir la probabilité
     * @return la probabilité d'etre égal à k : P(X=k)
     */
    @Override
    public double getProbabiliteEgale(double k) {
        return Math.exp(-esperance) * (Math.pow(esperance, k)) / (OutilsProbabilite.factorielle((int) k));
    }
    
    
    public double getProbabiliteCumulee(double k, boolean inferieur, boolean ouEgale) {

        if(k < 0 || k > n) {
            throw new IllegalArgumentException("k doit être compris entre 0 et " + n);
        }
        
        double probaCumume = 0;
        
        if(inferieur) {
            if (ouEgale) {
                /* inférieur  ou égale */
                for (int i = 0; i <= k; i++) {
                    probaCumume += Math.exp(-esperance) * ( (Math.pow(esperance, i)) / OutilsProbabilite.factorielle(i) );
                }
            } else {
                /* pas égal */
                for (int i = 0; i < k; i++) {
                    probaCumume += Math.exp(-esperance) * ( (Math.pow(esperance, i)) / OutilsProbabilite.factorielle(i) );
                }
            }
            return probaCumume;
        } else {
            if (ouEgale) {
                return 1 - getProbabiliteCumulee(k, true, false);
            } else {
                return 1 - getProbabiliteCumulee(k, true, true);
            }
        }
    }
    
    
}
