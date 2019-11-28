/*
 * LoiExponentielle.java                                                  24 oct. 2019
 * IUT info1 2018-2019 groupe 1, aucun droits : ni copyright ni copyleft 
 */
package probabilite;

/**
 * TODO commenter les responsabilités de cette classe
 * @author adrien.caubel
 *
 */
public class LoiExponentielle extends LoiContinue{

    /** paramètre de la loi Exponentielle */
    private double lambda; 
    
    /**
     * @param lambda paramètre de la loi exponentielle
     */
    public LoiExponentielle(double lambda) {
        super(0, Double.MAX_VALUE);
        
        // TODO vérifier lambda > à 0
        if (lambda < 0 || lambda > 1) {
            throw new IllegalArgumentException("lambda doit etre compris entre 0 et 1.");
        }
        this.lambda = lambda;
    }
    
    
    /**
     * @param t la borne
     * @param inferieur true si on veut la probabilité P(X<t)
     *                  false si on veut la probabilité P(X>t)
     * @return la probabilité cumulée
     */
    public double getProbabiliteCumulee(double t, boolean inferieur) {
        if (inferieur) {
            return 1 - Math.exp(-lambda * t);
        } else {
            return Math.exp(-lambda * t);
        }
    }
    
    /**
     * Déterminer la probalité entre un intervalle
     * @param borneInf borne inférieure de l'intervalle
     * @param borneSup borne supérieur de l'intervalle
     * @return la probabilité d'être dans l'intervalle [borneInf, borneSup]
     */
    public double getProbabiteIntervalle(double borneInf, double borneSup) {
        /* On vérifie la validité des bornes */
        if(borneInf < 0) {
            throw new IllegalArgumentException("La borne inférieure doit être supérieur ou égale à 0");
        }
        
        if (borneInf > borneSup) {
            throw new IllegalArgumentException("La borne inférieur doit être stricment inférieur à la borne supérieur");
        }
        
        /* P(a<X<b) = P(X<b) - P(X<a)*/
        return getProbabiliteCumulee(borneSup, true) - getProbabiliteCumulee(borneInf, true); 
    }
    
    
    /**
     * TODO PAS OK
     * Simulation d'un loi exponentielle
     * @param nbSimulation de cette loi
     * @param t le parametre de la simulation P(X<=t) ou P(X>=t)
     * @param inferieur true si on veut la probabilité qu'on soit inférieur à t
     *                  false si on veut la probabilité qu'on soit supérieur à t
     * @return les résultats de la simulation
     */
    public double[] simuler(int nbSimulation, double t, boolean inferieur) {
        
        if (nbSimulation < 0) {
            throw new IllegalArgumentException("Le nombre de simulations doit etre positif");
        }
        
        // tableau des résulats
        double resultatSimulation[] = new double[nbSimulation];
        
        if (inferieur) {
            for (int i = 0; i < nbSimulation; i++) {
                double nbAlea = Math.random();
                resultatSimulation[i] = (-1.0 / this.lambda) * Math.log(1-nbAlea);
            }
        } else {
            for (int i = 0; i < nbSimulation; i++) {
                double nbAlea = Math.random();
                resultatSimulation[i] = 1 - ( (-1.0 / this.lambda) * (Math.log(1-nbAlea)) );
            }
        }
        
        return resultatSimulation;
    }
}
