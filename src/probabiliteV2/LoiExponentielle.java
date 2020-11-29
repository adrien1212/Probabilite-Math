/*
 * LoiExponentielle.java                                                  24 oct. 2019
 * IUT info1 2018-2019 groupe 1, aucun droits : ni copyright ni copyleft 
 */
package probabiliteV2;

import java.util.ArrayList;

import outils.CreationCSV;

/**
 * TODO commenter les responsabilit�s de cette classe
 * @author groupe
 *
 */
public class LoiExponentielle{

    private ArrayList<String> sauvegardeTemporaire = new ArrayList<String>();
    
    /** param�tre de la loi Exponentielle */
    private double lambda; 
    
    /**
     * @param lambda param�tre de la loi exponentielle
     */
    public LoiExponentielle(double lambda) {
        if (lambda <= 0 || lambda > 1) {
            throw new IllegalArgumentException("lambda doit etre compris entre 0 et 1.");
        }
        this.lambda = lambda;
    }
    
    
    /**
     * @param t la borne
     * @param inferieur true si on veut la probabilit� P(X<t)
     *                  false si on veut la probabilit� P(X>t)
     * @return la probabilit� cumul�e
     */
    public double getProbabiliteCumulee(double t, boolean inferieur) {
        if (inferieur) {
            return 1 - Math.exp(-lambda * t);
        } else {
            return Math.exp(-lambda * t);
        }
    }
    
    /**
     * D�terminer la probalit� entre un intervalle
     * @param borneInf borne inf�rieure de l'intervalle
     * @param borneSup borne sup�rieur de l'intervalle
     * @return la probabilit� d'�tre dans l'intervalle [borneInf, borneSup]
     */
    public double getProbabiteIntervalle(double borneInf, double borneSup) {
        /* On v�rifie la validit� des bornes */
        if(borneInf < 0) {
            throw new IllegalArgumentException("La borne inf�rieure doit �tre sup�rieur ou �gale � 0");
        }
        
        if (borneInf > borneSup) {
            throw new IllegalArgumentException("La borne inf�rieur doit �tre stricment inf�rieur � la borne sup�rieur");
        }
        
        /* P(a<X<b) = P(X<b) - P(X<a)*/
        return getProbabiliteCumulee(borneSup, true) - getProbabiliteCumulee(borneInf, true); 
    }
    
    
    /**
     * Simulation d'un loi exponentielle
     * @param nbSimulation de cette loi
     * @return les r�sultats de la simulation
     */
    public double[] simuler(int nbSimulation) {
        
        if (nbSimulation < 0) {
            throw new IllegalArgumentException("Le nombre de simulations doit etre positif");
        }
        
        
        // tableau des r�sulats
        double resultatSimulation[] = new double[nbSimulation];
        
        for (int i = 0; i < nbSimulation; i++) {
            double nbAlea = Math.random();
            resultatSimulation[i] = (-1.0 / this.lambda) * Math.log(1-nbAlea);
        }  
        
        this.sauvegarderTempo(resultatSimulation);
        
        return resultatSimulation;
    }
    
    /**
     * @param resultat de la simulation
     */
    public void sauvegarderTempo(double[] resultat) {
        String aSave = null;
        String ligneIntro = "Lambda;Resultat";
        sauvegardeTemporaire.add(ligneIntro);
        for(int i = 0; i < resultat.length; i++) {
            aSave = lambda + ";" + resultat[i];
            aSave = aSave.replaceAll("\\.", ",");
            sauvegardeTemporaire.add(aSave);
        }
    }
    
    public void sauvegarder(String fichierDestination) {
        CreationCSV.creerCSV(sauvegardeTemporaire, fichierDestination);
    }
}
