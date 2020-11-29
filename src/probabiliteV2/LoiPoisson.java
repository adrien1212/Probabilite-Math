/*
 * LoiPoisson.java                                                  4 d�c. 2019
 * IUT info1 2018-2019 groupe 1, aucun droits : ni copyright ni copyleft 
 */
package probabiliteV2;

import outils.OutilsProbabilite;

/**
 * On pr�f�rera utilise rune loi de Poisson pour diminuer le coup calcul
 * lorsqu'une loi binomiale � un n grand et un p petit
 * @author Groupe
 *
 */
public class LoiPoisson extends LoiDiscrete {

    private double esperance; // param�tre d'un loi de Poisson
    private int n;
    private double p;
    
    public LoiPoisson(int n, double p) {
        super(construireValeurPossible(n),construireProbabilite(n, p));
        esperance = n*p;
        this.n = n;
        this.p = p;
    }
    
    /**
     * @param n le nombre de succ�s
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
     * @param n le nombre de r�p�tition
     * @param p la probabilit� d'un succ�s
     * @return un tableau de double avec l'emsemble des probabilit� associ�es aux valeurs
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
     * Approximation du r�sultat par une loi de Poisson
     * @param k valeur dont on veut savoir la probabilit�
     * @return la probabilit� d'etre �gal � k : P(X=k)
     */
    @Override
    public double getProbabiliteEgale(double k) {
        return Math.exp(-esperance) * (Math.pow(esperance, k)) / (OutilsProbabilite.factorielle((int) k));
    }
    
    
    public double getProbabiliteCumulee(double k, boolean inferieur, boolean ouEgale) {

        if(k < 0 || k > n) {
            throw new IllegalArgumentException("k doit �tre compris entre 0 et " + n);
        }
        
        double probaCumume = 0;
        
        if(inferieur) {
            if (ouEgale) {
                /* inf�rieur  ou �gale */
                for (int i = 0; i <= k; i++) {
                    probaCumume += Math.exp(-esperance) * ( (Math.pow(esperance, i)) / OutilsProbabilite.factorielle(i) );
                }
            } else {
                /* pas �gal */
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
