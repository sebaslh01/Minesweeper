package uo.mp.minesweeper.square;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import uo.mp.minesweeper.game.Square;
import uo.mp.minesweeper.game.Square.State;

public class UnflagTests {

	private Square square;
	@Before
	public void setUp()  {
		square = new Square(State.CLOSED, 0); //CLOSED, NO CLUES.
	}
	/**
	 * GIVEN: An OPEN square with no clues (value = 0).
	 * WHEN : unflag()
	 * THEN : state still OPEN.
	 */
	@Test
	public void unflag_OPEN() {
		square.open();
		square.unflag();
		assertTrue(square.isOpen());
		assertTrue(!square.hasFlag());
	}
	
	/**
	 * GIVEN: A CLOSED square with no clues (value = 0).
	 * WHEN : unflag()
	 * THEN : state still CLOSED.
	 */
	@Test
	public void unflag_CLOSED() {
		square.unflag();
		assertTrue(!square.isOpen());
		assertTrue(!square.hasFlag());
	}
	
	
	/**
	 * GIVEN: A FLAGGED square with no clues (value = 0).
	 * WHEN : unflag()
	 * THEN : state changed to CLOSED.
	 */
	@Test
	public void unflag_FLAGGED() {
		square.flag();
		square.unflag();
		assertTrue(!square.isOpen());
		assertTrue(!square.hasFlag());
	}
	
}
