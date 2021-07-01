package uo.mp.minesweeper.game;


import java.util.Random;

import uo.mp.minesweeper.game.actions.BlowUpAction;
import uo.mp.minesweeper.game.actions.ClearAction;
import uo.mp.minesweeper.util.ArgumentChecks;
import uo.mp.minesweeper.util.collections.List;
import uo.mp.minesweeper.util.collections.impl.ArrayList;


/**
 * MineSweeper sprint1
 * Contains the board of the game.
 * @author UO277069 - Sebastián López Hernández
 * @version sprint1
 *
 */
public class Board {
    public enum State {
	PLAYABLE, BLOWN_UP
    };

    private State state;
    private Square[][] board;
    private int numOfMines;
    private int flags;

    /**
     * Constructor for objects of the Class board
     * 
     * @param int width
     * @param int height
     * @param int percentage of bombs
     */
    public Board(int width, int height, int percentage) {
	checkParameters(width, height, percentage);
	setState(State.PLAYABLE);
	board = new Square[height][width];
	fillBoard();
	int numOfMines = (int) Math.ceil(percentage * 0.01 * width * height);// rounds up the number
									//of mines to place
	setNumOfMines(numOfMines);
	setFlags(numOfMines);
	loadMines(numOfMines);
	changeVal2SqrNearMine();
	setAction2EmptyTiles();
    }

    /**
     * This constructor is only for testing purposes. First argument is the number
     * of bombs contained in the matrix of squares provided as the second parameter,
     * which is set to the inner array.
     * 
     * @param int        mines
     * @param Square[][] squares
     */
    public Board(int mines, Square[][] square) {
	ArgumentChecks.isTrue(square != null, "Square cannot be null");
	board = square;
	setNumOfMines(mines);
	setFlags(mines);
	setState(State.PLAYABLE);
    }

    /**
     * Returns true if a hidden mine was revealed; false otherwise.
     * 
     * @return boolean true if a mine was revealed.
     */
    public boolean isExploded() {
	if (state.equals(State.BLOWN_UP))
	    return true;
	return false;
    }

    /**
     * Reveals the square at X,Y coordinates, if It is hidden.
     * 
     * @param int X coord
     * @param int Y coord
     */
    public void stepOn(int x, int y) {
	ArgumentChecks.checkCoord(x, y, this.board.length, this.board[0].length,
		"X and Y cannot be less than 0 nor greater than or equal to this.board.length");
	board[y][x].stepOn();
	if (board[y][x].hasMine() && board[y][x].isOpen())
	    markAsExploded();
    }

    /**
     * If a square at x y coordinates is hidden and it has not been marked as armed
     * yet, this method marks the square at (x, y) coordinates as armed and places a
     * flag on it. If this.flags == 0, the user cannot flag a tile.
     * 
     * @param int X coord
     * @param int Y coord
     */
    public void flag(int x, int y) {
	ArgumentChecks.checkCoord(x, y, this.board.length, this.board[0].length,
		"X and Y cannot be less than 0 nor greater than" + "or equal to this.board.length");
	if (!board[y][x].hasFlag() && getFlagsLeft() > 0) { // if it has not been marked as armed..
	    board[y][x].flag();
	    setFlags(--this.flags);
	    ;
	}

    }

    /**
     * Removes flag from tile at x y coordinates, if any.
     * 
     * @param int x coord.
     * @param int y coord.
     */
    public void unflag(int x, int y) {
	ArgumentChecks.checkCoord(x, y, this.board.length, this.board[0].length,
		"X and Y cannot be less than 0 nor greater than" + "or equal to this.board.length");
	if (board[y][x].hasFlag()) { // if it has been marked as armed..
	    board[y][x].unflag();
	    setFlags(++this.flags);
	}
    }

    /**
     * Reveals every tile on the board.
     */
    public void unveil() {
	for (int y = 0; y < board.length; y++) {
	    for (int x = 0; x < board[y].length; x++) {
		board[y][x].open();
	    }
	}
    }

    /**
     * Returns how many flags still remain to be set on board. Different from
     * getlMinesLeft().
     * 
     * @return int num of flags available
     */
    public int getFlagsLeft() {
	return this.flags;
    }

    /**
     * Returns how many bombs still remain unmarked.
     * 
     * @return int num of mines not marked with a flag.
     */
    public int getMinesLeft() {
	return getNumOfMines() - getNumFlaggedMines();
    }

    /**
     * State of the game is set to blown up because some mine was hit.
     */
    public void markAsExploded() {
	setState(State.BLOWN_UP);
    }

    /**
     * Returns a 2d array of chars representing the state of the board. A character
     * at x y coordinates represents the state of x y square.
     * 
     * @return char[][] status
     */
    public char[][] getStatus() {
	char[][] boardStatus = new char[board.length][board[0].length];
	for (int y = 0; y < board.length; y++) {
	    for (int x = 0; x < board[y].length; x++) {
		boardStatus[y][x] = board[y][x].toString().charAt(0);
	    }

	}
	return boardStatus;
    }

    /**
     * Returns a reference to the inner array of Square objects. Only for test
     * purposes.
     * 
     * @return Square[][] inner array(board).
     */
    public Square[][] getSquaresForTest() {
	return board;
    }

