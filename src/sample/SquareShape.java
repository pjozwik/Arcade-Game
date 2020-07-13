package sample;

/**
 * klasa dziedziczaca po klasie EnemyShape
 */
public class SquareShape extends EnemyShape {
    private GameController gameController;

    SquareShape(GameController gameController){
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
        double a = random.nextDouble() * (gameController.anchorPane.getWidth() - width);
        double b = random.nextDouble() * (gameController.anchorPane.getHeight() - width);
        gameController.rectangle.setX(a);
        gameController.rectangle.setY(b);
        gameController.rectangle.setHeight(width);
        gameController.rectangle.setWidth(width);
        gameController.anchorPane.getChildren().clear();
        gameController.anchorPane.getChildren().add(gameController.rectangle);
    }
}
