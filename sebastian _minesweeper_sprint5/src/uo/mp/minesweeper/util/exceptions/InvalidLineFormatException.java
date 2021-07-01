package uo.mp.minesweeper.util.exceptions;

public class InvalidLineFormatException extends Exception {
	private String message;
	private static final long serialVersionUID = 1L;
	public InvalidLineFormatException() {
		super();
		this.message = "InvalidLineFormatException";
	}
	/**
	 * @param message
	 */
	public InvalidLineFormatException(String message) {
		super();
		this.message = message;
	}
	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}


}
