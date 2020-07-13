package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.SplitPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * kontroler obslugujacy menu programu
 */
public class MenuController {

    private MainController mainController;
    public static Stage menuStage;
    public static Stage stage1;

    @FXML
    private Pane menuPane;
    @FXML
    private Button otworzGre;
    @FXML
    private Button otworzUstawienia;
    @FXML
    private Button otworzWyniki;
    @FXML
    private Button wyjscie;
    @FXML
    private ImageView image;

    public void initialize(){
        otworzGre.getStylesheets().add("stylesheet.css");
        otworzUstawienia.getStylesheets().add("stylesheet.css");
        otworzWyniki.getStylesheets().add("stylesheet.css");
        wyjscie.getStylesheets().add("stylesheet.css");
        Image image1 = new Image("nazwa2.png");
        image.setImage(image1);
    }

    /**
     * metoda wywolujaca sie w momencie klikniecia przycisku GRA na scenie z menu programu, powoduje otwarcie okienka proszącego
     * o wpisanie nicku oraz okienko z plansza gry, zamyka ona rowniez okno z menu programu
     */
    @FXML
    public void OtworzGre(){
        GameController.level = 0;
        menuStage = (Stage) otworzGre.getScene().getWindow();
        menuStage.close();

        FXMLLoader loade = new FXMLLoader(this.getClass().getResource("/sample/GameScreen.fxml"));
        SplitPane pane1 = null;
        try {
            pane1 = loade.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Scene scene1 = new Scene(pane1,Load.poczatkowaSzerokoscPlanszy,Load.poczatkowaWysokoscPlanszy);
        stage1 = new Stage();
        stage1.setScene(scene1);
        stage1.setTitle(Load.nazwaGry);
        stage1.setResizable(true);
        pane1.setStyle("-fx-background-color: rgb("+Load.kolor[0]+","+Load.kolor[1]+","+Load.kolor[2]+");");
        stage1.setOnCloseRequest(e -> event());
        stage1.show();


        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/sample/NickScreen.fxml"));
        Pane pane = null;
        try {
            pane = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Scene scene = new Scene(pane,433,151);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle(Load.nazwaGry);
        stage.setResizable(true);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setOnCloseRequest(e -> event());
        pane.setStyle("-fx-background-color: rgb("+Load.kolor[0]+","+Load.kolor[1]+","+Load.kolor[2]+");");
        stage.show();

    }

    /**
     * metoda wywolujaca sie w momencie naklikniecia na przycisk USTAWIENIA w menu gry, powoduje nalozenie sceny z
     * ustawieniami gry
     */
    @FXML
    public void OtworzUstawienia(){


        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/sample/SettingsScreen.fxml"));
        Pane pane = null;
        try {
            pane = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        SettingsController settingsController = loader.getController();
        settingsController.setMainController(mainController);

        mainController.setScreen(pane);
    }

    /**
     * metoda wywolujaca sie w momencie naklikniecia na przycisk WYNIKI w menu gry, powoduje nalozenie sceny z
     * najlepszymi wynikami gry
     */
    @FXML
    public void OtworzWyniki(){
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/sample/ScoreScreen.fxml"));
        AnchorPane pane = null;
        try {
            pane = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        ScoreController scoreController = loader.getController();
        scoreController.setMainController(mainController);

        mainController.setScreen(pane);
    }

    /**
     * metoda wywolujaca sie w momencie klikniecia przycisku WYJSCIE w menu programu, powoduje zamkniecie gry
     */
    @FXML
    public void Wyjscie(){
            System.exit(0);
    }

    /**
     * metoda sluzaca do przeslania glownego kontrolera
     * @param mainController
     */
    public void setMainController(MainController mainController){

        this.mainController = mainController;
    }

    /**
     * metoda ta jest wykorzystywana aby zmienic funkcje przycisku wyjscia w prawym gornym rogu okienka z nickiem gracza
     */
    public void event(){
        //NickController.nickStage.close();
        MenuController.stage1.close();
        MenuController.menuStage.show();
    }

}
