package uo.mp.minesweeper.game;
/**
 * Indicates the Level of the game. It can be HIGH, MEDIUM or EASY.
 * They have pre-established valus for the board constructor.
 * @author sebas
 *
 */
public enum GameLevel {
    HIGH(30, 16, 20), MEDIUM(16, 16, 15), EASY(9, 9, 12);

    private int width;
    private int height;
    private int percentageBombs;
    
    private GameLevel(int width, int height, int percentageBombs) {
	this.width = width;
	this.height = height;
	this.percentageBombs = percentageBombs;
    }

    public int getWidth() {
	return width;
    }

    public int getHeight() {
	return height;
    }

    public int getPercentageBombs() {
	return percentageBombs;
    }
}
