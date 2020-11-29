/*
 * LancementController.java                                                  13 déc. 2019
 * IUT info1 2018-2019 groupe 1, aucun droits : ni copyright ni copyleft 
 */
package lancementFXMLV2;

import java.io.IOException;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * TODO commenter les responsabilités de cette classe
 * @author MrAdrienBis
 *
 */
public class LancementController extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {  
        Parent root = FXMLLoader.load(getClass().getResource("LancementFXML.fxml"));

        Scene scene = new Scene(root);

        primaryStage.setScene(scene);
        primaryStage.show();

    }

    /* Redirection vers les autres pages */
    public void cliqueLoiDiscrete(ActionEvent event) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("loiDiscrete/loiDiscreteSimulationFXML.fxml"));

        Parent root = fxmlLoader.load();
        Stage stage = new Stage();

        stage.initModality(Modality.APPLICATION_MODAL);

        stage.setScene(new Scene(root));
        stage.showAndWait();
    }
    public void cliqueLoiDiscreteProba(ActionEvent event) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("loiDiscrete/loiDiscreteProbabiliteFXML.fxml"));

        Parent root = fxmlLoader.load();
        Stage stage = new Stage();

        stage.initModality(Modality.APPLICATION_MODAL);

        stage.setScene(new Scene(root));
        stage.showAndWait();
    }
    
    
    public void cliqueLoiUniforme(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("loiUniforme/loiUniformeSimulationFXML.fxml"));

        Parent root = fxmlLoader.load();
        Stage stage = new Stage();

        stage.initModality(Modality.APPLICATION_MODAL);

        stage.setScene(new Scene(root));
        stage.showAndWait();
    }
    public void cliqueLoiUniformeProba(ActionEvent event) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("loiUniforme/loiUniformeProbabiliteFXML.fxml"));

        Parent root = fxmlLoader.load();
        Stage stage = new Stage();

        stage.initModality(Modality.APPLICATION_MODAL);

        stage.setScene(new Scene(root));
        stage.showAndWait();
    }

    
    public void cliqueLoiBinomiale(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("loiBinomiale/loiBinomialeSimulationFXML.fxml"));

        Parent root = fxmlLoader.load();
        Stage stage = new Stage();

        stage.initModality(Modality.APPLICATION_MODAL);

        stage.setScene(new Scene(root));
        stage.showAndWait();
    }
    public void cliqueLoiBinomialeProba(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("loiBinomiale/loiBinomialeProbabiliteFXML.fxml"));

        Parent root = fxmlLoader.load();
        Stage stage = new Stage();

        stage.initModality(Modality.APPLICATION_MODAL);

        stage.setScene(new Scene(root));
        stage.showAndWait();
    }
    

    public void cliqueLoiExponentielle(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("loiExponentielle/loiExponentielleSimulationFXML.fxml"));

        Parent root = fxmlLoader.load();
        Stage stage = new Stage();

        stage.initModality(Modality.APPLICATION_MODAL);

        stage.setScene(new Scene(root));
        stage.showAndWait();
    }
    public void cliqueLoiExponentielleProba(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("loiExponentielle/loiExponentielleProbabiliteFXML.fxml"));

        Parent root = fxmlLoader.load();
        Stage stage = new Stage();

        stage.initModality(Modality.APPLICATION_MODAL);

        stage.setScene(new Scene(root));
        stage.showAndWait();
    }
    

    public void cliqueLoiNormale(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("loiNormale/loiNormaleSimulationFXML.fxml"));

        Parent root = fxmlLoader.load();
        Stage stage = new Stage();

        stage.initModality(Modality.APPLICATION_MODAL);

        stage.setScene(new Scene(root));
        stage.showAndWait();
    }
    
    public void cliqueLoiNormaleProba(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("loiNormale/loiNormaleProbabiliteFXML.fxml"));

        Parent root = fxmlLoader.load();
        Stage stage = new Stage();

        stage.initModality(Modality.APPLICATION_MODAL);

        stage.setScene(new Scene(root));
        stage.showAndWait();
    }
    
    


    /**
     * 
     * TODO commenter le rôle de cette méthode
     * @param args
     */
    public static void main(String[] args) {
        launch(args);
    }
}
