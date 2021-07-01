package uo.mp.minesweeper.board;

import static org.junit.Assert.*;



import org.junit.Before;
import org.junit.Test;

import uo.mp.minesweeper.game.Board;
import uo.mp.minesweeper.game.Square;
import uo.mp.minesweeper.game.Square.State;
import uo.mp.minesweeper.game.actions.BlowUpAction;
import uo.mp.minesweeper.game.actions.ClearAction;
import uo.mp.minesweeper.util.collections.List;
import uo.mp.minesweeper.util.collections.impl.ArrayList;

public class UnflagTests {
	private Board board;
	private Square sq0;
	private Square sq1;
	private Square sq2;
	private Square sq3;
	private Square sq4;
	private Square sq5;
	private Square sq6;
	private Square sq7;
	private Square sq8;
	@Before
	public void setUp()  {
		sq0 = new Square(State.CLOSED, 1);
		sq1 = new Square(State.CLOSED, -1);
		sq2 = new Square(State.CLOSED, 1);
		sq3 = new Square(State.CLOSED, 1);
		sq4 = new Square(State.CLOSED, 1);
		sq5 = new Square(State.CLOSED, 1);
		sq6 = new Square(State.CLOSED, 0);
		sq7 = new Square(State.CLOSED, 0);
		sq8 = new Square(State.CLOSED, 0);
		List<Square> neighBouringSquares6 = new ArrayList<>();
		neighBouringSquares6.add(sq3);
		neighBouringSquares6.add(sq4);
		neighBouringSquares6.add(sq7);
		sq6.setAction(new ClearAction(neighBouringSquares6));
		List<Square> neighBouringSquares7 = new ArrayList<>();
		neighBouringSquares7.add(sq6);
		neighBouringSquares7.add(sq3);
		neighBouringSquares7.add(sq4);
		neighBouringSquares7.add(sq5);
		neighBouringSquares7.add(sq8);
		sq7.setAction(new ClearAction(neighBouringSquares7));
		List<Square> neighBouringSquares8 = new ArrayList<>();
		neighBouringSquares8.add(sq7);
		neighBouringSquares8.add(sq4);
		neighBouringSquares8.add(sq5);
		sq8.setAction(new ClearAction (neighBouringSquares8));
		Square[][] squaresBoard = new Square[][] {			
			{sq0, sq1, sq2},
			{sq3, sq4, sq5},
			{sq6, sq7, sq8}
			};
		board = new Board(2, squaresBoard);
		sq1.setAction(new BlowUpAction(board));
	}
	/**
	 * GIVEN: A 4x4 board
	 * WHEN: unmark() a tile that was marked and hides a bomb.
	 * THEN: It unmarks the tile
	 */
	@Test
	public void unflagWhenAMineWasFlagged() {
		board.flag(1, 0);
		board.unflag(1, 0);
		assertEquals('#', board.getStatus()[0][1]);
		assertTrue(!board.getSquaresForTest()[0][1].isOpen());
		assertTrue(!board.getSquaresForTest()[0][1].hasFlag());
		assertTrue(!board.isExploded());
		assertEquals(2, board.getFlagsLeft());
		assertEquals(2, board.getMinesLeft());
	}
	/**
	 * GIVEN: A 4x4 board
	 * WHEN: unmark() a tile that was not marked and hides a bomb.
	 * THEN: The tile remains closed
	 */
	@Test
	public void unflagAMineNotArmed() {
		board.unflag(1, 0);
		assertEquals('#', board.getStatus()[0][1]);
		assertTrue(!board.getSquaresForTest()[0][1].isOpen());
		assertTrue(!board.getSquaresForTest()[0][1].hasFlag());
		assertTrue(!board.isExploded());
		assertEquals(2, board.getFlagsLeft());
		assertEquals(2, board.getMinesLeft());
	}
	
	/**
	 * GIVEN: A 4x4 board
	 * WHEN: unmark() a tile that was not marked and does not hide a bomb.
	 * THEN: Tile remains closed
	 */
	@Test
	public void unflagATileWithoutMineNotArmed() {
		board.unflag(0, 0);
		assertEquals('#', board.getStatus()[0][0]);
		assertTrue(!board.getSquaresForTest()[0][0].hasFlag());
		assertTrue(!board.getSquaresForTest()[0][0].isOpen());
		assertTrue(!board.isExploded());
		assertEquals(2, board.getFlagsLeft());
		assertEquals(2, board.getMinesLeft());
	}


}
