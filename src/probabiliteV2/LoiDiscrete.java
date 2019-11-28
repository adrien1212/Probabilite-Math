/*
 * LoiDiscrete.java                                                  20 nov. 2019
 * IUT info1 2018-2019 groupe 1, aucun droits : ni copyright ni copyleft 
 */
package probabiliteV2;

import java.util.ArrayList;

import outils.CreationCSV;
import outils.OutilsTableaux;

/**
 * TODO commenter les responsabilit�s de cette classe
 * @author adrien.caubel      
 *
 */
public class LoiDiscrete {

    protected ArrayList<String> sauvegardeTemporaire = new ArrayList<String>();
    
    private double[] ensembleValeur;
    private double[] probabiliteValeur;
    
    public LoiDiscrete(double[] ensembleValeur, double[] probabiliteValeur) {
          
        if(ensembleValeur.length != probabiliteValeur.length) {
            throw new IllegalArgumentException("Il n'y a pas le m�me nombre de valeur que de probabilit�");
        }
        
        if(!estValeurCorrecte(probabiliteValeur)) {
            throw new IllegalArgumentException("Au moins une valeur est n�gative");
        }
        
        if(estAvecDoublons(ensembleValeur)) {
            throw new IllegalArgumentException("Il y a des doublons dans l'ensemble des valeurs");
        }
        
        if(!estSommeCorrecte(probabiliteValeur)) {
            throw new IllegalArgumentException("La somme des valeurs n'est pas �gale � 1");
        }

        this.ensembleValeur = ensembleValeur;
        this.probabiliteValeur = probabiliteValeur;
        
        //TODO FAIRE LE CALCUL ESPACE VARIANCE ET ECART TYPE
        // private esperance
    }
     
    /**
     * La somme des probabilit� doit �tre �gale � 1
     * @param probabiliteASommer
     * @return true si la somme des probabilit� est �gale � 1
     *         false si la somme des probabilit� est diff�rente de 1
     */
    private static boolean estSommeCorrecte(double[] probabiliteASommer) {
        double somme = 0.0;
        
        for(int i = 0; i < probabiliteASommer.length; i++) {
            somme+=probabiliteASommer[i];
        }

        return nbEgaux(somme, 1);
    }
    
   
    /**
     * V�rifie si 2 double sont �gaux � Epsilon pr�s
     * @return true s'il sont �gaux
     *         false sinon
     */
    private static boolean nbEgaux(double a, double b) {
        double Epsilon = 0.0001;
        return Math.abs(b-a) < Epsilon;
    } 
    
    /**
     * Chaque probabilit� doit �tre sup�rieur ou �gale � 0
     * @param probabiliteAVerifier
     * @return true si aucune valeur n'est inf�rieur � 0
     *         false si une valeur est sup�rieur � 0
     */
    private static boolean estValeurCorrecte(double[] probabiliteAVerifier) {
        for(int i = 0; i < probabiliteAVerifier.length; i++) {
            if(probabiliteAVerifier[i] < 0) {
                return false;
            }
        }
        return true;
    }
    
    public static boolean estAvecDoublons(double[] ensembleValeur) {
        for(int i = 0; i < ensembleValeur.length; i++) {
            for (int j = i+1; j < ensembleValeur.length; j++) {
                if(ensembleValeur[i] == ensembleValeur[j]) {
                    return true;
                }
            }
        }
        return false;
    }
    
