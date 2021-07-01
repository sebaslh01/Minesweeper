package uo.mp.minesweeper.ranking;

import static org.junit.Assert.assertTrue;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Test;

import uo.mp.minesweeper.game.GameLevel;
import uo.mp.minesweeper.util.Console;

public class ScoreComparatorTests {
    private Score score1 = new Score("sebas", GameLevel.EASY, 10, true, createDate("08/05/20 10:00:00")) ;
    private Score score2 = new Score("sebas", GameLevel.EASY, 5, true, createDate("08/05/20 10:00:20")) ;
    private Score score3 = new Score("sebas", GameLevel.EASY, 5, true, createDate("08/05/20 00:00:00")) ;
    private Score score4 = new Score("sebas", GameLevel.MEDIUM, 10, true, createDate("08/05/20 18:59:01")) ;
    private Score score5 = new Score("sebas", GameLevel.HIGH, 10, true, createDate("08/05/20 18:59:01")) ;
    private ScoreComparator scoreComparator = new ScoreComparator();
    /**
     * GIVEN: Two equal scores
     * WHEN: ScoreComparator.compareTo(sc1, sc2);
     * THEN: 0
     */
    @Test
    public void compareEqualScores() {
	assertTrue(scoreComparator.compare(score1, score1) == 0);
    }
    
    /**
     * GIVEN: Two scores , same level, second one has shorter elapsed time, as consequence it goes first
     * WHEN: ScoreComparator.compareTo(sc1, sc2);
     * THEN: int returned >0
     */
    @Test
    public void compareSameLevelScores() {
	assertTrue(scoreComparator.compare(score1, score2) > 0);
    }
    
    /**
     * GIVEN: Two scores , same level and elapsed time, second one is oldest, thus it goes first
     * WHEN: ScoreComparator.compareTo(sc1, sc2);
     * THEN: int returned >0
     */
    @Test
    public void compareSameLevelAndTimeScores() {
	assertTrue(scoreComparator.compare(score2, score3) > 0);
    }
    
    /**
     * GIVEN: Two scores , different level, first easy , second medium, thus, higher goes first
     * WHEN: ScoreComparator.compareTo(sc1, sc2);
     * THEN: int returned >0
     */
    @Test
    public void compareDifferentLevelScores() {
	assertTrue(scoreComparator.compare(score3, score4) > 0);
    }
    
    /**
     * GIVEN: Two scores , different level, first easy , second high, thus, higher goes first
     * WHEN: ScoreComparator.compareTo(sc1, sc2);
     * THEN: int returned >0
     */
    @Test
    public void compareDifferentLevelScores2() {
	assertTrue(scoreComparator.compare(score3, score5) > 0);
    }
    
    /**
     * GIVEN: Two scores , different level, first medium , second high, thus, higher goes first
     * WHEN: ScoreComparator.compareTo(sc1, sc2);
     * THEN: int returned >0
     */
    @Test
    public void compareDifferentLevelScores3() {
	assertTrue(scoreComparator.compare(score4, score5) > 0);
    }
    private Date createDate(String str) {
	Date date = null;
	try {
	    date = new SimpleDateFormat("dd/MM/yy HH:mm:ss").parse(str);
	} catch (ParseException e) {
	    Console.printError("Something went wrong with RankingParserTests");
	    ;
	}
	return date;
    }
}
