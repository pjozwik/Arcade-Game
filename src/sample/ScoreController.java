package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;

import java.io.*;
import java.net.Socket;
import java.net.URL;
import java.util.Collections;
import java.util.ResourceBundle;

import static sample.NickController.nick;

/**
 * kontroler obslugujacy scene z najlepszymi wynikami
 */
public class ScoreController  {

    private MainController mainController;

    @FXML
    private Pane pane;
    @FXML
    private ListView<String> tabel;
    @FXML
    private Button ok;

    public void initialize() throws Exception{

        Load.wczytajWyniki();
        addToTabel();
        ok.getStylesheets().add("stylesheet.css");
    }

    /**
     * metoda sluzaca do przeslania glownego kontrolera
     * @param mainController
     */
    public void setMainController(MainController mainController){

        this.mainController =mainController;
    }

    /**
     * funkcja wyswietlajaca w tabeli nie wiecej niz 5 najlepszych wynikow
     */
    public void addToTabel(){
        ObservableList<String> record = FXCollections.observableArrayList();
        Collections.sort(Load.scores);
        if(Load.scores.size() < 5) {
            for (int i = 0; i < Load.scores.size(); i++) {
                record.add(Load.scores.get(i).nick + "    " + Load.scores.get(i).score + " pkt    " + Load.scores.get(i).difficultyLvl + " Stopien trudnosci");
            }
            tabel.setItems(record);
        }else {
            for (int i = 0; i < 5; i++) {
                record.add(Load.scores.get(i).nick + "    " + Load.scores.get(i).score + " pkt    " + Load.scores.get(i).difficultyLvl + " Stopien trudnosci");
            }
            tabel.setItems(record);
        }
    }

    /**
     * funkcja powodujaca powrot do menu
     */
    public void backToMenu(){
        mainController.loadMenuScreen();
        Load.scores.clear();
    }

}
