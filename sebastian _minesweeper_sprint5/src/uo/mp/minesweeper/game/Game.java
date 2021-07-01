package uo.mp.minesweeper.game;

import uo.mp.minesweeper.util.ArgumentChecks;
import uo.mp.minesweeper.util.Console;

/**
 * MineSweeper sprint5 - Have in mind that when the user enters a movement, the
 * first integer is coord X and the second one is the coord Y. Thus a movement
 * is made by coords (X,Y).
 * 
 * @author UO277069 - Sebastián López Hernández
 * @version sprint3
 *
 */
public class Game {
    private Board board;
    private GameInteractor interactor;
    private GameMove gm;
    private long elapsedTime;
    private boolean userHasWin;

    /**
     * Constructor for objects of the Game class
     * 
     * @param Board board
     */
    public Game(Board board) {
	ArgumentChecks.isTrue(board != null, "Board cannot be null");
	this.board = board;
    }

    /**
     * It is responsible for initializations (user messages, timer, etc.), for
     * deciding whether game is finished or not as well as for managing the main
     * loop. In this loop, user is asked to type an action to do and this action
     * (reveal and (un)flag) is performed over the corresponding tile. When a losing
     * condition (user hits a bomb) or a winning condition (every bomb has been
     * flagged) is detected, game stops, board is revealed and displayed completely
     * and a win or lose message is printed.
     */
    public void play() {
	run();
    }
    /**
     * It sets interactor as the interface to be used to communicate with the player
     * @param interactor
     */
    public void setInteractor(GameInteractor interactor) {
	this.interactor = interactor;
    }
    /**
     * Returns the value of time field.
     * @return long time
     */
    public long getTime() {
	return elapsedTime;
    }
    
    /**
     * Runs the game!
     */
    private void run() {
	int numOfMines = board.getFlagsLeft();
	this.board.uncoverWelcomeArea();
	this.interactor.showReadyToStart();
	long start = System.currentTimeMillis();
	long after = System.currentTimeMillis();
	this.elapsedTime = after - start;
	do {
	    this.interactor.showGame(elapsedTime, this.board);
	    askMovement();
	    doMovement();
	    after = System.currentTimeMillis();
	    elapsedTime = (after - start) / 1000;
	} while (!this.board.isExploded() && !userHasWin(numOfMines));
	this.board.unveil();
	showBoomIfExploded();
	this.interactor.showGame(elapsedTime, this.board);
	finishGame();
    }

    
    private void askMovement() {
	gm = this.interactor.askMove(board.rows(), board.cols());
	char movement = gm.getOperation();
	int column = gm.getColumn();
	int row = gm.getRow();
	checkMovement(movement);
	checkRowAndCol(column, row);
    }

    private void checkMovement(char movement) {
	switch (movement) {
	case 's':
	    break;
	case 'f':
	    break;
	case 'u':
	    break;
	default:
	    askMovementBecauseOfError("Invalid movement!!");
	}

    }

    private void checkRowAndCol(int column, int row) {
	if (column < 0 || column >= this.board.getStatus()[0].length)
	    askMovementBecauseOfError("Column cannot be less than 0 nor greater than board.length");
	if (row < 0 || row >= this.board.getSquaresForTest().length)
	    askMovementBecauseOfError("Row cannot be less than 0 nor greater than board.length");
    }

    private boolean userHasWin(int numOfMines) {
	userHasWin = this.board.getMinesLeft() == 0 || areAllTilesWithoutMinesRevealed(numOfMines);
	return userHasWin;
    }

    private boolean areAllTilesWithoutMinesRevealed(int numOfMines) {
	char[][] currentBoard = this.board.getStatus();
	int openTilesWithoutMine = 0;
	int tilesFlagged = 0;
	int boardSize = currentBoard[0].length * currentBoard.length;
	if (!this.board.isExploded()) {
	    for (int i = 0; i < currentBoard.length; i++) {
		for (int j = 0; j < currentBoard[i].length; j++) {
		    if (currentBoard[i][j] != '#')
			openTilesWithoutMine++;
		    if (currentBoard[i][j] == '¶')
			tilesFlagged++;
		}
	    }
	    if (openTilesWithoutMine - tilesFlagged == boardSize - numOfMines)
		return true;
	}
	return false;
    }

    private void doMovement() {
	int y;
	int x;
	switch (gm.getOperation()) {
	case 's':
	    y = getRow();
	    x = getColumn();

	    this.board.stepOn(x, y);
	    break;
	case 'f':
	    y = getRow();
	    x = getColumn();

	    this.board.flag(x, y);
	    break;
	case 'u':
	    y = getRow();
	    x = getColumn();

	    this.board.unflag(x, y);
	    break;
	default:
	    throw new IllegalArgumentException("Something went wrong when doing the movement!");
	}
    }

    private int getColumn() {
	int column = gm.getColumn();
	return column;
    }

    private void askMovementBecauseOfError(String msg) {
	Console.printError(msg + " Repeat the movement!");
	askMovement();
    }

    private int getRow() {
	int row = gm.getRow();
	return row;
    }

    public boolean didUserWon() {
	return userHasWin;
    }

    private void finishGame() {
	if (!this.board.isExploded())
	    interactor.showCongratulations(elapsedTime);
	else
	    this.interactor.showGameFinished();
    }

    private void showBoomIfExploded() {
	if (this.board.isExploded())
	    interactor.showBooommm();
    }
}
