package uo.mp.minesweeper.ranking;

import java.util.Comparator;

public class ScoreComparator implements Comparator<Score> {

    /**
     * Games with higher levels of difficulty will apear before, if tie, those with
     * shorter duration will apear before, if tie, the oldest ones will go first.
     */
    @Override
    public int compare(Score o1, Score o2) {
	int result = compareLevel(o1, o2);
	if (result == 0) {
	    result = compareElapsedTime(o1, o2);
	    if (result == 0) {
		result = compareDates(o1, o2);
	    }
	}
	return result;
    }

    private int compareLevel(Score o1, Score o2) {
	return o1.getLevel().compareTo(o2.getLevel());
    }

    private int compareElapsedTime(Score o1, Score o2) {
	return Long.compare(o1.getTime(), o2.getTime());
    }

    private int compareDates(Score o1, Score o2) {
	return o1.getDate().compareTo(o2.getDate());
    }

}