    /**
     * @param k valeur dont on veut savoir la probabilit�
     * @return la probabilit� d'etre �gal � k : P(X=k)
     */
    public double getProbabiliteEgale(double k) {

        /* On v�rifie que k est bien dans l'ensemble des valeurs */
        int indiceValeur = OutilsTableaux.estDansTableau(ensembleValeur, k);
        if(indiceValeur < 0) {
            throw new IllegalArgumentException("la valeur " + k + "n'est pas dans l'ensemble des valeurs");
        }

        return probabiliteValeur[indiceValeur];
    }
    
    
    /**
     * @param k valeur dont on veut savoir la probabilit�
     * @param inferieur true si on veut la probabilit� d'�tre inf�rieur � k
     *                  false si on veut la probabilit� d'�tre sup�rieur � k
     * @param ouEgale   true si on prend en compte l'�galati�
     *                  false si in�galit�
     * @return la probabilt� d'�tre inf�rieur ou sup�rieur suivant le choix de l'utilisateur
     */
    public double getProbabiliteCumulee(double k, boolean inferieur, boolean ouEgale) {

        //TODO v�rifier que k n'est pas inf�rieur au minimum de l'ensemble des valeur
        // et pas sup�m�rieur au maximum de l'ensemble des valeurs
        
        double probaCumume = 0;
        
        if(inferieur) {
            if (ouEgale) {
                /* inf�rieur  ou �gale */
                for (int i = 0; i < ensembleValeur.length && ensembleValeur[i] <= k; i++) {
                    System.out.println(ensembleValeur[i]);
                    probaCumume += probabiliteValeur[i];
                }
            } else {
                /* pas �gal */
                for (int i = 0; i < ensembleValeur.length && ensembleValeur[i] < k; i++) {
                    System.out.println(ensembleValeur[i]);
                    probaCumume += probabiliteValeur[i];
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
    
    
    /**
     * Simulation de la loi Discr�te avec tout l'ensemble des valeurs
     * @param nbSimulation nombre de simulation � r�aliser
     * @return un tableau avec pour chaque valeur de l'ensemble des valeurs
     *         le nombre de succ�s
     */
    public double[] simuler(int nbSimulation) {
        
        double resultat[] = new double[ensembleValeur.length];

        double nbAlea; // Nombre al�toire g�n�r�

        boolean estCompris; // true si ensembleValeur[i] < nbAlea && nbAlea < ensembleValeur[i+1]


        for (int i = 0; i < nbSimulation; i++) {
            estCompris = false;
            nbAlea = Math.random();

            for (int j = 0; j < ensembleValeur.length && !estCompris; j++) {

                /**
                 * On cherche nbAlea � [p0 + p1 + ... + pn-1, p0 + p1 + ... + pn]
                 * ex :  p � [p0 + p1, p0 + p1 + p2[ == p - p0 - p1 < p2
                 */
                if (nbAlea < probabiliteValeur[j]) {
                    resultat[j]++;
                    estCompris = true;
                } else {
                    nbAlea -= probabiliteValeur[j];
                }      
            }
        }
        
        /* Lors de chaque simulation, on fait une sauvegarde en pr�vision de la cr�ation
         * du fichier CSV
         */
        this.sauvegarder(resultat);
        
        return resultat;
    }
    
    /**
     * @param nbSimulation de l'emsemble des valeur
     * @param valeur o� ont veut savoir le nombre de fois o� on tombe dessus
     * @return le nombre de fois o� la valeur valeur est tomb�
     */
    public int simuler(int nbSimulation, double valeur) {
        int nbValeurSucces = 0;
        double nbAlea; 
        double probaCumule = 0; // proba cumul� de la valeur pass� en param�tre
     
        /* V�rifier que la valeur est bien dans l'emsemble des valeurs */
        int indiceValeur = OutilsTableaux.estDansTableau(ensembleValeur, valeur);
        if(indiceValeur < 0) {
            throw new IllegalArgumentException("la valeur " + valeur + "n'est pas dans l'ensemble des valeurs");
        }

        
        for (int i = 0; i < indiceValeur; i++) {
            probaCumule += probabiliteValeur[i];
        }

        for (int i = 0; i < nbSimulation; i++) {
            nbAlea = Math.random();
            if(nbAlea > probaCumule && nbAlea <= (probaCumule+probabiliteValeur[indiceValeur]))
                nbValeurSucces++;
        }
        return nbValeurSucces;
    }
    
    public void sauvegarder(double[] resultat) {
        String aSave;
        String ligneIntro = "EnsembleValeurs;ProbaAssiciee;NbSimul�";
        sauvegardeTemporaire.add(ligneIntro);
        for(int i = 0; i < resultat.length; i++) {
            aSave = ensembleValeur[i] + ";" + probabiliteValeur[i] + ";" + resultat[i];
            sauvegardeTemporaire.add(aSave);
        }
        CreationCSV.creerCSV(sauvegardeTemporaire);
    }
}
