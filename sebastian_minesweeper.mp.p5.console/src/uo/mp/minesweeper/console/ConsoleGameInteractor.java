package uo.mp.minesweeper.console;

import uo.mp.minesweeper.game.Board;
import uo.mp.minesweeper.game.GameInteractor;
import uo.mp.minesweeper.game.GameMove;
import uo.mp.minesweeper.util.Console;

public class ConsoleGameInteractor implements GameInteractor {
    @Override
    public GameMove askMove(int rows, int cols) {
	char c = Console.readCharacter("movement (s | f | u)? ");
	int row = readInteger();
	int col = Console.readInteger("col? ");
	return new GameMove(c, row, col);
    }

    private int readInteger() {
	return Console.readInteger("row? ");
    }

    @Override
    public void showGame(long elapsedTime, Board board) {
	char[][] boardStatus = board.getStatus();
	showTime(elapsedTime);
	flagsLeft(board);
	showBoard(boardStatus);
    }

    /**
     * Displays the time on the console
     * 
     * @param int current_time
     */
    private void showTime(long elapsedTime) {
	Console.println("TIME: " + elapsedTime + " seconds");
    }

    /**
     * Displays the cols of the board
     */
    private void showBoard(char[][] boardStatus) {
	for (int i = 0; i < boardStatus.length; i++) {
	    String numCol = "";
	    String grid = "+";
	    String tile = "|";
	    for (int j = 0; j < boardStatus[i].length; j++) {
		numCol = getNumColsToPrint(numCol, j);
		grid += "---+";
		tile += " " + boardStatus[i][j] + " |";
	    }
	    if (i == 0) {
		Console.println("\t" + numCol);
		Console.println("\t" + grid);
	    }
	    Console.println(i + "\t" + tile);
	    Console.println("\t" + grid);
	}
    }

    private String getNumColsToPrint(String numCol, int j) {
	if (j < 10)
	    numCol += String.format("%1$4s", j + " ");
	else
	    numCol += String.format("%1$4s", j);
	return numCol;
    }

    @Override
    public void showGameFinished() {
	Console.println("The GAME is OVER");

    }

    @Override
    public void showCongratulations(long elapsedTime) {
	Console.println("You have won!!! You spent: " + elapsedTime + " seconds playing!");

    }

    @Override
    public void showBooommm() {
	Console.println("BOOOMMM!!!");

    }

    @Override
    public void showReadyToStart() {
	Console.println("Ready to start");

    }

    /**
     * Shows how many flags are left
     */
    private void flagsLeft(Board board) {
	Console.println("Flags left: " + board.getFlagsLeft());
    }

}
