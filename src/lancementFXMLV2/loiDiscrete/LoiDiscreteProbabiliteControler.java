/*
 * LoiDiscreteProbabiliteControler.java                                                  14 déc. 2019
 * IUT info1 2018-2019 groupe 1, aucun droits : ni copyright ni copyleft 
 */
package lancementFXMLV2.loiDiscrete;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import probabiliteV2.LoiDiscrete;

/**
 * TODO commenter les responsabilités de cette classe
 * @author MrAdrienBis
 *
 */
public class LoiDiscreteProbabiliteControler extends Application {

    @FXML
    private TextField valeurTF;
    @FXML  
    private TextField probabiliteTF;
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
    
    LoiDiscrete loiD;
    double[] valeur;
    double[] probabilite;
    
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
                erreurMessageLabel.setText("Veuillez renseigné au moins un champ");
            } else if(probabiliteCumuleeTF.getText().trim().equals("")) {
                /* Alors on est sur les probabilité égale */
                valeuraRecuperer = Integer.parseInt(probabiliteEgaleTF.getText());
                
                Label aAfficherLabel = new Label();
                aAfficherLabel.setText(loiD.getProbabiliteEgale(valeuraRecuperer) +"");
                resultatVBox.getChildren().add(aAfficherLabel);
                
            } else if(probabiliteEgaleTF.getText().trim().equals("")) {
                boolean inferieur = inferieurCB.isSelected();
                boolean ouEgale = ouEgaleCB.isSelected();
                
                valeuraRecuperer = Integer.parseInt(probabiliteCumuleeTF.getText());
                
                Label aAfficherLabel = new Label();
                aAfficherLabel.setText(loiD.getProbabiliteCumulee(valeuraRecuperer, inferieur, ouEgale) + "");
                resultatVBox.getChildren().add(aAfficherLabel);
    
            } else {
                erreurMessageLabel.setText("Un seul champ à remplir");
            }
        } catch(IllegalArgumentException e) {
            erreurMessageLabel.setText(e.getMessage());
        }
    }
    
    public void cliqueQuitter(ActionEvent event){
        Stage stage = (Stage) quitterBTN.getScene().getWindow();
        stage.close();
    }
}
