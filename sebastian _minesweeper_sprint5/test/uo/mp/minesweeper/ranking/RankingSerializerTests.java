package uo.mp.minesweeper.ranking;

import static org.junit.Assert.assertEquals;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


import org.junit.Before;
import org.junit.Test;

import uo.mp.minesweeper.game.GameLevel;
import uo.mp.minesweeper.util.Console;
import uo.mp.minesweeper.util.collections.List;
import uo.mp.minesweeper.util.collections.impl.LinkedList;

public class RankingSerializerTests {
    private String score1 = "xXSebas;08/05/20;18:00:01;EASY;won;2";
    private String score2 = "Lau;08/05/20;18:00:09;MEDIUM;lost;2";
    private String score3 = "xXSebas;09/05/20;18:01:09;MEDIUM;lost;2";
    private String score4 = "xXSebas;09/05/20;18:01:09;HIGH;lost;2";

    private Score sc1 = this.createScore(score1.split(";"));
    private Score sc2 = this.createScore(score2.split(";"));
    private Score sc3 = this.createScore(score3.split(";"));
    private Score sc4 = this.createScore(score4.split(";"));;

    private RankingSerializer sr = new RankingSerializer();

    private List<Score> scores = new LinkedList<>();
    private List<String> lines = new LinkedList<>();

    @Before
    public void setUp() {
	lines.add(score1);
	lines.add(score2);
	lines.add(score3);
	lines.add(score4);

	scores.add(sc1);
	scores.add(sc2);
	scores.add(sc3);
	scores.add(sc4);

    }

    /**
     * GIVEN: A null Score list WHEN: serialize() THEN: empty list is returned
     */
    @Test
    public void serializeNullScoreList() {
	lines.clear();
	scores = null;
	assertEquals(lines, sr.serialize(scores));
    }

    /**
     * GIVEN: A empty Score list
     *  WHEN: serialize() 
     *  THEN: empty list is returned
     */
    @Test
    public void serializeEmptyScoreList() {
	lines.clear();
	scores.clear();
	assertEquals(lines, sr.serialize(scores));
    }

    /**
     * GIVEN: A Score list with several scores 
     * WHEN: serialize() 
     * THEN: a correct
     * list is returned
     */
    @Test
    public void serializeCorrectScoreList() {
	assertEquals(lines, sr.serialize(scores));
    }

    private Score createScore(String[] score) {
	String name = score[0];
	String strDate = score[1];
	String strTime = score[2];
	GameLevel level = getLevel(score[3]);
	boolean result = getResult(score[4]);
	long timeSpent = getTimeSpent(score[5]);
	String str = strDate + " " + strTime;
	Date date = createDate(str);
	Score newScore = new Score(name, level, timeSpent, result, date);
	return newScore;
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

    private boolean getResult(String result) {
	switch (result) {
	case "won":
	    return true;
	case "lost":
	    return false;
	}
	return false;
    }

    private long getTimeSpent(String time) {
	long timeSpent = Long.parseLong(time);
	return timeSpent;
    }

    private GameLevel getLevel(String level) {
	switch (level) {
	case "EASY":
	    return GameLevel.EASY;
	case "MEDIUM":
	    return GameLevel.MEDIUM;
	case "HIGH":
	    return GameLevel.HIGH;
	}
	return null;
    }
}
