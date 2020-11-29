/*
 * loiExponentielleProbabiliteControler.java                                                  14 déc. 2019
 * IUT info1 2018-2019 groupe 1, aucun droits : ni copyright ni copyleft 
 */
package lancementFXMLV2.loiExponentielle;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import probabiliteV2.LoiExponentielle;

/**
 * TODO commenter les responsabilités de cette classe
 * @author MrAdrienBis
 *
 */
public class loiExponentielleProbabiliteControler extends Application {

    @FXML
    private TextField lambdaTF;
    @FXML
    private TextField borneInfTF;
    @FXML 
    private TextField borneSupTF;
    @FXML
    private TextField probabiliteCumuleeTF;

    @FXML
    private Button quitterBTN;
    @FXML
    private Button validerSaisieBTN;
    @FXML
    private Button recupererBTN;

    @FXML 
    private CheckBox inferieurCB;
    @FXML
    private CheckBox superieurCB;

    @FXML
    private Label erreurMessageLabel;

    @FXML
    private VBox resultatVBox;

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

            probabiliteCumuleeTF.setDisable(false);
            borneInfTF.setDisable(false);
            borneSupTF.setDisable(false);
            recupererBTN.setDisable(false);
            inferieurCB.setDisable(false);
            superieurCB.setDisable(false);
            resultatVBox.setDisable(false);

        } catch (NumberFormatException e) {
            erreurMessageLabel.setText("Le paramètre doit être un nombre");
        } catch (IllegalArgumentException e)  {
            erreurMessageLabel.setText(e.getMessage());
        } 
    }

    public void cliqueRecuperer(ActionEvent event) {
        double valeurBorneInf;
        double valeurBorneSup;
        
        double valeuraRecuperer;

        try {
            if(probabiliteCumuleeTF.getText().trim().equals("") 
                    && borneInfTF.getText().trim().equals("")
                    && borneSupTF.getText().trim().equals("")) {
                erreurMessageLabel.setText("Veuillez renseigné un champ");

            } else if(probabiliteCumuleeTF.getText().trim().equals("")) {
                /* Alors intervale */
                if(borneInfTF.getText().trim().equals("") || borneSupTF.getText().trim().equals("")) {
                    erreurMessageLabel.setText("Un borne n'est pas renseigné");
                } else {
                    valeurBorneInf = Double.parseDouble(borneInfTF.getText());
                    valeurBorneSup = Double.parseDouble(borneSupTF.getText());
                    
                    Label aAfficherLabel = new Label();
                    aAfficherLabel.setText(loiE.getProbabiteIntervalle(valeurBorneInf, valeurBorneSup) +"");
                    resultatVBox.getChildren().add(aAfficherLabel); 
                }
            } else if(borneInfTF.getText().trim().equals("") && borneSupTF.getText().trim().equals("")) {
                /* Alors proba cumulee */         
                Label aAfficherLabel = new Label();
                aAfficherLabel.setText(loiE.getProbabiliteCumulee(Double.parseDouble(probabiliteCumuleeTF.getText()), 
                                                                  inferieurCB.isSelected()) 
                                                                  +"");
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

    @Override
    public void start(Stage arg0) throws Exception {
        // TODO Auto-generated method stub

    }
}
