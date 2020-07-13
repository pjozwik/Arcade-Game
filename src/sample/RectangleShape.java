package sample;

/**
 * klasa dziedziczaca po klasie EnemyShape
 */
public class RectangleShape extends EnemyShape {
    private GameController gameController;

    RectangleShape(GameController gameController){
        this.gameController = gameController;
    }

    /**
     * metoda słuzaca do wyswietlenia obiektu tej klasy na ekranie
     */
    public void draw(){
        gameController.anchorPane.prefWidthProperty().bind(MenuController.stage1.widthProperty());
        gameController.anchorPane.prefHeightProperty().bind(MenuController.stage1.heightProperty());
        width = Load.poczatkowaSzerokoscObiektuGryJakoProcentPoczatkowejSzerokosciPlanszy*gameController.anchorPane.getWidth()/100;
        height = Load.poczatkowaSzerokoscObiektuGryJakoProcentPoczatkowejSzerokosciPlanszy*gameController.anchorPane.getHeight()/100;
        double a = random.nextDouble() * (gameController.anchorPane.getWidth() - width);
        double b = random.nextDouble() * (gameController.anchorPane.getHeight() - height);
        gameController.rectangle.setX(a);
        gameController.rectangle.setY(b);
        gameController.rectangle.setHeight(height);
        gameController.rectangle.setWidth(width);
        gameController.anchorPane.getChildren().clear();
        gameController.anchorPane.getChildren().add(gameController.rectangle);

    }
}
