/*
 * LoiDiscreteControler.java                                                  13 déc. 2019
 * IUT info1 2018-2019 groupe 1, aucun droits : ni copyright ni copyleft 
 */
package lancementFXMLV2.loiBinomiale;


import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import probabiliteV2.LoiBinomiale;
import probabiliteV2.LoiNormale;
import probabiliteV2.LoiProbabilite;

/**
 * TODO commenter les responsabilités de cette classe
 * @author MrAdrienBis
 *
 */
public class LoiBinomialeSimulationControler extends Application {

    @FXML
    private TextField nbSuccesTF;
    @FXML  
    private TextField probabiliteSuccesTF;
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
    
    /* Suivant n on utiliser la loi binomiale ou la loi normale */
    LoiBinomiale loiB;
    
    /**
     * TODO commenter le rôle de cette méthode
     * @param event
     */
    public void cliqueValiderSaisie(ActionEvent event) {
        try {
            double n = Integer.parseInt(nbSuccesTF.getText());
            double p = Double.parseDouble(probabiliteSuccesTF.getText());
            
            /* Simulation de la loi discrète */
            loiB = new LoiBinomiale(Integer.parseInt(nbSuccesTF.getText()),
                                    Double.parseDouble(probabiliteSuccesTF.getText()));
            
            
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
                resulatAffichage = loiB.simuler(Integer.parseInt(nbSimulationTF.getText()));
                /* Affichage des résultats sur la scrollPane */
                for(int i = 0; i < resulatAffichage.length; i++) {
                    Label aAfficherLabel = new Label();
                    aAfficherLabel.setText(i + " ---> "  + resulatAffichage[i]);
                    
                    resultatAffichageVBox.getChildren().add(aAfficherLabel);
                }
            } else {
                resultatAfficheValeurSaisie = loiB.simuler(Integer.parseInt(nbSimulationTF.getText()), 
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
        loiB.sauvegarder("saveLoiBinomialeIHM.csv");
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
