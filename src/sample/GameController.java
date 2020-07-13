package sample;
import java.io.*;
import java.net.Socket;
import java.util.*;
import java.util.AbstractMap.SimpleEntry;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.ImageCursor;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;
import sample.MainController;
import sample.MenuController;

/**
 * kontroler obslugujacy plansze z gra
 */
public class GameController {
    final private String lsep = System.getProperty("line.separator");
    private SettingsController settingsController;


    @FXML
    private SplitPane splitPane;

    @FXML
    public AnchorPane anchorPane;
    @FXML
    public AnchorPane infoAnchor;

    @FXML
    private Label pointLabel;

    @FXML
    private Label timeLabel;
    @FXML
    private Label scoreLabel;
    @FXML
    private Button startGame;
    @FXML
    private Button exitGame;
    @FXML
    private Label infoLabel;
    @FXML
    public Label totalScoreLabel;

    public Circle circle = new Circle();
    public Circle circle2 = new Circle();
    public Rectangle rectangle = new Rectangle();
    public Polygon triangle = new Polygon();
    Timeline timeLine = new Timeline();
    Timeline timeLine2 = new Timeline();
    private Integer score = 0;
    private Integer totalScore = 0;
    private Integer seconds;
    public Boolean pause = false;
    public int[] time3 = new int[Load.liczbaStopniTrudnosci];
    public float[] speed = new float[Load.liczbaStopniTrudnosci];
    public static float speed1;
    public static Integer level = 0;
    public Boolean ifEnd = false;
    public static String clientRequest = null;
    public static double resize = 1;
    public static double resize1 = 1;
    public static Integer a;
    public int killStrike = 1;

    /**
     * metoda uruchamiająca się zaraz po otworzeniu okna z planszą gry
     */
    public void initialize() {
        startGame.getStylesheets().add("stylesheet.css");
        exitGame.getStylesheets().add("stylesheet.css");
        pointLabel.setText("Punkty do zdobycia: " + score + "/ " + Load.levels2[0]);

        if(Load.tlo.equals("plikGraficzny")) {
            splitPane.getStylesheets().add("stylesheet.css");
            pointLabel.setTextFill(Color.web("#fbfafa"));
            timeLabel.setTextFill(Color.web("#fbfafa"));
            scoreLabel.setTextFill(Color.web("#fbfafa"));
            infoLabel.setTextFill(Color.web("#fbfafa"));
            totalScoreLabel.setTextFill(Color.web("#fbfafa"));

        }

        //startGame.setLayoutX(Load.poczatkowaSzerokoscPlanszy/2 - 70);
        //startGame.setLayoutY(Load.poczatkowaWysokoscPlanszy/2);
        setDifficultyLvl();
        setCrosshair();
        reSize();
    }

    /**
     * metoda powodujaca zamkniecie okna z plansza gry oraz pokazanie okna zawierajacego menu programu
     */
    @FXML
    public void powrotDoMenu() throws Exception {
        MenuController.stage1.close();
        MenuController.menuStage.show();
        timeLine.stop();
        time3 = null;

        if(Server.sr!=null) {
            Socket sr = new Socket("localhost", 6777);
            OutputStream os = sr.getOutputStream();
            PrintWriter pw = new PrintWriter(os, true);
            pw.println("Update score file");
            pw.println(NickController.nick + " " + totalScore + " " + SettingsController.difficultyLvl);
        }else{
            saveScoreToFile("wyniki.txt");
        }
    }


