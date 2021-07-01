package uo.mp.minesweeper.board;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ ConstructorTests.class, FlagTests.class, GetStatusTests.class, StepOnTests.class, UnflagTests.class,
		UnveilTests.class, UncoverWelcomeTests.class })
public class AllBoardTests {

}
