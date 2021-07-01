package uo.mp.minesweeper.util;

import java.text.SimpleDateFormat;

import uo.mp.minesweeper.ranking.Score;

public class ScoreFormat {
    private static final int ELAPSED_TIME = 5;
    private static final int RESULT = 4;
    private static final int LEVEL = 3;
    private static final int HOUR = 2;
    private static final int DATE = 1;
    private static final int NAME = 0;
    
    private static final String DEFAULT_FORMAT = "%1$10s %2$10s %3$10s %4$10s %5$10s";
    private static final String DEFAULT_FORMAT_NAME = "%1$25s";
    
    private static String [] SCOREFIELDS = new String [6] ;
    
    public static String getScoreData(Score score) {
	fillScoreFields(score);
 	
 	String userData = String.format(DEFAULT_FORMAT,SCOREFIELDS[DATE],
     		SCOREFIELDS[HOUR], SCOREFIELDS[LEVEL],
     		SCOREFIELDS[4], SCOREFIELDS[5]);
 	return userData;
     }
    
    public static String getScoreCompleteData(Score score){
	fillScoreFields(score);
	 String userData = String.format(DEFAULT_FORMAT_NAME, SCOREFIELDS[NAME]);
	 userData += getScoreData(score);
	 return userData;
    }
    
    public static String getScoreCompleteData(Score score, String format) {
	fillScoreFields(score);
	return String.format(format, SCOREFIELDS[NAME], SCOREFIELDS[DATE], SCOREFIELDS[HOUR], SCOREFIELDS[LEVEL],
		SCOREFIELDS[RESULT], SCOREFIELDS[ELAPSED_TIME]);
		
    }
    
    private static void fillScoreFields(Score score) {
	SCOREFIELDS[NAME] = score.getUserName();
	SCOREFIELDS[DATE] = getDate(score);
	SCOREFIELDS[HOUR] = getHour(score);
	SCOREFIELDS[LEVEL] =score.getLevel() + "";
	SCOREFIELDS[RESULT] = getResult(score);
	SCOREFIELDS[ELAPSED_TIME] = score.getTime() + "";
    }
    private static String getDate(Score score) {
  	return new SimpleDateFormat("dd/MM/yy").format(score.getDate());
      }

     private static String getHour(Score score) {
  	return new SimpleDateFormat("HH:mm:ss").format(score.getDate());
      }

      private static String getResult(Score score) {
  	if (score.hasWon())
  	    return "won";
  	return "lost";
      }

}
