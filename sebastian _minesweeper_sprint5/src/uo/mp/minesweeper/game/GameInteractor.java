package uo.mp.minesweeper.game;

public interface GameInteractor {
    /**
     * It asks the user to type an operation (s, f, u) as well as row and column.
     * Returns a GameMove object containing this information. rows and cols
     * establish the highest possible value the user can type for row and column,
     * respectively.This method has to make sure that coordinates as well as
     * character returned are valid.
     * 
     * @param int rows
     * @param int cols
     * @return GameMoves
     */
    GameMove askMove(int rows, int cols);

    /**
     * It shows the user the state of the game including board, elapsed time and
     * flags still unplaced. elapsedTime is the time spent and board is a reference
     * to the Board instance to be displayed.
     * 
     * @param elapsedTime
     * @param Board       board
     */
    void showGame(long elapsedTime, Board board);

    /**
     * It informs the player the game is over
     */
    void showGameFinished();

    /**
     * It tells the user that he wins and shows him the time spent playing
     * (parameter elapsedTime)
     * 
     * @param elapsedTime
     */
    void showCongratulations(long elapsedTime);

    /**
     * It tells the user he/she has hit a mine
     */
    void showBooommm();

    /**
     * It tells the user the game is ready to start
     */
    void showReadyToStart();
}
