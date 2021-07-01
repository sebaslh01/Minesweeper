package uo.mp.minesweeper.ranking;

import static org.junit.Assert.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


import org.junit.Before;
import org.junit.Test;

import uo.mp.minesweeper.game.GameLevel;
import uo.mp.minesweeper.util.Console;
import uo.mp.minesweeper.util.collections.List;
import uo.mp.minesweeper.util.collections.impl.LinkedList;
import uo.mp.minesweeper.util.log.FileLogger;

public class RankingParserTests {
	private String score1 = "xXSebas;08/05/20;18:00:01;EASY;won;2";
	private String score2= "Lau;08/05/20;18:00:09;MEDIUM;lost;2";
	private String score3 = "xXSebas;09/05/20;18:01:09;MEDIUM;lost;2";
	private String score4 = "xXSebas;09/05/20;18:01:09;HIGH;lost;2";
	
	private Score sc1 = this.createScore(score1.split(";"));
	private Score sc2 = this.createScore(score2.split(";"));
	private Score sc3 = this.createScore(score3.split(";"));
	private Score sc4 = this.createScore(score4.split(";"));;
	
	private RankingParser ps = new RankingParser(new FileLogger("tests.log"));
	
	private List<String> lines = new LinkedList<>();
	private List<Score> scores = new LinkedList<>();
	@Before
	public void setUp(){
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
	 * GIVEN: A list of valid one line 
	 * WHEN: parse()
	 * THEN: a correct list of Score is returned
	 */
	@Test
	public void parseOneCorrectLine() {	
		lines.clear();
		lines.add(score1);
		
		scores.clear();
		scores.add(sc1);
		assertEquals(scores, ps.parse(lines));
	}
	
	/**
	 * GIVEN: A list of valid several lines
	 * WHEN: parse()
	 * THEN: a correct list of Score is returned
	 */
	@Test
	public void parseSeveralCorrectLines() {	
		assertEquals(scores, ps.parse(lines));
	}
	
	/**
	 * GIVEN: A null lines list
	 * WHEN: parse()
	 * THEN: empty list is returned
	 */
	@Test
	public void parseNullLinesList() {	
		scores.clear();
		lines = null;
		assertEquals(scores, ps.parse(lines));
	}
	
	/**
	 * GIVEN: An empty  lines list
	 * WHEN: parse()
	 * THEN: empty list is returned
	 */
	@Test
	public void parseEmptyLinesList() {	
		scores.clear();
		lines.clear();
		assertEquals(scores, ps.parse(lines));
	}
	
	

	/**
	 * GIVEN: A list with one invalid line, fields separated by ":" instead of ";"
	 * WHEN: parse()
	 * THEN: empty list is returned
	 */
	@Test
	public void parseWhenWrongSeparatorInALine() {	
		scores.clear();
		lines.clear();	
		score1 = "xXSebas:08/05/20:18:00:01:EASY:won:2";
	

		lines.add(score1);
		assertEquals(scores, ps.parse(lines));
	}
	
	/**
	 * GIVEN: A list with incorrect line less fields than expected
	 * WHEN: parse()
	 * THEN: empty list is returned
	 */
	@Test
	public void parseLessFieldsThanExpectedInLine() {	
		scores.clear();
		lines.clear();	
		
		score1 = "xXSebas;08/05/20;18:00:01;EASY;won";

		lines.add(score1);
		assertEquals(scores, ps.parse(lines));
	}
	
	/**
	 * GIVEN: A list with incorrect line, order of fields wrong
	 * WHEN: parse()
	 * THEN: empty list is returned
	 */
	@Test
	public void parseWrongFieldsOrderInLine() {	
		scores.clear();
		lines.clear();	
		
		score1 = "xXSebas;18:00:01;08/05/20;won;EASY";

		lines.add(score1);
		assertEquals(scores, ps.parse(lines));
	}

	/**
	 * GIVEN: A list with incorrect line wrong username format
	 * WHEN: parse()
	 * THEN: empty list is returned
	 */
	@Test
	public void parseWhenWrongUsernameFormat() {	
		scores.clear();
		lines.clear();	
		
		score1 = ";08/05/20;18:00:01;EASY;won;2";

		lines.add(score1);
		assertEquals(scores, ps.parse(lines));
	}
	
	
	/**
	 * GIVEN: A list with incorrect line wrong date format
	 * WHEN: parse()
	 * THEN: empty list is returned
	 */
	@Test
	public void parseWhenWrongDateFormat() {	
		scores.clear();
		lines.clear();	
		
		score1 = "xxSebas;08-05-20;18:00:01;EASY;won;2";

		lines.add(score1);
		assertEquals(scores, ps.parse(lines));
	}
	/**
	 * GIVEN: A list with incorrect line wrong level format
	 * WHEN: parse()
	 * THEN: empty list is returned
	 */
	@Test
	public void parseWhenWrongLevelFormat() {	
		scores.clear();
		lines.clear();	
		
		score1 = "xxSebas;08/05/20;18:00:01;easy;won;2";

		lines.add(score1);
		assertEquals(scores, ps.parse(lines));
	}
	/**
	 * GIVEN: A list with incorrect line wrong result format
	 * WHEN: parse()
	 * THEN: empty list is returned
	 */
	@Test
	public void parseWhenWrongResultFormat() {	
		scores.clear();
		lines.clear();	
		
		score1 = ";08/05/20;18:00:01;EASY;los;2";

		lines.add(score1);
		assertEquals(scores, ps.parse(lines));
	}
	
	/**
	 * GIVEN: A list with incorrect line wrong time format
	 * WHEN: parse()
	 * THEN: empty list is returned
	 */
	@Test
	public void parseWhenWrongElapsedTimeFormat() {	
		scores.clear();
		lines.clear();	
		
		score1 = "xxSebas;08/05/20;18:00:01;EASY;los;d";

		lines.add(score1);
		assertEquals(scores, ps.parse(lines));
	}
	private Score createScore(String [] score) {
		String name = score [0];
		String strDate = score[1];
		String strTime = score[2];
		GameLevel level = getLevel(score[3]);
		boolean result = getResult(score[4]);
		long timeSpent = getTimeSpent(score[5]);
		String str = strDate+ " " + strTime;
		Date date =createDate(str);
		Score newScore = new Score(name, level, timeSpent, result, date);
		return newScore;
	}


	private Date createDate(String str)  {
		Date date = null;
		try {
			date = new SimpleDateFormat("dd/MM/yy HH:mm:ss").parse(str);
		} catch (ParseException e) {
			Console.printError("Something went wrong with RankingParserTests");;
		}
		return date;
	}
	
	private boolean getResult(String result)  {
		switch(result) {
		case "won" : 
			return true;
		case "lost" :
			return false;
		}
		return false;
	}

	private long getTimeSpent(String time) {
		long timeSpent = Long.parseLong(time);
		return timeSpent;
	}

	private GameLevel getLevel(String level)  {
		switch(level) {
		case "EASY": 
			return GameLevel.EASY;		
		case "MEDIUM": 
			return GameLevel.MEDIUM;		
		case "HIGH" : 
			return GameLevel.HIGH;
		}
		return null;
	}
}
