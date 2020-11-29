/*
 * loiExponentielleProbabiliteControler.java                                                  14 d�c. 2019
 * IUT info1 2018-2019 groupe 1, aucun droits : ni copyright ni copyleft 
 */
package lancementFXMLV2.loiNormale;

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
import probabiliteV2.LoiNormale;

/**
 * TODO commenter les responsabilit�s de cette classe
 * @author MrAdrienBis
 *
 */
public class LoiNormaleProbabiliteControler extends Application {

    @FXML
    private TextField esperanceTF;
    @FXML
    private TextField varianceTF;
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
    LoiNormale loiN;
    String esperance;
    String ecartType;

    /**
     * TODO commenter le r�le de cette m�thode
     * @param event
     */
    public void cliqueValiderSaisie(ActionEvent event) {
        esperance = esperanceTF.getText();
        ecartType = varianceTF.getText();

        try {
            /* Simulation de la loi discr�te */
            loiN = new LoiNormale(Double.parseDouble(esperance), Double.parseDouble(ecartType));

            probabiliteCumuleeTF.setDisable(false);
            borneInfTF.setDisable(false);
            borneSupTF.setDisable(false);
            recupererBTN.setDisable(false);
            inferieurCB.setDisable(false);
            superieurCB.setDisable(false);
            resultatVBox.setDisable(false);

        } catch (NumberFormatException e) {
            erreurMessageLabel.setText("Le param�tre doit �tre un nombre");
        } catch (IllegalArgumentException e)  {
            erreurMessageLabel.setText(e.getMessage());
        } 
    }

    public void cliqueRecuperer(ActionEvent event) {
        double valeurBorneInf;
        double valeurBorneSup;
        
        double valeuraRecuperer;

        erreurMessageLabel.setText("");
        try {
            if(probabiliteCumuleeTF.getText().trim().equals("") 
                    && borneInfTF.getText().trim().equals("")
                    && borneSupTF.getText().trim().equals("")) {
                erreurMessageLabel.setText("Veuillez renseign� un champ");

            } else if(probabiliteCumuleeTF.getText().trim().equals("")) {
                /* Alors intervale */
                if(borneInfTF.getText().trim().equals("") || borneSupTF.getText().trim().equals("")) {
                    erreurMessageLabel.setText("Un borne n'est pas renseign�");
                } else {
                    valeurBorneInf = Double.parseDouble(borneInfTF.getText());
                    valeurBorneSup = Double.parseDouble(borneSupTF.getText());
                    
                    Label aAfficherLabel = new Label();
                    aAfficherLabel.setText(loiN.getProbabiteIntervalle(valeurBorneInf, valeurBorneSup) +"");
                    resultatVBox.getChildren().add(aAfficherLabel); 
                }
            } else if(borneInfTF.getText().trim().equals("") && borneSupTF.getText().trim().equals("")) {
                /* Alors proba cumulee */
                if(!inferieurCB.isSelected() && !superieurCB.isSelected()) {
                    erreurMessageLabel.setText("Veuillez cocher inf�rieur ou sup�rieur");
                } else {
                    
                    Label aAfficherLabel = new Label();
                    aAfficherLabel.setText(loiN.getProbabiliteCumulee(Double.parseDouble(probabiliteCumuleeTF.getText()), 
                                                                      inferieurCB.isSelected()) 
                                                                      +"");
                    resultatVBox.getChildren().add(aAfficherLabel); 
                }
               
            } else {
                erreurMessageLabel.setText("Un seul champ � remplir");
            }
        } catch (NumberFormatException e) {
            erreurMessageLabel.setText("Les valeurs saisies doivent �tre des nombres");
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
