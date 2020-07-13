package sample;

import javafx.scene.shape.Polygon;
/**
 * klasa dziedziczaca po klasie EnemyShape
 */
public class TriangleShape extends EnemyShape {
    private GameController gameController;

    TriangleShape(GameController gameController){
        this.gameController = gameController;
    }

    @Override
    /**
     * metoda słuzaca do wyswietlenia obiektu tej klasy na ekranie
     */
    public void draw() {
        gameController.anchorPane.prefWidthProperty().bind(MenuController.stage1.widthProperty());
        gameController.anchorPane.prefHeightProperty().bind(MenuController.stage1.heightProperty());
        width = Load.poczatkowaSzerokoscObiektuGryJakoProcentPoczatkowejSzerokosciPlanszy*gameController.anchorPane.getWidth()/100;
        height = Load.poczatkowaSzerokoscObiektuGryJakoProcentPoczatkowejSzerokosciPlanszy*gameController.anchorPane.getHeight()/100;
        double a = random.nextDouble() * (gameController.anchorPane.getWidth() - width);
        double b = random.nextDouble() * (gameController.anchorPane.getHeight() - height);
        Polygon triangle = new Polygon(a,b,a+width,b,a+width/2,b - width/2);
        gameController.triangle = triangle;
        gameController.anchorPane.getChildren().clear();
        gameController.anchorPane.getChildren().add(gameController.triangle);
    }
}
