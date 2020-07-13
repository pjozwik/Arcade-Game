package sample;

import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;

import java.util.Random;

/**
 * klasa dziedziczaca po klasie EnemyShape
 */
public class CircleShape extends EnemyShape {
    private GameController gameController;
    private double radius;

    CircleShape(GameController gameController){
        this.gameController = gameController;
    }

    /**
     * metoda sluzaca do wyswietlenia obiektu tej klasy na ekranie
     */
    public void draw() {
        gameController.anchorPane.prefWidthProperty().bind(MenuController.stage1.widthProperty());
        gameController.anchorPane.prefHeightProperty().bind(MenuController.stage1.heightProperty());
        width = //(Load.poczatkowaSzerokoscObiektuGryJakoProcentPoczatkowejSzerokosciPlanszy * Load.poczatkowaSzerokoscPlanszy) / 100 * ((gameController.anchorPane.getWidth()) / Load.poczatkowaSzerokoscPlanszy) * gameController.anchorPane.getHeight() / Load.poczatkowaWysokoscPlanszy;
                 Load.poczatkowaSzerokoscObiektuGryJakoProcentPoczatkowejSzerokosciPlanszy*gameController.anchorPane.getWidth()/100;
        radius = width/2;
        if(Load.obiektyGry.equals("plikGraficzny") && Load.tlo.equals("plikGraficzny")){
            Image image = new Image("gwiazdka.png");
            gameController.circle.setFill(new ImagePattern(image));
        }else if(Load.obiektyGry.equals("plikGraficzny")){
            Image image = new Image("circle2.png");
            gameController.circle.setFill(new ImagePattern(image));
        }

        double a = width/2 + random.nextDouble() * (gameController.anchorPane.getWidth() - (width + 10));
        double b = width/2 +  random.nextDouble() * (gameController.anchorPane.getHeight() - (width+10));
        //circle = new Circle(a, b, 20);
        gameController.circle.setCenterX(a);
        gameController.circle.setCenterY(b);
        gameController.circle.setRadius(radius);
        gameController.anchorPane.getChildren().clear();
        gameController.anchorPane.getChildren().add(gameController.circle);
    }

}
