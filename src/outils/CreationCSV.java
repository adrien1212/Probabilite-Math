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
 * G�n�rer un document au format CSV
 * @author MrAdrien
 *
 */
public class CreationCSV {

    /**
     * Creer un fichier CSV � partir d'une ArrayList
     * Chaque �l�ment de l'arrayList est une ligne dans le fichier CSV
     * @param list � �crire
     * @param fichierDestination 
     */
    public static void creerCSV(ArrayList<String> list, String fichierDestination) {
        try(PrintWriter fichier = new PrintWriter(new FileWriter(fichierDestination))) {
           
            /* On parcoure l'ArrayList*/
            for (int i = 0; i < list.size(); i++) {
                fichier.println(list.get(i));
            }
        } catch (IOException e) {
            System.out.println("Erreur durant l'ouverture ou �criture du fichier");
        }
    }
}
