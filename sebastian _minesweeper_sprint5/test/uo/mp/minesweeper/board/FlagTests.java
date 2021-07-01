
package uo.mp.minesweeper.board;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import uo.mp.minesweeper.game.Board;
import uo.mp.minesweeper.game.Square;
import uo.mp.minesweeper.game.Square.State;

public class FlagTests {

	private Board board;

	@Before
	public void setUp()  {
		Square[][] squaresBoard = new Square[][] {
			{new Square(State.CLOSED, 1), new Square(State.CLOSED, -1), new Square(State.CLOSED, 1), new Square(State.CLOSED, 0)},
			{new Square(State.CLOSED, 1), new Square(State.CLOSED, 1), new Square(State.CLOSED, 1), new Square(State.CLOSED, 0)},
			{new Square(State.CLOSED, 0), new Square(State.CLOSED, 0), new Square(State.CLOSED, 1), new Square(State.CLOSED, 1)},
			{new Square(State.CLOSED, 0), new Square(State.CLOSED, 0), new Square(State.CLOSED, 1), new Square(State.CLOSED, -1)}
			};			
		board = new Board(2, squaresBoard);
	}
	/**
	 * GIVEN: A 4x4 board, there is already a flag on that square
	 * WHEN: flag()
	 * THEN: It doesn't put another flag 
	 */
	@Test
	public void flagWhenItHadAFlag() {
		board.flag(0, 0);
		board.flag(0, 0);
		assertEquals('¶', board.getStatus()[0][0]);
		assertTrue(board.getSquaresForTest()[0][0].hasFlag());
		assertTrue(!board.getSquaresForTest()[1][1].isOpen());
		assertTrue(!board.isExploded());
		assertEquals(1, board.getFlagsLeft());
		assertEquals(2, board.getMinesLeft());
	}
	
	/**
	 * GIVEN: A 4x4 board, there is still no flag and it hides a mine
	 * WHEN: flag()
	 * THEN: It puts a flag
	 */
	@Test
	public void flagInTileWithMine() {
		board.flag(1, 0);
		assertEquals('¶', board.getStatus()[0][1]);
		assertTrue(board.getSquaresForTest()[0][1].hasFlag());
		assertTrue(!board.getSquaresForTest()[1][1].isOpen());
		assertTrue(!board.isExploded());
		assertEquals(1, board.getFlagsLeft());
		assertEquals(1, board.getMinesLeft());
	}
	
	/**
	 * GIVEN: A 4x4 board, there is still no flag and it does not hide a mine
	 * WHEN: flag()
	 * THEN: It puts a flag
	 */
	@Test
	public void flagInTileWithNoMine() {
		board.flag(0, 0);
		assertEquals('¶', board.getStatus()[0][0]);
		assertTrue(board.getSquaresForTest()[0][0].hasFlag());
		assertTrue(!board.getSquaresForTest()[1][1].isOpen());
		assertTrue(!board.isExploded());
		assertEquals(1, board.getFlagsLeft());
		assertEquals(2, board.getMinesLeft());
	}
	
	

}
