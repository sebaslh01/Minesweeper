package uo.mp.minesweeper.session;

import uo.mp.minesweeper.util.Console;

/**
 * Contains the menu for the game
 * 
 * @author sebas
 *
 */
public class Menu {
    private String[] options = { "Play a new game", 
	    			"Show my results", 
	    			"Show all results",
	    			"Export results",
	    			"Import results"

    };

    public void showOptions() {
	int i = 1;
	Console.println("Avalaible options");
	for (String line : options) {
	    if ("".equals(line)) {
		Console.println("");
		continue;
	    }

	    Console.printf("\t%2d- %s\n", i++, line);
	}
	Console.printf("\t%2d- %s\n", 0, "Exit");
    }

}
