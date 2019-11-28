/*
 * CreationCSV.java                                                  30 oct. 2019
 * IUT info1 2018-2019 groupe 1, aucun droits : ni copyright ni copyleft 
 */
package outils;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

/**
 * Générer un document au format CSV
 * @author MrAdrien
 *
 */
public class CreationCSV {

    /**
     * @param list à sauvegarder
     * @param nbColonne nombre de colonne par ligne 
     */
    
    /** TODO RAJOUTER COLONNE : NOMBRE DE SIMULATION*/
    
    public static void creerCSV(ArrayList<Double> list, int nbColonne) {
        try(PrintWriter fichier = new PrintWriter(new FileWriter("test.csv"))) {
            StringBuilder sb = new StringBuilder();
            
            // ajout d'une première ligne
            fichier.write("nbRepetition;Probabilite;nbSucces\n");
            
            /* On parcoure l'arrauyList*/
            for (int i = 0; i < list.size() / nbColonne; i++) {
                /* On regroupe par "bloc" (ligne) les informations */
                for(int j = 0 ; j < nbColonne - 1; j++) {
                    sb.append(list.get(i*nbColonne + j)).append(';');
                }
                // on ajoute la dernière colonne de la ligne
                sb.append(list.get(i*nbColonne + nbColonne-1)).append("\n");
                        
                        
                fichier.write(sb.toString());
                sb.delete(0, sb.length()); // on remet à zero la chaine
            }
        } catch (IOException e) {
            System.out.println("Erreur durant l'ouverture ou écriture du fichier");
        }
    
    }
}
