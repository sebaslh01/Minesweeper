package uo.mp.minesweeper.square;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import uo.mp.minesweeper.game.Square;
import uo.mp.minesweeper.game.Square.State;

public class StepOnTests {
	private Square square;
	@Before
	public void setUp()  {
		square = new Square(State.CLOSED, 0); //CLOSED, NO CLUES.
	}
	/**
	 * GIVEN: A CLOSED square with no clues (value = 0).
	 * WHEN : stepOn()
	 * THEN : state changed to OPEN.
	 */
	@Test
	public void stepOn_CLOSED() {
		square.stepOn();
		assertTrue(square.isOpen());
	}
	
	/**
	 * GIVEN: An OPEN square with no clues (value = 0).
	 * WHEN : stepOn()
	 * THEN : state still OPEN.
	 */
	@Test
	public void stepOn_OPEN() {
		square.open();
		square.stepOn();
		assertTrue(square.isOpen());
	}
	/**
	 * GIVEN: A FLAGGED square with no clues (value = 0).
	 * WHEN : stepOn()
	 * THEN : state still FLAGGED.
	 */
	@Test
	public void stepOn_FLAGGED() {
		square.flag();
		square.stepOn();
		assertTrue(square.hasFlag());
		assertTrue(!square.isOpen());
	}
}
