package sample;

import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;

import java.util.Random;

/**
 * klasa sluzaca do generowania dodatkowego przeciwnika(bonusu)
 */
public class Bonus {
    private Integer bonusType;
    private GameController gameController;
    Random random = new Random();
    Bonus(GameController gameController, Integer bonusType){
        this.bonusType = bonusType;
        this.gameController = gameController;
    }

    /**
     * funkcja tworzaca obiekt w zaleznosci od typu wylosowanego bonusu
     */

    public void chooseBonus(){
        gameController.anchorPane.prefWidthProperty().bind(MenuController.stage1.widthProperty());
        gameController.anchorPane.prefHeightProperty().bind(MenuController.stage1.heightProperty());
         double width = //(Load.poczatkowaSzerokoscObiektuGryJakoProcentPoczatkowejSzerokosciPlanszy * Load.poczatkowaSzerokoscPlanszy) / 100 * ((gameController.anchorPane.getWidth()) / Load.poczatkowaSzerokoscPlanszy) * gameController.anchorPane.getHeight() / Load.poczatkowaWysokoscPlanszy;
                Load.poczatkowaSzerokoscObiektuGryJakoProcentPoczatkowejSzerokosciPlanszy*gameController.anchorPane.getWidth()/100;
        double radius = width/2;

        double a = width/2 + random.nextDouble() * (gameController.anchorPane.getWidth() - width);
        double b = width/2 +  random.nextDouble() * (gameController.anchorPane.getHeight() - width);

        gameController.circle2.setCenterX(a);
        gameController.circle2.setCenterY(b);
        gameController.circle2.setRadius(radius);

        if (bonusType.equals(1)){
            Image image = new Image("perk2.png");
            gameController.circle2.setFill(new ImagePattern(image));
        }

        if (bonusType.equals(2)){
            Image image = new Image("perk1.png");
            gameController.circle2.setFill(new ImagePattern(image));
        }

        gameController.circle2.setCenterX(a);
        gameController.circle2.setCenterY(b);
        gameController.circle2.setRadius(radius);
        gameController.anchorPane.getChildren().add(gameController.circle2);

    }


}
