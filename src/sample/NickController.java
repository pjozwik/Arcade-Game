package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.ImageCursor;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * kontroler oblsugujacy okienko z nickiem gracza
 */
public class NickController {
    private  MainController mainController;
    private GameController gameController;
    public static Stage nickStage;
    public static String nick;
    public static Boolean ifAccepted = false;
    @FXML
    private Pane pane;
    @FXML
    private Label nickLabel;
    @FXML
    private Button ok;

    @FXML Button cancel;

    @FXML
    private TextField textField;


    public void initialize(){
        ok.getStylesheets().add("stylesheet.css");
        cancel.getStylesheets().add("stylesheet.css");
       if(Load.tlo.equals("plikGraficzny")) {
           pane.getStylesheets().add("stylesheet.css");
           nickLabel.setTextFill(Color.web("#fbfafa"));
       }

    }

    /**
     * metoda wywolywana w momencie klikniecia przycisku Ok w tym oknie, powodujaca zaakceptowanie danego nicku
     * i zamkniecie okna
     */
    public void openGame()
    {
            Stage dialogStage = (Stage) ok.getScene().getWindow();
            dialogStage.close();
            nick = textField.getText();
    }

    /**
     * metoda wywolywana w momencie klikniecia przycisku ANULUJ w tym oknie, powodujaca zamkniecie okienka z nickiem
     * i plansza gry oraz powrot do menu
     */
    public void openMenu(){

        nickStage = (Stage) ok.getScene().getWindow();
        nickStage.close();
        Stage gameStage = MenuController.stage1;
        gameStage.close();
        Stage menuStage = MenuController.menuStage;
        menuStage.show();


    }

    /**
     * metoda wywolujaca sie w momencie wpisania tekstu do pola textField, powodujaca mozliwosc nacisniecia przycisku OK
     * w tym oknie
     */
    public void setVisible(){


            if(textField.getText().isEmpty()){
                ok.setDisable(true);
            }else ok.setDisable(false);
    }
}
