package uo.mp.minesweeper.board;

import static org.junit.Assert.*;

import org.junit.Test;

import uo.mp.minesweeper.game.Board;
import uo.mp.minesweeper.game.Square;

public class ConstructorTests {
	
	private Board board;
	
	/**
	 * GIVEN: Invalid parameters(width = 0, height = 9, percentage = 12)
	 * WHEN: Constructing a board
	 * THEN: IllegalArgumentException
	 */
	@Test(expected=IllegalArgumentException.class)
	public void invalidParameters1() {
		board = new Board(0, 9, 12);
		
	}
	
	/**
	 * GIVEN: Invalid parameters(width = 9, height = 0, percentage = 12)
	 * WHEN: Constructing a board
	 * THEN: IllegalArgumentException
	 */
	@Test(expected=IllegalArgumentException.class)
	public void invalidParameters2() {
		board = new Board(9, 0, 12);
		
	}
	/**
	 * GIVEN: Invalid parameters(width = 9, height = 9, percentage = 0)
	 * WHEN: Constructing a board
	 * THEN: IllegalArgumentException
	 */
	@Test(expected=IllegalArgumentException.class)
	public void invalidParameters3() {
		board = new Board(9, 9, 0);
		
	}
	
	/**
	 * GIVEN: Invalid parameters(width = 9, height = 9, percentage = -1)
	 * WHEN: Constructing a board
	 * THEN: IllegalArgumentException
	 */
	@Test(expected=IllegalArgumentException.class)
	public void invalidParameters4() {
		board = new Board(9, 9, -1);
		
	}
	
	/**
	 * GIVEN: Invalid parameters(width = 9, height = 9, percentage = 101)
	 * WHEN: Constructing a board
	 * THEN: IllegalArgumentException
	 */
	@Test(expected=IllegalArgumentException.class)
	public void invalidParameters5() {
		board = new Board(9, 9, 101);
		
	}
	/**
	 * GIVEN: Parameters(width = 9, height = 9, percentage = 1)
	 * WHEN: Constructing a board
	 * THEN: It has the size (9x9) and 1 possible mines(1% of 81 =0,81 rounded to 1)
	 */
	@Test
	public void validParameters1() {
		board = new Board(9, 9, 1);
		int boardWidth = board.getSquaresForTest()[0].length;
		int boardHeight = board.getSquaresForTest().length;
		
		Square [][] expected = new Square[9][9];
		Board boardExpected = new Board(1, expected);
		int expectedWidth = boardExpected.getSquaresForTest()[0].length;
		int expectedHeight = boardExpected.getSquaresForTest().length;
		

		assertEquals(expectedWidth, boardWidth);
		assertEquals(expectedHeight, boardHeight);
		assertEquals(1, board.getFlagsLeft());
	}
	/**
	 * GIVEN: Parameters(width = 9, height = 9, percentage = 12)
	 * WHEN: Constructing a board
	 * THEN: It has the size (9x9) and 10 possible mines(12% of 81 =9,72 rounded to 10)
	 */
	@Test
	public void validParameters2() {
		board = new Board(9, 9, 12);
		int boardWidth = board.getSquaresForTest()[0].length;
		int boardHeight = board.getSquaresForTest().length;
		
		Square [][] expected = new Square[9][9];
		Board boardExpected = new Board(10, expected);
		int expectedWidth = boardExpected.getSquaresForTest()[0].length;
		int expectedHeight = boardExpected.getSquaresForTest().length;
		
		assertEquals(expectedWidth, boardWidth);
		assertEquals(expectedHeight, boardHeight);
		assertEquals(10, board.getFlagsLeft());
	}
	/**
	 * GIVEN: Parameters(width = 9, height = 9, percentage = 50)
	 * WHEN: Constructing a board
	 * THEN: It has the size (9x9) and 81 possible mines(100% of 81 =81)
	 */
	@Test
	public void validParameters3() {
		board = new Board(9, 9, 50);
		int boardWidth = board.getSquaresForTest()[0].length;
		int boardHeight = board.getSquaresForTest().length;
		
		Square [][] expected = new Square[9][9];
		Board boardExpected = new Board(41, expected);
		int expectedWidth = boardExpected.getSquaresForTest()[0].length;
		int expectedHeight = boardExpected.getSquaresForTest().length;
		
		assertEquals(expectedWidth, boardWidth);
		assertEquals(expectedHeight, boardHeight);
		assertEquals(41, board.getFlagsLeft());
	}
	/**
	 * GIVEN: Parameters(width = 9, height = 9, percentage = 100)
	 * WHEN: Constructing a board
	 * THEN: It has the size (9x9) and 81 possible mines(100% of 81 =81)
	 */
	@Test
	public void validParameters4() {
		board = new Board(9, 9, 100);
		int boardWidth = board.getSquaresForTest()[0].length;
		int boardHeight = board.getSquaresForTest().length;
		
		Square [][] expected = new Square[9][9];
		Board boardExpected = new Board(81, expected);
		int expectedWidth = boardExpected.getSquaresForTest()[0].length;
		int expectedHeight = boardExpected.getSquaresForTest().length;
		
		assertEquals(expectedWidth, boardWidth);
		assertEquals(expectedHeight, boardHeight);
		assertEquals(81, board.getFlagsLeft());
	}
 
}
