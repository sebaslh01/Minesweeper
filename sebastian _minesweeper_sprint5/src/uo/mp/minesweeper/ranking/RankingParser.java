package uo.mp.minesweeper.ranking;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


import uo.mp.minesweeper.game.GameLevel;
import uo.mp.minesweeper.util.collections.List;
import uo.mp.minesweeper.util.collections.impl.LinkedList;
import uo.mp.minesweeper.util.exceptions.InvalidLineFormatException;
import uo.mp.minesweeper.util.log.SimpleLogger;
/**
 * Helps to import a ranking file
 * @author sebas
 *
 */
public class RankingParser {

    private static final int ELLAPSED_TIME = 5;
    private static final int RESULT = 4;
    private static final int LEVEL = 3;
    private static final int TIME = 2;
    private static final int DATE = 1;
    private static final int USERNAME = 0;

    private SimpleLogger logger;

    public RankingParser(SimpleLogger logger) {
	this.logger = logger;
    }
    /**
     * Process a list of String lines from a file that contains
     * information about objects of type Score , generates a List<Score>.
     * @param lines
     * @return List<Score>
     */
    public List<Score> parse(List<String> lines) {
	List<Score> scores = new LinkedList<Score>();
	if (lines == null)
	    return scores;
	Score score = null;
	for (String line : lines) {
	    try {
		score = process(line);
		scores.add(score);
	    } catch (InvalidLineFormatException e) {
		this.logger.log(e);
	    }

	}
	return scores;
    }

    private Score process(String line) throws InvalidLineFormatException {
	Score result = null;
	if (lineisBlank(line))
	    throw new InvalidLineFormatException("Wrong format for a score!! Score discarded!! ");
	String[] score = line.split(";");
	if (score.length < 6)
	    throw new InvalidLineFormatException("Wrong format for a score! Less fields than expected!");
	result = createNewScore(score);
	return result;
    }

    private Score createNewScore(String[] score) throws InvalidLineFormatException {
	String name = score[USERNAME];
	String strDate = score[DATE];
	String strTime = score[TIME];
	GameLevel level = getLevel(score[LEVEL]);
	boolean result = getResult(score[RESULT]);
	long timeSpent = getTimeSpent(score[ELLAPSED_TIME]);
	String str = strDate + " " + strTime;
	Date date = null;
	Score newScore = null;
	try {
	    date = new SimpleDateFormat("dd/MM/yy HH:mm:ss").parse(str);
	    newScore = new Score(name, level, timeSpent, result, date);
	} catch (ParseException | IllegalArgumentException e) {
	    String message = e.getMessage() != null
		    ? e.getMessage() : "Wrong format for a score!! Score discarded!! ";
	    throw new InvalidLineFormatException(message);
	}
	return newScore;
    }

    private boolean getResult(String result) throws InvalidLineFormatException {
	switch (result) {
	case "won":
	    return true;
	case "lost":
	    return false;
	default:
	    throw new InvalidLineFormatException("Wrong result format for a score!! Score discarded!! ");
	}
    }

    private long getTimeSpent(String time) throws InvalidLineFormatException {
	long timeSpent = -1;
	try {
	    timeSpent = Long.parseLong(time);
	} catch (NumberFormatException nfe) {
	    throw new InvalidLineFormatException("Wrong time spent format for a score!! Score discarded!! ");
	}
	return timeSpent;
    }

    private GameLevel getLevel(String level) throws InvalidLineFormatException {
	switch (level) {
	case "EASY":
	    return GameLevel.EASY;
	case "MEDIUM":
	    return GameLevel.MEDIUM;
	case "HIGH":
	    return GameLevel.HIGH;

	default:
	    throw new InvalidLineFormatException("Wrong level format for a score!! Score discarded!!");
	}
    }

    private boolean lineisBlank(String line) {
	return line.trim().isEmpty();
    }
}
