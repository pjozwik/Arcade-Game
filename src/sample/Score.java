package sample;

/**
 * klasa sluzaca do tworzenia obiektu z aktualnymi parametrami uzytkownika
 */
public class Score implements Comparable<Score> {

    public Integer score;
    public String nick;
    public int difficultyLvl;

    Score(String nick, int score, int difficultyLvl){
        this.score = score;
        this.nick = nick;
        this.difficultyLvl = difficultyLvl;
    }

    /**
     * metoda ustawiajaca po jakiej wielkosci bedzie sortowala obiekty
     * @param sc
     * @return
     */
    public int compareTo(Score sc) {

        return sc.score.compareTo(score);
    }


}