    /**
     * metoda słuzaca do odpalenia gry
     */
    public void play() {
        pause = false;
        anchorPane.getChildren().clear();
        setDifficultyLvl();
        timer();
        if (!level.equals(Load.liczbaPoziomow)) {
            pointLabel.setText("Punkty do zdobycia: " + score + "/ " + Load.levels2[level]);
            timeLine = new Timeline(new KeyFrame(Duration.seconds(speed1), actionEvent -> {
                selectEnemy();
                if (score.equals(Load.levels2[level]) || score > Load.levels2[level]) {
                    timeLine.stop();
                    timeLine2.stop();
                    anchorPane.getChildren().clear();
                    pause = true;
                    infoLabel.setText("Gratulacje!!!");
                    startGame.setText("Nastepny poziom");
                    anchorPane.getChildren().addAll(startGame, infoLabel);
                    level = level + 1;
                    //totalScore = score;
                    score = 0;
                } else if (seconds.equals(0)) {
                    anchorPane.getChildren().clear();
                    pause = true;
                    timeLine.stop();
                    timeLine2.stop();
                    startGame.setVisible(true);
                    infoLabel.setText("Powtorz lvl");
                    anchorPane.getChildren().addAll(startGame, infoLabel);
                    totalScore = totalScore - score;
                    score = 0;
                }
                if(score< Load.levels2[level] - 3) {
                    if(seconds>3) {
                        Random rand = new Random();
                        a = rand.nextInt(10);
                        if (a.equals(2)) {
                            Bonus bonus = new Bonus(this, 1);
                            bonus.chooseBonus();
                        } else if (a.equals(1)) {
                            Bonus bonus = new Bonus(this, 2);
                            bonus.chooseBonus();
                        }
                    }
                }
                System.out.println(speed1);
            }));
            timeLine.setCycleCount(Animation.INDEFINITE);
            timeLine.play();
        }
        if (level.equals(Load.liczbaPoziomow)) {
            anchorPane.getChildren().clear();
            infoLabel.setText("Koniec gry");
            timeLine.stop();
            timeLine2.stop();
            exitGame.setVisible(true);
            exitGame.setDisable(false);
            startGame.setDisable(false);
            scoreLabel.setVisible(true);
            scoreLabel.setDisable(false);
            scoreLabel.setText("Ukonczyles gre z wynikiem: " + totalScore);
            anchorPane.getChildren().addAll(exitGame, infoLabel, scoreLabel);
        }
    }

    /**
     * metoda odpowiedzialna za dodanie punktu jezeli obiekt zostanie zestrzelony lub odjecia jezeli nie zostanie trafiony
     */
    public void remove() {
        if (!level.equals(Load.liczbaPoziomow)) {
            anchorPane.setOnMouseClicked(e -> {
                if (e.getButton() == MouseButton.PRIMARY) {
                    anchorPane.getChildren().remove(e.getTarget());
                    if (e.getTarget().equals(rectangle)) {
                        score++;
                        pointLabel.setText("Punkty do zdobycia: " + score + " / " + Load.levels2[level]);
                    } else if (e.getTarget().equals(circle)) {
                        score++;
                        totalScore++;
                        pointLabel.setText("Punkty do zdobycia: " + score + " / " + Load.levels2[level]);
                        totalScoreLabel.setText("Laczna ilosc punktow: " + totalScore);
                    } else if (e.getTarget().equals(triangle)) {
                        score++;
                        pointLabel.setText("Punkty do zdobycia: " + score + " / " + Load.levels2[level]);
                    } else if(e.getTarget().equals(circle2)){
                        if(a.equals(2)) seconds = seconds +5;
                        if(a.equals(1)) {
                            if(score.equals(Load.levels2[level] - 1)) {
                                score = score + 1;
                                totalScore = totalScore + 1;
                            }else{
                                score = score + 2;
                                totalScore = totalScore + 2;
                            }
                            pointLabel.setText("Punkty do zdobycia: " + score + " / " + Load.levels2[level]);
                            totalScoreLabel.setText("Laczna ilosc punktow: " + totalScore);
                        }
                    } else {
                        if (pause.equals(false)) {
                            score -= Load.levels1[level];
                            totalScore -= Load.levels1[level];
                            if (score < 0) score = 0;
                            if (totalScore < 0) totalScore = 0;
                            pointLabel.setText("Punkty do zdobycia: " + score + " / " + Load.levels2[level]);
                            totalScoreLabel.setText("Laczna ilosc punktow: " + totalScore);
                        }
                    }
                }
            });
        }
    }

    /**
     * metoda odliczajaca czas do konca rundy
     */
    public void timer() {
        timeLine2 = new Timeline(new KeyFrame(Duration.seconds(1), actionEvent -> {
            if (seconds <= 0) {
                timeLine2.stop();
            } else if (seconds > 0) {
                seconds--;
                timeLabel.setText("Pozostaly czas: " + seconds);
            }
        }));
        timeLine2.setCycleCount(Animation.INDEFINITE);
        timeLine2.play();
    }

    /**
     * metoda zatrzymujaca gre
     */
    public void pauseGame() {
        timeLine.pause();
        timeLine2.pause();
        circle.setDisable(true);
        circle2.setDisable(true);
        rectangle.setDisable(true);
        triangle.setDisable(true);
        pause = true;
    }

    /**
     * metoda wznawiajaca wstrzymana gre
     */
    public void unPauseGame() {
        timeLine.play();
        timeLine2.play();
        circle.setDisable(false);
        circle2.setDisable(false);
        rectangle.setDisable(false);
        triangle.setDisable(false);
        pause = false;
    }

