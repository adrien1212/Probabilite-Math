/*
 * LoiUniforme.java                                                  20 nov. 2019
 * IUT info1 2018-2019 groupe 1, aucun droits : ni copyright ni copyleft 
 */
package probabiliteV2;

import outils.OutilsTableaux;

/**
 * TODO commenter les responsabilités de cette classe
 * @author adrien.caubel
 *
 */
public class LoiUniforme extends LoiDiscrete{

    private double p;
    
    /**
     * @param ensembleValeur
     */
    public LoiUniforme(double[] ensembleValeur) {
        super(ensembleValeur, construireProbilite(ensembleValeur.length));
        this.p = 1.0/ensembleValeur.length;
    }
    
    /**
     * @param longueurTableau = nombre d'éléménet dans la loi Uniforme
     * @return
     */
    private static double[] construireProbilite(int longueurTableau) {
        double[] aRetourner = new double[longueurTableau];
        double probabilite = 1.0 / longueurTableau;
        
        for(int i = 0; i < longueurTableau; i++) {
            aRetourner[i] = probabilite;
        }
        return aRetourner;
        
    }
    
    public double getP() {
        return p;
    }
    
//    @Override
//    public double getProbabiliteEgale(double k) {
//        int indiceValeur = OutilsTableaux.estDansTableau(ensembleValeur, k);
//        if(indiceValeur < 0) {
//            throw new IllegalArgumentException("la valeur " + k + "n'est pas dans l'ensemble des valeurs");
//        }
//        
//        return p;
//    }

    
    

    
}
