/*
 * LoiBinomiale.java                                                  20 nov. 2019
 * IUT info1 2018-2019 groupe 1, aucun droits : ni copyright ni copyleft 
 */
package probabiliteV2;

import java.util.ArrayList;

import outils.CreationCSV;
import outils.OutilsProbabilite;

/**
 * TODO commenter les responsabilités de cette classe
 * @author groupe
 *
 */
public class LoiBinomiale extends LoiDiscrete {
   
    private int n;
    private double p;
    
    /**
     * @param n le nombre de succès
     * @param p la probabité d'un succès
     */
    public LoiBinomiale(int n, double p) {
        super(construireValeurPossible(n), construireProbabilite(n, p));
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
        double somme = 0;
        /* Si n est trop grand on préfère utiliser une loi normale */
        if(n <= 30) {
            for(int i = 0; i <= n; i++) {
                aRetourner[i] = OutilsProbabilite.coefficientBinomial(i, n) * Math.pow(p, i) * Math.pow(1-p, n-i);
                somme+=aRetourner[i];
            }
        } else {
            LoiNormale loiN = new LoiNormale(n*p, Math.sqrt(n*p*(1-p)));
            aRetourner[0] = 0; // on suppose que c'est 0 pour la proba qu'il y est 0 succès 
            for(int i = 1; i <= n; i++) {
                aRetourner[i] = loiN.getProbabiteIntervalle(i-0.5, i+0.5);
            }
        }
        return aRetourner;
    }
    
    @Override
    public int simuler(int nbSimulation, double valeur) {
        if(valeur < 0 || valeur > n) {
            throw new IllegalArgumentException("La valeur doit être comprise entre 0 et " + n);
        }
        
        return super.simuler(nbSimulation, valeur);
    }
    
    @Override
    public void sauvegarderTempo(double[] resultat) {
        String ligneIntro = "NbRepetition;probaSucces;NbSucces;NbSimulé";
        sauvegardeTemporaire.add(ligneIntro);
        for (int i = 0; i < resultat.length; i++) {
            String aSave = n + ";" + p + ";" + i + ";" + resultat[i];
            aSave = aSave.replaceAll("\\.", ",");
            sauvegardeTemporaire.add(aSave);
        }
    }
    
    
    /**
     * Sauvegarde le contenu d'une arrayList dans un fichier csv
     * @param fichierDestination fichier ou on fait la sauvegarde
     */
    @Override
    public void sauvegarder(String fichierDestination) {
        CreationCSV.creerCSV(sauvegardeTemporaire, fichierDestination); 
        System.out.println("Sauvegarde de la loi binomiale effectuée");
    }
}
