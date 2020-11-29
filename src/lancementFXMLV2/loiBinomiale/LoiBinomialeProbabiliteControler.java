/*
 * LoiDiscreteProbabiliteControler.java                                                  14 déc. 2019
 * IUT info1 2018-2019 groupe 1, aucun droits : ni copyright ni copyleft 
 */
package lancementFXMLV2.loiBinomiale;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import probabiliteV2.LoiBinomiale;
import probabiliteV2.LoiDiscrete;

/**
 * TODO commenter les responsabilités de cette classe
 * @author MrAdrienBis
 *
 */
public class LoiBinomialeProbabiliteControler extends Application {

    @FXML
    private TextField nbSuccesTF;
    @FXML  
    private TextField probabiliteSuccesTF;
    @FXML
    private TextField probabiliteEgaleTF;
    @FXML
    private TextField probabiliteCumuleeTF;
    
    @FXML
    private Button validerSaisieBTN;
    @FXML
    private Button quitterBTN;
    @FXML
    private Button recupererBTN;
    
    @FXML 
    private CheckBox inferieurCB;
    @FXML
    private CheckBox superieurCB;
    @FXML
    private CheckBox ouEgaleCB;
    
    @FXML
    private Label erreurMessageLabel;
    
    @FXML
    private VBox resultatVBox;
    
    LoiBinomiale loiB;
    
    public void cliqueValiderSaisie(ActionEvent event) {
        try {
            /* Simulation de la loi discrète */
            loiB = new LoiBinomiale(Integer.parseInt(nbSuccesTF.getText()),
                                    Double.parseDouble(probabiliteSuccesTF.getText()));
            
            probabiliteEgaleTF.setDisable(false);
            probabiliteCumuleeTF.setDisable(false);
            inferieurCB.setDisable(false);
            superieurCB.setDisable(false);
            ouEgaleCB.setDisable(false);
            recupererBTN.setDisable(false);
            resultatVBox.setDisable(false);
            
        } catch (NumberFormatException e) {
            erreurMessageLabel.setText("Les valeurs saisies doivent être des nombres");
        } catch (IllegalArgumentException e)  {
            erreurMessageLabel.setText(e.getMessage());
        } 
    }
    
    public void cliqueRecuperer(ActionEvent event) {
        double valeuraRecuperer;
        
        try {
            if(probabiliteEgaleTF.getText().trim().equals("") && probabiliteCumuleeTF.getText().trim().equals("")) {
                erreurMessageLabel.setText("Veuillez renseigné un champ");
            } else if(probabiliteCumuleeTF.getText().trim().equals("")) {
                /* Alors on est sur les probabilité égale */
                valeuraRecuperer = Integer.parseInt(probabiliteEgaleTF.getText());
                
                Label aAfficherLabel = new Label();
                aAfficherLabel.setText(loiB.getProbabiliteEgale(valeuraRecuperer) +"");
                resultatVBox.getChildren().add(aAfficherLabel);
                
            } else if(probabiliteEgaleTF.getText().trim().equals("")) {
                boolean inferieur = inferieurCB.isSelected();
                boolean ouEgale = ouEgaleCB.isSelected();
                
                valeuraRecuperer = Integer.parseInt(probabiliteCumuleeTF.getText());
                
                Label aAfficherLabel = new Label();
                aAfficherLabel.setText(loiB.getProbabiliteCumulee(valeuraRecuperer, inferieur, ouEgale) + "");
                resultatVBox.getChildren().add(aAfficherLabel);

            } else {
                erreurMessageLabel.setText("Un seul champ à remplir");
            }
        } catch (NumberFormatException e) {
            erreurMessageLabel.setText("Les valeurs saisies doivent être des nombres");
        }
        
    }
    
    public void cliqueQuitter(ActionEvent event){
        Stage stage = (Stage) quitterBTN.getScene().getWindow();
        stage.close();
    }
}