    /**
     * metoda wybierajaca przeciwnika zgodnie z typem wylosowanym w pliku par.text
     */
    public void selectEnemy() {
        if (Load.figuraObiektuGry.equals("prostokaty")) {
            RectangleShape rectangleShape = new RectangleShape(this);
            rectangleShape.draw();
        } else if (Load.figuraObiektuGry.equals("kolka")) {
            CircleShape circleShape = new CircleShape(this);
            circleShape.draw();
        } else if (Load.figuraObiektuGry.equals("kwadraty")) {
            SquareShape squareShape = new SquareShape(this);
            squareShape.draw();
        } else if (Load.figuraObiektuGry.equals("trojkaty")) {
            TriangleShape trianglteShape = new TriangleShape(this);
            trianglteShape.draw();
        }
    }

    /**
     * metoda ustawiajaca wymagania danej rundy w grze
     */
    public void setDifficultyLvl() {

        time3[0] = Load.czasGry;
        speed[0] = Load.szybkosc;
        for (int i = 1; i < Load.liczbaStopniTrudnosci; i++) {
            time3[i] = time3[0] - (time3[0] * Load.zmianaStopniaTrudnosci / 100);
            time3[0] = time3[i];
            speed[i] = speed[0] - (speed[0] * Load.zmianaStopniaTrudnosci / 100);
            speed[0] = speed[i];
        }
        time3[0] = Load.czasGry;
        speed[0] = Load.szybkosc;
        for (int j = 0; j <= Load.liczbaStopniTrudnosci; j++) {
            if (SettingsController.difficultyLvl.equals(j + 1)) {
                timeLabel.setText("Pozostaly czas: " + time3[j]);
                speed1 = speed[j];
                seconds = time3[j];
            }
        }

    }

    /**
     * funkcja dopisujaca aktualny nick wynik oraz poziom trudnosci do pliku z wynikami offline
     * @param file plik gdzie zostaje dopisany wynik
     */
    public void saveScoreToFile(String file) {
        PrintWriter pw = null;
        try {
            pw = new PrintWriter(new BufferedWriter(new FileWriter(file,true)));
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        /*for(int i =0; i < Load.scores.size(); i++) {
            pw.println(Load.scores.get(i).nick + " " + Load.scores.get(i).score + " " + Load.scores.get(i).difficultyLvl);
        }*/

        pw.println(NickController.nick + " " + totalScore +" " +  SettingsController.difficultyLvl);
        pw.close();
    }

    /**
     * funkcja sluzaca do skalowania obiektow planszy gry
     */
    public void reSize() {
        resize = 1;
        resize1 =1;
        anchorPane.widthProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1) {
                if((double)number == 0.0) number = (double)t1;
                resize = resize * (double)t1 / (double)number;
                for (Node node: anchorPane.getChildren())
                      {

                        node.setScaleX(resize*resize1);
                        node.setScaleY(resize*resize1);
                }


            }
        });

        anchorPane.heightProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number1, Number t2) {
                if((double)number1 == 0.0) number1 = (double)t2;

                resize1 = resize1 * (double)t2 / (double)number1;
                for (Node node: anchorPane.getChildren())
                {

                    node.setScaleY(resize1*resize);
                    node.setScaleX(resize*resize1);
                }


            }
        });


    }

    /**
     * funkcja sluzaca do ustawienia celownika
     */
    public void setCrosshair(){
        if (SettingsController.crossHairStyle == 1 && SettingsController.color == 1) {
            Image image = new Image("celownik5.png");
            anchorPane.setCursor(new ImageCursor(image, image.getWidth() / 2, image.getHeight() / 2));
        }else if(SettingsController.crossHairStyle == 1 && SettingsController.color==2){
            Image image = new Image("celownik6.png");
            anchorPane.setCursor(new ImageCursor(image, image.getWidth() / 2, image.getHeight() / 2));
        }else if(SettingsController.crossHairStyle == 1 && SettingsController.color == 3){
            Image image = new Image("celownik2.png");
            anchorPane.setCursor(new ImageCursor(image, image.getWidth() / 2, image.getHeight() / 2));
        }else if(SettingsController.crossHairStyle == 2 && SettingsController.color == 1) {
            Image image = new Image("celownik14.png");
            anchorPane.setCursor(new ImageCursor(image, image.getWidth() / 2, image.getHeight() / 2));
        }else if(SettingsController.crossHairStyle == 2 && SettingsController.color == 2) {
            Image image = new Image("celownik15.png");
            anchorPane.setCursor(new ImageCursor(image, image.getWidth() / 2, image.getHeight() / 2));
        }else if(SettingsController.crossHairStyle == 2 && SettingsController.color == 3) {
            Image image = new Image("celownik10.png");
            anchorPane.setCursor(new ImageCursor(image, image.getWidth() / 2, image.getHeight() / 2));
        }
    }
}