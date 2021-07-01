package uo.mp.minesweeper.game;

import uo.mp.minesweeper.game.actions.Action;
import uo.mp.minesweeper.game.actions.NullAction;
import uo.mp.minesweeper.util.ArgumentChecks;

/**
 * MineSweeper sprint1
 * Represents a single tile in the board.
 * 
 * @author UO277069 - Sebastián López Hernández
 * @version sprint1
 *
 */
public class Square {
    public enum State {
	CLOSED, OPEN, FLAGGED
    };

    private State state;
    private Action action;
    private int value;

    /**
     * Constructor for objects of the Square class.
     * 
     * @param String state that is one of the POSSIBLE_STATES.
     * @param int    content that is one of the POSSIBLE_CONTENTS.
     */
    public Square(State state, int content) {
	setState(state);
	setValue(content);
	setAction(new NullAction());
    }

    public Square(State state, int content, Action action) {
	setState(state);
	setValue(content);
	setAction(action);
    }

    /**
     * If actual state is CLOSED, it becomes OPEN. Otherwise, does nothing.
     */
    public void stepOn() {
	if (state.equals(State.CLOSED)) { // current state = CLOSED
	    open();
	    this.action.activate();
	}
    }

    /**
     * Returns a character representing the square according to its state and
     * content
     */
    public String toString() {
	if (state.equals(State.CLOSED))
	    return "#";
	else if (state.equals(State.FLAGGED))
	    return Character.toString((char) 182);
	else if (this.hasMine())
	    return "@";
	else if (this.getValue() == 0)
	    return " ";
	return String.valueOf(this.getValue());
    }

    /**
     * If actual state is CLOSED, it becomes FLAGGED. Otherwise , does nothing.
     */
    public void flag() {
	if (state.equals(State.CLOSED))
	    state = State.FLAGGED;
    }

    /**
     * If actual state is FLAGEGD, it becomes CLOSED. Otherwise, does nothing.
     */
    public void unflag() {
	if (hasFlag())
	    state = State.CLOSED;
    }

    /**
     * State becomes OPEN .
     */
    public void open() {
	if (!this.isOpen())
	    setState(State.OPEN);
    }

    /**
     * Returns whether state is OPEN or not
     * 
     * @return boolean true whenever state is OPEN, false otherwise.
     */
    public boolean isOpen() {
	return state.equals(State.OPEN); // OPEN
    }

    /**
     * Returns whether state's FLAGGED or not
     * 
     * @return boolean true whenever state is FLAGGED; false otherwise.
     */
    public boolean hasFlag() {
	return state.equals(State.FLAGGED);
    }

    /**
     * Set square's value to the one corresponding to a mine, see comments above in
     * POSSIBLE_CONTENTS.
     * 
     */
    public void putMine() {
	setValue(-1);
    }

    /**
     * Returns true whenever the square hides a mine, false otherwise.
     * 
     * @return boolean true whenever the square hides a mine; false otherwise.
     */
    public boolean hasMine() {
	return getValue() == -1;
    }

    /**
     * Changes the current state of the square object to the one given as parameter
     * 
     * @param state the state to set
     */
    public void setState(State state) {
	ArgumentChecks.isTrue(state != null, "State cannot be null");
	this.state = state;
    }

    /**
     * Returns the content of the square, that is its value
     * 
     * @return the content
     */
    public int getValue() {
	return value;
    }

    /**
     * Changes the value of the square object to the given content as a parameter
     * 
     * @param content the value to set
     */
    public void setValue(int value) {
	ArgumentChecks.isTrue(value >= (-1), "value cannot be less than -1");
	ArgumentChecks.isTrue(value <= 8, "value cannot be greater than 8");
	this.value = value;
    }

    /**
     * Sets the action to the square
     * 
     * @param Action action
     */
    public void setAction(Action action) {
	ArgumentChecks.isTrue(action != null, "It cannot be null");
	this.action = action;
    }

}
