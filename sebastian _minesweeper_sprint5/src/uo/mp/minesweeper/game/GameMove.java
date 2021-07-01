package uo.mp.minesweeper.game;
/**
 * acts just a data container to store a user action: operation, row and column
 * @author sebas
 *
 */
public class GameMove {
    private char operation;
    private int row;
    private int column;

    /**
     * Constructor. Receives a character (‘s’, ‘f’, o ‘u’) representing the
     * operation and two integer numbers row and column indicating the square where
     * the action will be executed.
     * 
     * @param char operation( 's'. 'f' or 'u')
     * @param int  row
     * @param int  column
     */
    public GameMove(char operation, int row, int column) {
	this.operation = operation;
	this.row = row;
	this.column = column;
    }

    /**
     * @return the character for the action to be executed
     */
    public char getOperation() {
	return this.operation;
    }

    /**
     * @return the row number
     */
    public int getRow() {
	return this.row;
    }

    /**
     * @return the column number
     */
    public int getColumn() {
	return this.column;
    }

    /**
     * Returns a text-based representation of the complete command to be executed
     */
    @Override
    public String toString() {
	return "GameMove [operation =" + this.operation + ", row=" + this.row + ", column=" + this.column + " ]";
    }
}
