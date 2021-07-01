package uo.mp.minesweeper.ranking;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ RankingParserTests.class, RankingSerializerTests.class, ScoreComparatorTests.class })
public class AllRankingTests {

}
