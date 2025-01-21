class Game {
    private int score = 0;
    private final int gameID;
    private final String gameName;

    public Game(final int gameID, final String gameName) {
        this.gameID = gameID;
        this.gameName = gameName;
    }

    public void incrementScore() {
        score++;
    }
}