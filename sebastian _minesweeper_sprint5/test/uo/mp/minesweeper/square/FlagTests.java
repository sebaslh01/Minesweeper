package uo.mp.minesweeper.square;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import uo.mp.minesweeper.game.Square;
import uo.mp.minesweeper.game.Square.State;

public class FlagTests {

	private Square square;
	@Before
	public void setUp()  {
		square = new Square(State.CLOSED, 0); //closed, NO CLUES.
	}
	/**
	 * GIVEN: An OPEN square with no clues (value = 0).
	 * WHEN : flag()
	 * THEN : state still OPEN.
	 */
	@Test
	public void flag_OPEN() {
		square.open();
		square.flag();
		assertTrue(!square.hasFlag());
		assertTrue(square.isOpen());
	}
	
	/**
	 * GIVEN: A CLOSED square with no clues (value = 0).
	 * WHEN : flag()
	 * THEN : state changed to FLAGGED.
	 */
	@Test
	public void flag_CLOSED() {
		square.flag();
		assertTrue(!square.isOpen());
		assertTrue(square.hasFlag());
	}
	
	
	/**
	 * GIVEN: A FLAGGED square with no clues (value = 0).
	 * WHEN : flag()
	 * THEN : state still FLAGGED.
	 */
	@Test
	public void flag_FLAGGED() {
		square.flag();
		square.flag();
		assertTrue(!square.isOpen());
		assertTrue(square.hasFlag());
	}

}
