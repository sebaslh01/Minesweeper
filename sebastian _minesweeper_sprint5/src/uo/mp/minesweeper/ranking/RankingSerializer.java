package uo.mp.minesweeper.ranking;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;


import uo.mp.minesweeper.util.ScoreFormat;
import uo.mp.minesweeper.util.collections.List;
import uo.mp.minesweeper.util.collections.impl.LinkedList;

/**
 * Helps the serialization/deserialization of objects of type Score.
 * @author sebas
 *
 */
public class RankingSerializer {
    
    public void serialize(String fileName, List<Score> scores) {
	try {
	    FileOutputStream fileOut = new FileOutputStream(fileName);
	    ObjectOutputStream out = new ObjectOutputStream(fileOut);
	    out.writeObject(scores);
	    out.close();
	    fileOut.close();
	} catch (IOException ioe) {
	    throw new RuntimeException("Something went wrong with serialization persistent rank copy");
	}

    }

    public List<String> serialize(List<Score> scores) {
	List<String> rankingSerialized = new LinkedList<String>();
	if (scores == null)
	    return rankingSerialized;
	for (Score score : scores) {
	    rankingSerialized.add(serialize(score));
	}
	return rankingSerialized;
    }

    @SuppressWarnings("unchecked")
    public List<Score> deserialize(String fileName) {
	List<Score> scores = new LinkedList<>();
	try {
	    ObjectInputStream in = new ObjectInputStream(new FileInputStream(fileName));
	    scores = (List<Score>) in.readObject();
	    in.close();
	} catch (FileNotFoundException e) {

	} catch (Exception e) {
	    throw new RuntimeException("Something went wrong with deserialization of persistent rank copy");
	}
	return scores;
    }

    private String serialize(Score score) {
	String userData = ScoreFormat.getScoreCompleteData(score, "%1$s;%2$s;%3$s;%4$s;%5$s;%6$s");
	return userData;
    }

}
