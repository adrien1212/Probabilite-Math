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
     * Creer un fichier CSV à partir d'une ArrayList
     * Chaque élément de l'arrayList est une ligne dans le fichier CSV
     * @param list à écrire
     * @param fichierDestination 
     */
    public static void creerCSV(ArrayList<String> list, String fichierDestination) {
        try(PrintWriter fichier = new PrintWriter(new FileWriter(fichierDestination))) {
           
            /* On parcoure l'ArrayList*/
            for (int i = 0; i < list.size(); i++) {
                fichier.println(list.get(i));
            }
        } catch (IOException e) {
            System.out.println("Erreur durant l'ouverture ou écriture du fichier");
        }
    }
}