    /**
     * Search a random empty square and runs its stepOn method to open a set of
     * adjacent squares. The square chosen to be start the game cannot be a corner
     * of the board.
     */
    public void uncoverWelcomeArea() {
	Random random = new Random();
	int x = random.nextInt(this.board[0].length);
	int y = random.nextInt(this.board.length);
	while (board[y][x].getValue() != 0) {
	    x = random.nextInt(this.board[0].length);
	    y = random.nextInt(this.board.length);
	}
	this.stepOn(x, y);
    }

    /**
     * Returns number of rows in the board
     */
    public int rows() {
	return this.board.length;
    }

    /**
     * Returns number of columns in the board
     */
    public int cols() {
	return this.board[0].length;
    }

    /**
     * Returns how many mines are flagged
     * 
     * @return int num of flagged mines
     */
    private int getNumFlaggedMines() {
	int numOfFlaggedMines = 0;
	for (Square[] squares : board) {
	    for (Square square : squares) {
		if (square.hasMine() && square.hasFlag())
		    numOfFlaggedMines++;
	    }
	}
	return numOfFlaggedMines;
    }

    /**
     * @param State the state to set
     */
    private void setState(State state) {
	ArgumentChecks.isTrue(state != null, "state cannot be null");
	this.state = state;
    }

    /**
     * Fills the board with closed Squares and value 0.
     */
    private void fillBoard() {
	for (int y = 0; y < board.length; y++) {
	    for (int x = 0; x < board[y].length; x++) {
		board[y][x] = new Square(Square.State.CLOSED, 0);
	    }
	}
    }

    /**
     * Searchs for tiles with no clues and sets their Action field to ClearAction.
     */
    private void setAction2EmptyTiles() {
	for (int i = 0; i < board.length; i++) {
	    for (int j = 0; j < board[i].length; j++) {
		if (board[i][j].getValue() == 0) {
		    board[i][j].setAction(new ClearAction(adjacentSquares(i, j)));
		}
	    }
	}
    }

    /**
     * Returns a list containing the adjacent squares without mines for a given
     * cell.
     * 
     * @param int i
     * @param int j
     * @return a List containing the adjacentSquares without mines for a given cell.
     */
    private List<Square> adjacentSquares(int i, int j) {
	List<Square> adjacentSquares = new ArrayList<Square>();

	for (int y_cord = i - 1; y_cord <= i + 1; y_cord++) {
	    for (int x_cord = j - 1; x_cord <= j + 1; x_cord++) {
		if (y_cord >= 0 && x_cord >= 0 && y_cord < board.length && x_cord < board[0].length) {
		    if (!board[y_cord][x_cord].hasMine()) {
			adjacentSquares.add(board[y_cord][x_cord]);
		    }
		}

	    }
	}
	return adjacentSquares;
    }

    /**
     * Load the board with the given number of mines
     * 
     * @param int numOfMines
     */
    private void loadMines(int numOfMines) {
	ArgumentChecks.isTrue(numOfMines <= (board[0].length * board.length),
		"Number of Mines must be less than the total size of the board");
	Random random = new Random();
	while (numOfMines > 0) {
	    int xCoord2PutAMine = random.nextInt(this.board[0].length);
	    int yCoord2PutAMine = random.nextInt(this.board.length);
	    while (board[yCoord2PutAMine][xCoord2PutAMine].hasMine()) {
		xCoord2PutAMine = random.nextInt(this.board[0].length);
		yCoord2PutAMine = random.nextInt(this.board.length);
	    }
	    board[yCoord2PutAMine][xCoord2PutAMine].putMine();
	    board[yCoord2PutAMine][xCoord2PutAMine].setAction(new BlowUpAction(this));
	    numOfMines--;
	}

    }

    /**
     * Changes the value of Squares near to mines in accordance on how many mines
     * are near them. 1 mine near, it means its value is set to 1, 2, to 2 and so
     * on..
     * 
     */
    private void changeVal2SqrNearMine() {
	for (int y = 0; y < board.length; y++) {
	    for (int x = 0; x < board[y].length; x++) {

		for (int y_cord = y - 1; y_cord <= y + 1; y_cord++) {
		    for (int x_cord = x - 1; x_cord <= x + 1; x_cord++) {
			if (y_cord >= 0 && x_cord >= 0 && y_cord < board.length && x_cord < board[0].length) {
			    if (board[y_cord][x_cord].hasMine() && !board[y][x].hasMine()) {
				board[y][x].setValue(board[y][x].getValue() + 1);
			    }
			}

		    }
		}
	    }

	}
    }

    /**
     * @return the numOfMines
     */
    private int getNumOfMines() {
	return this.numOfMines;
    }

    /**
     * @param numOfMines the numOfMines to set
     */
    private void setNumOfMines(int numOfMines) {
	ArgumentChecks.checkNumber(numOfMines, "number of mines must be strictly positive");
	this.numOfMines = numOfMines;
    }

    /**
     * @param flags the flags to set
     */
    private void setFlags(int flags) {
	ArgumentChecks.isTrue(flags >= 0, "number of flags must be positive");
	this.flags = flags;
    }

    /**
     * Checks that every parameter is valid
     * 
     * @param width
     * @param height
     * @param percentage
     */
    private void checkParameters(int width, int height, int percentage) {
	ArgumentChecks.checkNumber(width, "width must be strictly positive");
	ArgumentChecks.checkNumber(height, "height must be strictly positive");
	ArgumentChecks.checkNumber(percentage, "percentage must be strictly positive");
	ArgumentChecks.isTrue(percentage <= 100, "percentage cannot be greater than 100%");
    }

}