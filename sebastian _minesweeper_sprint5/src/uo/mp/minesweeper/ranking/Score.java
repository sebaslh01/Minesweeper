package uo.mp.minesweeper.ranking;

import java.util.Date;

import uo.mp.minesweeper.game.GameLevel;
import uo.mp.minesweeper.util.ArgumentChecks;

/**
 * It stores the score (and other information) of a single game.
 * 
 * @author sebas
 *
 */
public class Score implements java.io.Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private String userName;
    private GameLevel level;
    private long time;
    private boolean hasWon;
    private Date date = new Date();

    public Score(String userName, GameLevel level, long time, boolean hasWon) {
	ArgumentChecks.checkString(userName);
	ArgumentChecks.isNotNull(level);
	this.userName = userName;
	this.level = level;
	this.time = time;
	this.hasWon = hasWon;
    }

    public Score(String userName, GameLevel level, long time, boolean hasWon, Date date) {
	this(userName, level, time, hasWon);
	ArgumentChecks.isNotNull(date);
	this.date = date;
    }

    public String getUserName() {
	return userName;
    }

    public long getTime() {
	return time;
    }

    public boolean hasWon() {
	return hasWon;
    }

    public Date getDate() {
	return date;
    }

    public GameLevel getLevel() {
	return level;
    }

    @Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((date == null) ? 0 : date.hashCode());
	result = prime * result + (hasWon ? 1231 : 1237);
	result = prime * result + ((level == null) ? 0 : level.hashCode());
	result = prime * result + (int) (time ^ (time >>> 32));
	result = prime * result + ((userName == null) ? 0 : userName.hashCode());
	return result;
    }

    @Override
    public boolean equals(Object obj) {
	if (this == obj)
	    return true;
	if (obj == null)
	    return false;
	if (getClass() != obj.getClass())
	    return false;
	Score other = (Score) obj;
	if (date == null) {
	    if (other.date != null)
		return false;
	} else if (!date.equals(other.date))
	    return false;
	if (hasWon != other.hasWon)
	    return false;
	if (level != other.level)
	    return false;
	if (time != other.time)
	    return false;
	if (userName == null) {
	    if (other.userName != null)
		return false;
	} else if (!userName.equals(other.userName))
	    return false;
	return true;
    }

}
