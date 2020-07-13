package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Cursor;
import javafx.scene.ImageCursor;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * kontroler obsugujacy scene z ustawieniami gry
 */
public class SettingsController {

    private MainController mainController;

    @FXML
    public  RadioButton CrossRadioButton;
    @FXML
    private  RadioButton DotRadioButton;
    @FXML
    private Pane pane;
    @FXML
    private ComboBox LvlComboBox;
    @FXML
    private ComboBox ColorComboBox;
    @FXML
    private Button ok;
    @FXML
    private Button cancel;

    public static Integer difficultyLvl = 1;
    public static Integer crossHairStyle = 0;
    public static Integer color = 0;

    public void initialize(){
        crossHairStyle = 0;
        color = 0;
        ok.getStylesheets().add("stylesheet.css");
        cancel.getStylesheets().add("stylesheet.css");
        addToLvlComboBox();
        LvlComboBox.setValue("1 Stopien Trudnosci");

    }

    /**
     * metoda przesylajaca glowny kontroler
     * @param mainController - obiekt glownego kontrolera
     */
    public void setMainController(MainController mainController){

        this.mainController =mainController;
    }


    /**
     * metoda wywolujaca sie w momencie klikniecia przycisku ANULUJ, powodujaca odrzucenie zmian i powrot do glownego menu
     */
    public void backToMenu(){
        choseDifficultyLvl();
        chooseCrosshair();
        mainController.loadMenuScreen();
    }

    public void rejectChanges(){
        CrossRadioButton.setSelected(false);
        mainController.loadMenuScreen();
    }
    /**
     * metoda uzupełniająca comboBox zmiennym zczytanymi z pliku, słuzy do wyboru poziomu trudności gry
     */
    public void addToLvlComboBox(){
        for(int i = 0; i < Load.liczbaStopniTrudnosci; i++){
            LvlComboBox.getItems().add(i+1 +"  Stopien trudnosci");
        }
        LvlComboBox.setValue("1 Stopien Trudnosci");

        ColorComboBox.getItems().add("brak");
        for (int j =0; j < Load.kolor2.length; j++){
            ColorComboBox.getItems().add(Load.kolor2[j]);
        }
        ColorComboBox.setValue("brak");
    }

    /**
     * metoda pobierająca z comboBox wybrany poziom trudności
     */
    public void choseDifficultyLvl(){
        for(int i =0;i<Load.liczbaStopniTrudnosci;i++){
            if(LvlComboBox.getValue().equals((i+1)+ "  Stopien trudnosci")){
                difficultyLvl = i+1;
            }
        }
    }

    /**
     * funkcja ustawiajaca kolor oraz rodzaj celownika
     */
    public void chooseCrosshair(){
        if(CrossRadioButton.isSelected()) crossHairStyle = 1;
        else if(DotRadioButton.isSelected()) crossHairStyle = 2;

        for (int j =0; j < Load.kolor2.length; j++){
            if(ColorComboBox.getValue().equals("niebieski")) color = 1;
            if(ColorComboBox.getValue().equals("zielony")) color = 2;
            if(ColorComboBox.getValue().equals("czerwony")) color = 3;
        }
    }

    /**
     * funkcja uaktywniajaca przyciski do wyboru rodzaju celownika
     */
    public void crossHair(){
        if(ColorComboBox.getValue() != "brak"){
            CrossRadioButton.setDisable(false);
            DotRadioButton.setDisable(false);
        }else{
            CrossRadioButton.setDisable(true);
            DotRadioButton.setDisable(true);
        }
    }

}
