package model;

public class Traveler {
    private String name;
    private Game game;

    public Traveler(String name) {

        this.name = name;
        this.game = new Game();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public Game getGame() {
        return game;
    }
}
