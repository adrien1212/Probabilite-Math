/*
 * LoiDiscreteControler.java                                                  13 déc. 2019
 * IUT info1 2018-2019 groupe 1, aucun droits : ni copyright ni copyleft 
 */
package lancementFXMLV2.loiExponentielle;


import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import probabiliteV2.LoiExponentielle;
import probabiliteV2.LoiUniforme;

/**
 * TODO commenter les responsabilités de cette classe
 * @author MrAdrienBis
 *
 */
public class LoiExponentielleSimulationControler extends Application {

    @FXML
    private TextField lambdaTF;
    @FXML
    private TextField nbSimulationTF;
    
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
    LoiExponentielle loiE;
    String lambda;
    
    /**
     * TODO commenter le rôle de cette méthode
     * @param event
     */
    public void cliqueValiderSaisie(ActionEvent event) {
        lambda = lambdaTF.getText();

        try {
            /* Simulation de la loi discrète */
            loiE = new LoiExponentielle(Double.parseDouble(lambda));
            
            nbSimulationTF.setDisable(false);
            simulerBTN.setDisable(false);
            sauvegarderBTN.setDisable(false);
            resultatAffichageVBox.setDisable(false);
            
        } catch (NumberFormatException e) {
            erreurMessageLabel.setText("Le paramètre doit être un nombre");
        } catch (IllegalArgumentException e)  {
            erreurMessageLabel.setText(e.getMessage());
        } 
    }
    
    
    public void cliqueSimuler(ActionEvent event) {
        double[] resulatAffichage;
        
        try {

            resulatAffichage = loiE.simuler(Integer.parseInt(nbSimulationTF.getText()));
            /* Affichage des résultats sur la scrollPane */
            for(int i = 0; i < resulatAffichage.length; i++) {
                Label aAfficherLabel = new Label();
                aAfficherLabel.setText(lambda + " ---> "  + resulatAffichage[i]);

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
        loiE.sauvegarder("saveLoiExponentielleIHM.csv");
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
