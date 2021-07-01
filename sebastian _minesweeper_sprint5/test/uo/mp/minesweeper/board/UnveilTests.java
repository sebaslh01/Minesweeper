package uo.mp.minesweeper.board;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import uo.mp.minesweeper.game.Board;
import uo.mp.minesweeper.game.Square;
import uo.mp.minesweeper.game.Square.State;

public class UnveilTests {

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
	 * GIVEN: A 4x4 board
	 * WHEN: unveil() after every square was closed
	 * THEN: Every square is open
	 */
	@Test
	public void unveilWhenAllSquaresHidden() {
		board.unveil();
		Square[][]boardGame = board.getSquaresForTest();
		for (Square[] squares : boardGame) {
			for (Square square: squares) {
				assertTrue(square.isOpen());
			}
		}
	}
	
	/**
	 * GIVEN: A 4x4 board
	 * WHEN: unveil() after some squares were flagged
	 * THEN: Every square is open
	 */
	@Test
	public void unveilWhenSomeSquareHadFlag() {
		board.flag(1,1);
		board.flag(3, 3);
		board.flag(2, 2);
		board.unveil();
		Square[][]boardGame = board.getSquaresForTest();
		for (Square[] squares : boardGame) {
			for (Square square: squares) {
				assertTrue(square.isOpen());
			}
		}
	}
	/**
	 * GIVEN: A 4x4 board
	 * WHEN: unveil() after some squares were flagged
	 * THEN: Every square is open
	 */
	@Test
	public void unveilWhenSomeSquareWasRevealed() {
		board.stepOn(1,1);
		board.stepOn(3, 3);
		board.stepOn(2, 2);
		board.unveil();
		Square[][]boardGame = board.getSquaresForTest();
		for (Square[] squares : boardGame) {
			for (Square square: squares) {
				assertTrue(square.isOpen());
			}
		}
	}

	

}
