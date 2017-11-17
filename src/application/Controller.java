package application;

import javafx.animation.FadeTransition;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable  {

    @FXML
    private Label logoLabel;

    @FXML
    private Pane spinnerPane;

    @FXML
    private AnchorPane rootPane;

    @FXML
    private Label helloLabel;

    @FXML
    private Label nameLabel;

    Main main;
    Stage stage;

    public void main(Stage stage,Main main){
        this.main=main;
        this.stage=stage;
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    	//Primero movemos todos los items abajo del todo para despu�s hacer la transicion
    	//Con fromX y toX elegimos desde donde hasta donde
    	//Con setByX  el origen de coordenadas esta en la esquina superior izquierda
        TranslateTransition translateTransition =new TranslateTransition(Duration.seconds(0.3),logoLabel);
        translateTransition.setByY(700);
        translateTransition.play();

        TranslateTransition translateTransition0 =new TranslateTransition(Duration.seconds(0.5),nameLabel);
        translateTransition0.setByY(700);
        translateTransition0.play();

        TranslateTransition translateTransition00 =new TranslateTransition(Duration.seconds(0.5),helloLabel);
        translateTransition00.setByY(700);
        translateTransition00.play();

        //Para crear la transicion
        translateTransition.setOnFinished(event ->{
            TranslateTransition translateTransition1 =new TranslateTransition(Duration.seconds(1),logoLabel);
            translateTransition1.setByY(-700);
            translateTransition1.play();

            translateTransition1.setOnFinished(event1 -> {

            	//La hacemos visible y hacemos que suba
                nameLabel.setVisible(true);

                TranslateTransition translateTransition11 =new TranslateTransition(Duration.seconds(1),nameLabel);
                translateTransition11.setByY(-700);
                translateTransition11.play();

                //Cuando nameLabel este arriba, comienza a subir el siguiente
                translateTransition11.setOnFinished(event2 -> {

                    helloLabel.setVisible(true);
                    TranslateTransition translateTransition111 =new TranslateTransition(Duration.seconds(1),helloLabel);
                    translateTransition111.setByY(-700);
                    translateTransition111.play();

                    //Cuando termine de subir el anterior, comienza a subir el siguiente
                    translateTransition111.setOnFinished(event3 -> {
                        spinnerPane.setVisible(true);

                        FadeTransition fadeTransition=new FadeTransition(Duration.seconds(1),spinnerPane);
                        fadeTransition.setFromValue(0);
                        fadeTransition.setToValue(1);
                        fadeTransition.play();

                        fadeTransition.setOnFinished(event4 -> {
                        	//FadeTransition es para que en Pane aparezca suavemente, valores entre 0 y 1
                            FadeTransition fadeTransition1=new FadeTransition(Duration.seconds(4),rootPane);
                            fadeTransition1.setFromValue(1);
                            fadeTransition1.setToValue(0.1);
                            fadeTransition1.play();

                            fadeTransition1.setOnFinished(event5 -> {
                                //main.closeStage();
                            	main.stage.close();
                                System.out.println("------- splash screen is closed --------");
                            });
                        });
                    });
                });
            });
        });
    }
}