
package uo.mp.minesweeper.util;

import java.io.PrintStream;
import java.util.Scanner;

/**
 * Offers utility methods for user-console interaction
 */
public class Console {

    private static PrintStream out = System.out;
    private static PrintStream outErr = System.err;
    private static Scanner keyboard = new Scanner(System.in);

    public static String readString(String msg) {
	out.println(msg);
	keyboard.useDelimiter(System.lineSeparator());
	String res = keyboard.next();
	keyboard.reset();
	return res;
    }

    public static int readInteger(String msg) {
	int integer = -1;
	    do {
		String input  = readString(msg) ;
		try {  
		   integer = Integer.valueOf(input);
		   break;
		} catch (NumberFormatException nfe) {
		   printError("Unable to process input ' " +input+ " ': Try again! " );
		}
	    } while (true);
	return integer;
    }

    public static char readCharacter(String msg) {
	String charRead = "";
	do {
	    charRead = readString(msg);
	    if(charRead.length()>1)
		printError("Unable to process input ' " +charRead+ " ': Try again! ");
	}while(charRead.length()>1);
	return  charRead.charAt(0);

    }

    public static void println(String msg) {
	out.println(msg);
    }

    public static void printError(String msg) {
	outErr.println(msg);
    }

    public static void printf(String fmt, Object... params) {
	out.printf(fmt, params);
    }

}
