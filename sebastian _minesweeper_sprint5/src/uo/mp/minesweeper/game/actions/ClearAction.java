package uo.mp.minesweeper.game.actions;




import uo.mp.minesweeper.game.Square;
import uo.mp.minesweeper.util.ArgumentChecks;
import uo.mp.minesweeper.util.collections.List;


public class ClearAction implements Action {
    private List<Square> neighbouringSquares;
    
    /**
     *  It receives as parameter a list of Square objects corresponding to
     *  squares adjacent with the one associated with this action. A square is considered to
     *  be adjacent to a particular one when they are joined by a common edge or meet a
     *  common vertex.
     * @param neighbouringSquares
     */
    public ClearAction(List<Square> neighbouringSquares) {
	ArgumentChecks.isTrue(neighbouringSquares != null, "It cannot be null");
	this.neighbouringSquares = neighbouringSquares;
    }
    /**
     * It causes method stepOn() to be run on adjacent squares (8-neighbours).
     */
    @Override
    public void activate() {
	for (Square square : neighbouringSquares) {
	    square.stepOn();
	}

    }
}
