package uo.mp.minesweeper;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import uo.mp.minesweeper.board.AllBoardTests;
import uo.mp.minesweeper.ranking.AllRankingTests;
import uo.mp.minesweeper.square.AllSquareTests;

@RunWith(Suite.class)
@SuiteClasses({AllBoardTests.class, AllSquareTests.class, AllRankingTests.class})
public class AllTests {

}
