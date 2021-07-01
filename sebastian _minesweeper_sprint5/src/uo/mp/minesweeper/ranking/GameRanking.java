package uo.mp.minesweeper.ranking;






import uo.mp.minesweeper.util.ArgumentChecks;
import uo.mp.minesweeper.util.collections.List;
import uo.mp.minesweeper.util.collections.impl.ArrayList;
import uo.mp.minesweeper.util.collections.impl.LinkedList;
/**
 * This class stores a list of Score objects representing finished games and it 
 * offers methods to query that list
 * @author sebas
 *
 */
public class GameRanking {

    private List<Score> scores = new LinkedList<>();
    private String filePath;

    public GameRanking(String filePath) {
	ArgumentChecks.checkString(filePath);
	this.filePath = filePath;
	scores = new RankingSerializer().deserialize(filePath);
    }

    public GameRanking(List<Score> scores, String filePath) {
	this.scores = scores;
	this.filePath = filePath;
    }

    /**
     * It adds the argument to the end of the list of scores.
     * 
     * @param score
     */
    public void append(Score score) {
	ArgumentChecks.isNotNull(score);
	this.scores.add(score);
    }

    /**
     * It returns a copy of the list of scores.
     * 
     * @return scores
     */
    public List<Score> getGeneral() {
	return new ArrayList<Score>(scores);

    }

    /**
     * Returns a list containing only those scores whose username matches the
     * argument.
     * 
     * @param userName
     * @return scores per username
     */
    public List<Score> getScoresFor(String userName) {
	ArgumentChecks.checkString(userName);
	List<Score> scoresForUsername = new LinkedList<>();
	for (Score score : scores) {
	    if (score.getUserName().compareToIgnoreCase(userName) == 0) {
		scoresForUsername.add(score);
	    }
	}
	return scoresForUsername;
    }

    public String getFilePath() {
	return filePath;
    }

}
