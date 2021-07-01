package uo.mp.minesweeper.game.actions;

import uo.mp.minesweeper.game.Board;

public class BlowUpAction implements Action {
    private Board board;
    
    /**
     *  It receives a reference to the Board instance containing the square
     *  associated to this action
     * @param board
     */
    public BlowUpAction(Board board) {
	this.board = board;
    }
    
    /*
     * It causes the board to be marked as blown up.
     */
    @Override
    public void activate() {
	this.board.markAsExploded();
    }

}
