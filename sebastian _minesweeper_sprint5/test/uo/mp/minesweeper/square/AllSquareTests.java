package uo.mp.minesweeper.square;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ FlagTests.class, StepOnTests.class, UnflagTests.class, 
	ActivateBlowUpActionTests.class, ActivateClearActionTests.class })
public class AllSquareTests {

}
