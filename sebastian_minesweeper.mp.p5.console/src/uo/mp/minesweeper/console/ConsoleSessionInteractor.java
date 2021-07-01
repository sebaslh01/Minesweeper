package uo.mp.minesweeper.console;


import java.io.FileNotFoundException;


import uo.mp.minesweeper.game.GameLevel;
import uo.mp.minesweeper.ranking.Score;
import uo.mp.minesweeper.ranking.ScoreComparator;
import uo.mp.minesweeper.session.SessionInteractor;
import uo.mp.minesweeper.util.Console;
import uo.mp.minesweeper.util.ScoreFormat;
import uo.mp.minesweeper.util.collections.List;
import uo.mp.minesweeper.util.collections.impl.Collections;
import uo.mp.minesweeper.util.collections.impl.LinkedList;
import uo.mp.minesweeper.util.file.TextFileUtil;

public class ConsoleSessionInteractor implements SessionInteractor {

    @Override
    public String askUserName() {
	showWelcome();
	String userName = this.readUsername();
	if (userName.trim().isEmpty()) {
	    showErrorMessage("username cannot be empty, try again!");
	    askUserName();
	}
	return userName;
    }

    private void showWelcome() {
	
	List<String> lines = new LinkedList<>();
	try {
	    lines = new TextFileUtil().readLines("WELCOMEUSER.txt");
	} catch (FileNotFoundException e) {
	    showErrorMessage("Welcome with style couldn't be shown");
	    return;
	}
	for(String line : lines) {
	    Console.println(line);
	}
	Console.println("Please, log-in in order to acces the game.");
	
    }

    @Override
    public int askNextOption() {
	return readOption();
    }



    @Override
    public GameLevel askGameLevel() {
	switch (this.readLevel()) {
	case 'e':
	case 'E':
	    return GameLevel.EASY;
	case 'm':
	case 'M':
	    return GameLevel.MEDIUM;
	case 'h':
	case 'H':
	    return GameLevel.HIGH;
	default:
	    showErrorMessage(" You've entered the level in an inappropriate format!! Try Again!");
	    return askGameLevel();
	}
    }

    @Override
    public void showPersonalRanking(List<Score> ranking) {
	Collections.sort(ranking, new ScoreComparator());
	String headline = String.format("%1$10s %2$10s %3$10s %4$10s %5$10s", " .Date", " .Hour", " .Level", " .Res",
		" .Time");
	Console.println(headline);
	if(ranking.isEmpty())
	    Console.println("\nWe've got no record of your scores ;( !");
	for (Score score : ranking) {
	    String userData = ScoreFormat.getScoreData(score);
	    Console.println(userData);
	}
    }

    @Override
    public void showRanking(List<Score> ranking) {
	Collections.sort(ranking, new ScoreComparator());
	String headline = String.format("%1$25s %2$10s %3$10s %4$10s %5$10s %6$10s", "User", " .Date", " .Hour",
		" .Level", " .Res", " .Time");
	Console.println(headline);
	for (Score score : ranking) {
	    String userData = ScoreFormat.getScoreCompleteData(score);
	    Console.println(userData);
	}

    }

    @Override
    public void showGoodBye() {
	Console.println("Thanks for your session. Bye, bye!");

    }

    @Override
    public boolean doYouWantToRegisterYourScore() {
	switch (this.userWantsToRegisterScore()) {
	case 'y':
	case 'Y':
	    return true;
	case 'n':
	case 'N':
	    return false;
	default:
	    showErrorMessage("Invalid input!Must be y or n! Try again");
	    return doYouWantToRegisterYourScore();
	}
    }

    private String readUsername() {
	return Console.readString("Player name? ");
    }

    private int readOption()  {
	return Console.readInteger("Option?");
    }

    private char readLevel() {
	return Console.readCharacter("Level? (e)asy, (m)edium, (h)igh ");
    }

    private char userWantsToRegisterScore() {
	return Console.readCharacter("Do you want to store your score? (y)es, (n)o ");
    }

    @Override
    public void showErrorMessage(String message) {
	Console.printError("RECOVERABLE ERROR: " + message);

    }

    @Override
    public void showFatalErrorMessage(String message) {
	Console.printError("FATAL ERROR: " + message);

    }

    @Override
    public String askFileName() {
	String fileName = Console.readString("fileName? ");
	return fileName;
    }
}
