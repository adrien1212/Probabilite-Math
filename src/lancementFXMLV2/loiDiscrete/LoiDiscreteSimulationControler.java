/*
 * LoiDiscreteControler.java                                                  13 déc. 2019
 * IUT info1 2018-2019 groupe 1, aucun droits : ni copyright ni copyleft 
 */
package lancementFXMLV2.loiDiscrete;


import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import probabiliteV2.LoiDiscrete;

/**
 * TODO commenter les responsabilités de cette classe
 * @author MrAdrienBis
 *
 */
public class LoiDiscreteSimulationControler extends Application {

    @FXML
    private TextField valeurTF;
    @FXML  
    private TextField probabiliteTF;
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
    
    LoiDiscrete loiD;
    double valeur[];
    double probabilite[];
    
    /**
     * TODO commenter le rôle de cette méthode
     * @param event
     */
    public void cliqueValiderSaisie(ActionEvent event) {
        String valeurChaine[] = valeurTF.getText().split(",");
        String probabiliteChaine[] = probabiliteTF.getText().split(",");
        
        valeur = new double[valeurChaine.length];
        probabilite = new double[probabiliteChaine.length];

        try {
            /* Conversion des valeurs en nombres */
            for(int i = 0; i < valeur.length; i++) {
                valeur[i] = (double) Double.parseDouble(valeurChaine[i]);
            }
            for(int i = 0; i < probabilite.length; i++) {
                probabilite[i] = (double) Double.parseDouble(probabiliteChaine[i]);
            }
            
            /* Simulation de la loi discrète */
            loiD = new LoiDiscrete(valeur, probabilite);
            
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
                resulatAffichage = loiD.simuler(Integer.parseInt(nbSimulationTF.getText()));
                /* Affichage des résultats sur la scrollPane */
                for(int i = 0; i < resulatAffichage.length; i++) {
                    Label aAfficherLabel = new Label();
                    aAfficherLabel.setText(valeur[i] + " ---> "  + resulatAffichage[i]);
                    
                    resultatAffichageVBox.getChildren().add(aAfficherLabel);
                }
            } else {
                resultatAfficheValeurSaisie = loiD.simuler(Integer.parseInt(nbSimulationTF.getText()), 
                             Double.parseDouble(valeurPreciseSimulationTF.getText()));
                Label aAfficherLabel = new Label();
                aAfficherLabel.setText(Integer.parseInt(nbSimulationTF.getText())  + " ---> "  + resultatAfficheValeurSaisie);
                
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
        loiD.sauvegarder("saveLoiDiscreteIHM.csv");
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
