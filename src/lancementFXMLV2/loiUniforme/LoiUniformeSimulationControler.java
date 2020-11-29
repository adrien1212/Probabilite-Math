/*
 * LoiDiscreteControler.java                                                  13 déc. 2019
 * IUT info1 2018-2019 groupe 1, aucun droits : ni copyright ni copyleft 
 */
package lancementFXMLV2.loiUniforme;


import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import probabiliteV2.LoiUniforme;

/**
 * TODO commenter les responsabilités de cette classe
 * @author MrAdrienBis
 *
 */
public class LoiUniformeSimulationControler extends Application {

    @FXML
    private TextField valeurTF;
    @FXML  
    private Label probabiliteLabel;
    @FXML
    private TextField nbSimulationTF;
    @FXML 
    private TextField valeurPreciseSimulationTF;
    
    @FXML
    private Button validerSaisieBTN;
    @FXML
    private Button simulerBTN;
    @FXML
    private Button sauvegarderBTN;
    @FXML
    private Button quitterBTN;
    
    @FXML
    private Label erreurMessageLabel;
    
    @FXML
    private VBox resultatAffichageVBox;
    
    /** Variables pour la loi */
    LoiUniforme loiU;
    double valeur[];
    
    /**
     * TODO commenter le rôle de cette méthode
     * @param event
     */
    public void cliqueValiderSaisie(ActionEvent event) {
        String valeurChaine[] = valeurTF.getText().split(",");
        
        valeur = new double[valeurChaine.length];

        try {
            /* Conversion des valeurs en nombres */
            for(int i = 0; i < valeur.length; i++) {
                valeur[i] = (double) Double.parseDouble(valeurChaine[i]);
            }
            
            /* Simulation de la loi discrète */
            loiU = new LoiUniforme(valeur);
            
            probabiliteLabel.setText(loiU.getP() +"");
            
            nbSimulationTF.setDisable(false);
            valeurPreciseSimulationTF.setDisable(false);
            simulerBTN.setDisable(false);
            sauvegarderBTN.setDisable(false);
            resultatAffichageVBox.setDisable(false);
            
        } catch (NumberFormatException e) {
            erreurMessageLabel.setText("Les valeurs saisies doivent être des nombres");
        } catch (IllegalArgumentException e)  {
            erreurMessageLabel.setText(e.getMessage());
        } 
    }
    
    
    public void cliqueSimuler(ActionEvent event) {
        double[] resulatAffichage;
        int resultatAfficheValeurSaisie;
        
        try {
            if(valeurPreciseSimulationTF.getText().trim().equals("")) {
                resulatAffichage = loiU.simuler(Integer.parseInt(nbSimulationTF.getText()));
                /* Affichage des résultats sur la scrollPane */
                for(int i = 0; i < resulatAffichage.length; i++) {
                    Label aAfficherLabel = new Label();
                    aAfficherLabel.setText(valeur[i] + " ---> "  + resulatAffichage[i]);
                    
                    resultatAffichageVBox.getChildren().add(aAfficherLabel);
                }
            } else {
                resultatAfficheValeurSaisie = loiU.simuler(Integer.parseInt(nbSimulationTF.getText()), 
                             Double.parseDouble(valeurPreciseSimulationTF.getText()));
                Label aAfficherLabel = new Label();
                aAfficherLabel.setText(Integer.parseInt(valeurPreciseSimulationTF.getText())  + " ---> "  + resultatAfficheValeurSaisie);
                
                resultatAffichageVBox.getChildren().add(aAfficherLabel);
            }
            
            /* Saut de ligne entre chaque simulation*/
            Label aAfficherLabel = new Label();
            aAfficherLabel.setText("\n");
            resultatAffichageVBox.getChildren().add(aAfficherLabel);
            
        } catch (IllegalArgumentException e)  {
            erreurMessageLabel.setText(e.getMessage());
        }   
    }
    
    public void cliqueSauvegarder(ActionEvent event) {
        loiU.sauvegarder("saveLoiUniformeIHM.csv");
    }


    public void cliqueQuitter(ActionEvent event){
        Stage stage = (Stage) quitterBTN.getScene().getWindow();
        stage.close();
    }
    
    @Override
    public void start(Stage arg0) throws Exception {
        // TODO Auto-generated method stub
        
    }

}
