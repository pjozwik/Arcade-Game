package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Properties;

//import static jdk.nashorn.internal.runtime.regexp.joni.Syntax.Java;

/**
 * klasa glowna programu dziedziczaca po klasie Application
 */
public class Main extends Application {

    /**
     * funkcja ustawiajaca głownego stage na sample, ustawia jego parametry oraz nakłada na niego scene
     * @param primaryStage
     * @throws Exception
     */
    //@Override
    public void start(Stage primaryStage) throws Exception {

        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("sample.fxml"));
        Pane pane = loader.load();
        Scene scene = new Scene(pane,400,400);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle(Load.nazwaGry);
        stage.setResizable(true);
        stage.show();

    }

    /**
     * głowna funkcja programu
     * @param args
     */
    public static void main(String[] args) throws Exception{
        try {
            String ifconnected;
            Socket sr = new Socket("localhost", 6777);
            OutputStream os = sr.getOutputStream();
            PrintWriter pw = new PrintWriter(os, true);
            pw.println("Login");

            InputStream is = sr.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            ifconnected = br.readLine();
            if (ifconnected.equals("Loggedin")) {
                Load.connection = "connected";
                System.out.println("Grasz online");
            }else System.out.println("Grasz offline");
        }catch (UnknownHostException h){
            System.out.println("Błąd podczas łączenia z hostem");
        }catch (IOException e){
            System.out.println("Grasz offline");
        }

        Load.wczytajZPliku();
        Load.wczytajZPliku2();
        Load.wczytajZPlikuZLevelami();
        //Load.wczytajWyniki();
        launch(args);
    }


}
