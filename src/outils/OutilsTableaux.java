/*
 * LoiDiscrete.java         18/10/2019
 * IUT RODEZ aucun droit
 */


package outils;

/**
 * Outils utilitaires pour la gestion de tableaux
 * @author MrAdrien
 *
 */
public class OutilsTableaux {

    /**
     * Afficher tableau à 2 dimensions
     * @param aAfficher tableau à afficher
     */
    public static void tabAffichage(double[][] aAfficher) {
        for(double valeur : aAfficher[0]) {
            System.out.print(valeur + "\t");
        }

        System.out.println();

        for(double valeur : aAfficher[1]) {
            System.out.print(valeur + "\t");
        }

        System.out.println("\n");

    }
    
    /**
     * Afficher une tableau à une dimension
     * @param aAfficher tableau à afficher
     */
    public static void tabAfficher(double[] aAfficher) {
        for(double valeur : aAfficher) {
            System.out.println(valeur + "\t");
        }
    }


    /**
     * Vérifier si une valeur est contenue dans un tableau
     * @param T tableau ou on cherche la valeur
     * @param valeur à chercher dans le tableau
     * @return l'indice où se trouve la valeur si elle existe
     *         -1 sinon
     */
    public static int estDansTableau(double T[], double valeur){
        for(int i = 0 ; i<T.length;i++){
            if(valeur==T[i])
                //retourner la position courante
                return i;
        }
        System.out.println("La valeur recherchée n'existe pas");
        return -1;
    }
}
