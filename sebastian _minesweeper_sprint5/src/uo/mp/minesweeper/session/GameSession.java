package uo.mp.minesweeper.session;

import java.io.FileNotFoundException;


import uo.mp.minesweeper.game.Board;
import uo.mp.minesweeper.game.Game;
import uo.mp.minesweeper.game.GameInteractor;
import uo.mp.minesweeper.game.GameLevel;
import uo.mp.minesweeper.ranking.GameRanking;
import uo.mp.minesweeper.ranking.RankingParser;
import uo.mp.minesweeper.ranking.RankingSerializer;
import uo.mp.minesweeper.ranking.Score;
import uo.mp.minesweeper.util.collections.List;
import uo.mp.minesweeper.util.collections.impl.ArrayList;
import uo.mp.minesweeper.util.file.TextFileUtil;
import uo.mp.minesweeper.util.log.SimpleLogger;

public class GameSession {
    private static final int EXIT = 0;

    private String userName;
    private Game game;
    private Menu menu = new Menu();
    private SessionInteractor session;
    private GameInteractor gameInteractor;
    private SimpleLogger logger;
    private GameRanking ranking;
    private GameLevel level;

    /**
     * It sets the SessionInteractor field in the GameSession to the argument
     * 
     * @param interactor
     */
    public void setSessionInteractor(SessionInteractor interactor) {
	this.session = interactor;

    }

    /**
     * It sets the GameInteractor field in the GameSession to the argument.
     * 
     * @param interactor
     */
    public void setGameInteractor(GameInteractor interactor) {
	this.gameInteractor = interactor;
    }

    /**
     * It sets the SimpleLogger field in the GameSession to the argument.
     * 
     * @param logger
     */
    public void setLogger(SimpleLogger logger) {
	this.logger = logger;

    }

    /**
     * It sets the GameRanking field in the GameSession to the argument.
     * 
     * @param gameRanking
     */
    public void setGameRanking(GameRanking gameRanking) {
	this.ranking = gameRanking;

    }

    /**
     * It launches the GameSession logic.
     */
    public void run() {
	int option = EXIT;
	this.userName = this.session.askUserName();
	do {
	   
	    menu.showOptions();
	    try {
		option = this.session.askNextOption();
		processOption(option);
		safetyRankingCopy();
	    } catch (FileNotFoundException fne) {
		handleFileNotFoundException(fne);
	    } catch (RuntimeException rte) {
		handleRuntimeException(rte);
		return;
	    } catch (Exception e) {
		handleException(e);
	    }
	} while (option != EXIT);
	this.session.showGoodBye();

    }

    private void handleFileNotFoundException(FileNotFoundException fne) {
	this.session.showErrorMessage("The file you specified is unreachable and the import was discarded.");

    }

    private void handleException(Exception e) {
	this.session.showFatalErrorMessage("This error should've never happened");
	this.logger.log(e);
	return;
    }

    private void handleRuntimeException(RuntimeException rte) {
	String message = rte.getMessage() != null ? rte.getMessage() : "Try again, restart the program!!";
	this.session.showFatalErrorMessage(message);
	this.logger.log(rte);

    }

    private void processOption(int option) throws FileNotFoundException {
	switch (option) {
	case EXIT:
	    return;
	case 1:
	    playNewGame();
	    break;
	case 2:
	    showCurrentUserResults();
	    break;
	case 3:
	    showAllResults();
	    break;
	case 4:
	    exportResults();
	    break;
	case 5:
	    importResults();
	    break;
	default:
	    this.session.showErrorMessage("Invalid option, try again!");
	}

    }

    private void playNewGame() {
	setUpGame();
	this.game.play();
	boolean storeResult = this.session.doYouWantToRegisterYourScore();
	if (storeResult)
	    this.storeResult();
    }

    private void storeResult() {
	Score score = new Score(userName, this.level, game.getTime(), game.didUserWon());
	this.ranking.append(score);
    }

    private void safetyRankingCopy() {
	new RankingSerializer().serialize(ranking.getFilePath(), ranking.getGeneral());
    }

    private void showCurrentUserResults() {
	this.session.showPersonalRanking(this.ranking.getScoresFor(this.userName));

    }

    private void showAllResults() {
	this.session.showRanking(this.ranking.getGeneral());
    }

    private void exportResults() {
	String fileName = this.session.askFileName();
	new TextFileUtil().writeLines(fileName, new RankingSerializer().serialize(ranking.getGeneral()));

    }

    private void importResults() throws FileNotFoundException {
	String fileName = this.session.askFileName();
	List<String> lines = new ArrayList<String>();
	lines = new TextFileUtil().readLines(fileName);
	List<Score> scores = new RankingParser(this.logger).parse(lines);
	String filePath = this.ranking.getFilePath();
	setGameRanking(new GameRanking(scores, filePath));

    }

    private void setUpGame() {
	this.level = this.session.askGameLevel();
	this.configure(level.getWidth(), level.getHeight(), level.getPercentageBombs());
    }

    private void configure(int width, int height, int percentageBombs) {
	Board board = new Board(width, height, percentageBombs);
	this.game = new Game(board);
	this.game.setInteractor(this.gameInteractor);
    }

}
