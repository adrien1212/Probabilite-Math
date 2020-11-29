package probabiliteV2;
/* LoiNormal.java 	14/12/2019 */

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import outils.CreationCSV;

/**
 * Repr�sente uen loi normal
 * @author IUT
 * version 1.0
 */
public class LoiNormale {

    private ArrayList<String> sauvegardeTemporaire = new ArrayList<String>();
    
    private double esperance;		// Espérance de la loi
    private double ecartType;		// ecart type de la loi

    /**
     * Constructeur de la loiNormal
     * @param esperance l'esp�rence de la loi
     * @param ecartType	l'ecart type de la loi
     */
    public LoiNormale(double esperance, double ecartType) {
        if(ecartType < 0) {
            throw new IllegalArgumentException("L'�cart type doit �tre positif ou nul");
        }
        /* Initialisation avec affectation */
        this.esperance = esperance; 
        this.ecartType = ecartType;
    }

    /**
     * Permet de simuler la loiNormal
     * @param nbSimulation le nombre de simulation
     * @return un tableau contenant les valeurs de simulations
     */
    public double[] simuler(int nbSimulation) {
        /* Contient un nombre aléatoire */
        double alea1;
        double alea2;

        double t; 	// Contiendra la valeur simuler
        double[] tabSimulation = new double[nbSimulation];	// Contient les simulations

        if (nbSimulation < 0) {
            System.out.println("Veuillez donner un nombre supérieur à 0");
            return null;
        } else {
            for (int i = 0; i < nbSimulation ; i++ ) {
                /* On fait la simulation */
                alea1 = Math.random();
                alea2 = Math.random();

                t = ecartType * (Math.sqrt(-2 * Math.log(alea1)) * Math.cos(2 * Math.PI * alea2)) + esperance; 
                tabSimulation[i] = t;
            }
        }
        
        this.sauvegarderTempo(tabSimulation);

        return tabSimulation;
    }
    
    /**
     * On va utiliser le fichier de fonction de r�partition
     * @param t le valeur � rechercher
     * @param inferieur
     * @return la probabilit� cumul�e 
     */
    public double getProbabiliteCumulee(double t, boolean inferieur) {
        /* On centre et on r�duit la loi normale */
        
        double T = (t-esperance)/ecartType;
        double proba = 0.0;
        double col0 = 0.0; // valeur de la colonne 0 dans le fichier de la Fonction de r�partition
        String ligne;
        
        boolean estNegatif = T < 0;
        
        /* Si T est n�gatif on le passe en positif temporairement
         * pour pouvoir d�terminer la probabilit�
         */
        T = estNegatif ? -T : T;
        
        
        /* On recherche la probilit� cumul�e � T */
        String lignePrecedente = null;
        try (BufferedReader table = new BufferedReader(new FileReader("src/outils/FonctionRepartitionLoiNormale.txt"))) {
            /* Etant donn�e que ligne ira une ligne trop loin, on doit revenir en arri�re
             * de une ligne
             */
            while ( (ligne = table.readLine()) != null && (col0 = Double.parseDouble(ligne.split(" ")[0])) <= T) {
                lignePrecedente = ligne;
            }
            /* */
            col0 = col0-0.1;
            
            /* On cherche la bonne colonne */
            if (ligne != null) {
                    /* On d�coupe la ligne en plusieurs parties */
                    String[] laLigne = lignePrecedente.split(" ");
                    int i;
                    for (i=1; i < laLigne.length-1 && T > (col0)+((i-1)*0.01); i++) {
                    }
                    proba = Double.parseDouble(laLigne[i]);
            } else {
                    proba = 1; // Cas si t est plus grand que les valeurs de la table
            }
            
            proba = estNegatif ? 1.0-proba : proba;
            
        } catch (IOException e) {
            System.out.println("Pb d'acces au fichier");
        }
        
        if(inferieur) {
            return proba;
        } else {
            return 1.0-proba;
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
    
    public void sauvegarderTempo(double[] resultat) {
        String aSave = null;
        String ligneIntro = "esperance;variance;resultat";
        sauvegardeTemporaire.add(ligneIntro);
        for(int i = 0; i < resultat.length; i++) {
            aSave = esperance + ";" + ecartType + ";" + resultat[i];
            aSave = aSave.replaceAll("\\.", ",");
            sauvegardeTemporaire.add(aSave);
        }
    }

    public void sauvegarder(String fichierDestination) {
        CreationCSV.creerCSV(sauvegardeTemporaire, fichierDestination);
    }
    
    /**
     * Affiche les valeurs d'un tableau
     * @param tab
     */
    public void afficherValeur(double[] tab) {
        for (int i = 0; i < tab.length ; i++ ) {
            System.out.println(tab[i]);
        }
    }
}