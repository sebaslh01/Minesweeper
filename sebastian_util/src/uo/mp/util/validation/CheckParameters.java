package uo.mp.util.validation;

public class CheckParameters {
	/**
	 * Checks if an integer parameter of a method is valid or not. Throws an
	 * exception when not.
	 * 
	 * @param int arg
	 * @param String message
	 */
	public static void checkNumber(int arg, String message) {
		if (arg <= 0)
			throw new IllegalArgumentException(message);
	}
	/**
	 * Checks if an integer parameter of a method is valid or not. Throws an
	 * exception when not.
	 * 
	 * @param int x
	 * @param int y
	 * @param String message
	 */
	public static void checkCoord(int x,int y, int length, String message ) {
		if (y < 0 || x <0 || y>=length || x>=length)
			throw new IllegalArgumentException(message);
	}
	/*
	 * Checks whether an String is different from null or if is not made up of all
	 * spaces. Throws an exception when it is null or all spaces.
	 * 
	 * @param String arg
	 * @param String message
	 */
	public static void checkString(String arg, String message) {
		if (arg == null || arg.trim().isEmpty())
			throw new IllegalArgumentException(message);
	}

	/**
	 * Validates whether an condition is false or not. If the given condition is
	 * false, throws an exception whose message is the given expression as
	 * parameter.
	 * 
	 * @param boolean condition
	 * @param String expression
	 */
	public static void checkArgument(boolean condition, String expression) {
		if (!condition)
			throw new IllegalArgumentException(expression);

	}
	
	
}
