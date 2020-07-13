package sample;

import java.util.Random;

/**
 * klasa abstrakcyjna, jest klasa bazowa po ktorej dziedzicza obiekty do zestrzelenia
 */
public abstract class EnemyShape {

    public double height;
    public double width;
    Random random = new Random();

    /**
     * metoda abstrakcyjna inicjalizujaca wyswietlenie obiektu na ekranie
     */
    public abstract void draw();
}
