package model;

public class Game {
    private int countVictory;
    private int countDefeat;

    public Game() {
        this.countVictory = 0;
        this.countDefeat = 0;
    }

    public void addVictory() {
        countVictory++;
    }

    public void addDefeat() {
        countDefeat++;
    }

    public int getCountVictory() {
        return countVictory;
    }

    public int getCountDefeat() {
        return countDefeat;
    }

    public int getTotalGames() {
        return countVictory + countDefeat;
    }
}
