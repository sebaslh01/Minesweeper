package uo.mp.minesweeper.util;

public class ArgumentChecks {
	/**
	 * Evaluates condition. If false, throw IllegalArgumentException with message
	 * 
	 * @param boolean condition
	 * @param String expression
	 */
	public static void isTrue(boolean condition, String expression) {
		if (!condition)
			throw new IllegalArgumentException(expression);

	}
	
	/**
	 * Evaluates condition. If false, throw IllegalArgumentException.
	 * 
	 * @param boolean condition
	 */
	public static void isTrue(boolean condition) {
		if (!condition)
			throw new IllegalArgumentException();

	}
	
	public static void isFalse(boolean arg, String expression) {
		isTrue(!arg, expression);
	}
	/**
	 * Checks if an integer parameter of a method is valid or not. Throws an
	 * exception when not.
	 * 
	 * @param int arg
	 * @param String message
	 */
	public static void checkNumber(int arg, String message) {
		isFalse(arg<=0, message);
	}
	/**
	 * Checks if an integer parameter of a method is valid or not. Throws an
	 * exception when not.
	 * 
	 * @param int x
	 * @param int y
	 * @param String message
	 */
	public static void checkCoord(int x,int y, int height, int width, String message ) {
		isFalse(y < 0 || x <0 || y>=height || x>=width, message);
	}
	/**
	 * Checks whether an String is different from null or if is not made up of all
	 * spaces. Throws an exception when it is null or all spaces.
	 * 
	 * @param String arg
	 * @param String message
	 */
	public static void checkString(String arg) {
		isFalse(arg.trim().isEmpty(), arg + " cannot be empty/null");
	}
	
	/**
	 * Checks if an object is different from null
	 * @param obj
	 */
	public static void isNotNull(Object obj) {
		isTrue(obj!=null, obj +" cannot be null");
	}

	/**
	 * Evaluates condition. If false, throw IndexOutOufBoundsException with message
	 * 
	 * @param boolean condition
	 * @param String expression
	 */
	public static void checkBounds(boolean condition, String expression) {
		if (!condition)
			throw new IndexOutOfBoundsException(expression);

	}

}
