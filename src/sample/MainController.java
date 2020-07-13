package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Menu;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

import java.io.IOException;
import java.lang.reflect.Field;
import java.security.MessageDigest;
import java.text.MessageFormat;
import java.util.Optional;

/**
 * kontroler glownego stage wywolywanego w metodzie start klasy Main
 */
public class MainController {


    @FXML
    private StackPane glownyPane;

    /**
     * metoda wywolujaca sie przy odpaleniu programu
     */
    @FXML
    public void initialize(){
        loadMenuScreen();
    }

    /**
     * metoda umozliwiajaca zaladowanie sceny z menu gry
     */
    public void loadMenuScreen() {
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("MenuScreen.fxml"));
        Pane pane = null;

        try {
             pane = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //pane.setPrefSize(Load.poczatkowaSzerokoscPlanszy,Load.poczatkowaWysokoscPlanszy);
        glownyPane.setStyle("-fx-background-color: rgb("+Load.kolor[0]+","+Load.kolor[1]+","+Load.kolor[2]+");");
        //pane.setStyle("-fx-background-color: rgb("+Load.kolor[0]+","+Load.kolor[1]+","+Load.kolor[2]+");");
        MenuController menuController = loader.getController();
        menuController.setMainController(this);
        setScreen(pane);
    }

    /**
     * metoda ustawiajaca scene na glownym stage
     * @param pane - kontener ktory zostanie nalozony na glownego stage
     */
    public void setScreen(Pane pane) {

        glownyPane.getChildren().clear();
        glownyPane.getChildren().add(pane);

    }


}
