package uo.mp.minesweeper.session;



import uo.mp.minesweeper.game.GameLevel;
import uo.mp.minesweeper.ranking.Score;
import uo.mp.minesweeper.util.collections.List;

public interface SessionInteractor {
	/**
	 * Prompts the user for a name and returns a String with the answer, 
	 * which cannot be neither null nor empty.
	 * @return String username
	 */
    String askUserName();
	
	/**
	 * It asks the user to choose an option from the menu. It returns an integer 
	 * representing the selection. A value greater than zero will represent some 
	 * of the available actions. A zero value will always represent the exit option.
	 * @return int option
	 */
	int askNextOption();
	
	/**
	 * It asks the user for a level of difficulty and returns the answer with a GameLevel object.
	 * @return
	 */
	GameLevel askGameLevel();
	
	/**
	 * It receives a list of Score objects representing all the scores recorded in the system and 
	 * displays all the information about them all (tabular format, one line for each score) 
	 * except username (it is understood that it is the user stored in the session).
	 * @param ranking
	 */
	void showPersonalRanking(List<Score> ranking);
	/**
	 * It receives a list of Score objects representing all the scores recorded in the system 
	 * and displays all the information about them all (tabular format, one line for each score).
	 * @param ranking
	 */
	void showRanking(List<Score> ranking);
	
	/**
	 * It displays a farewell message when user chooses to end the session.
	 */
	void showGoodBye();
	
	/**
	 * At the end of a game, it asks the user if he wants to save his score. 
	 * Returns true if the answer is affirmative and false otherwise.
	 * @return
	 */
	boolean doYouWantToRegisterYourScore();
	/**
	 * It displays an error message, received as a parameter. 
	 * This kind of errors don’t stop execution (recoverable errors).
	 * @param message
	 */
	void showErrorMessage(String message);
	/**
	 * It displays serious error messages to the user. This method has
	 *  to be used to report that the error is unrecoverable and the program 
	 *  will end its execution immediately.
	 * @param message
	 */
	void showFatalErrorMessage(String message);
	
	/**
	 * Ask the user to type path to the file. Must return a non-empty String.
	 */
	String askFileName();
	
}
	
