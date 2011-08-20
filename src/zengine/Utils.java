package zengine;

/**
* A collection of useful methods 
*/
public class Utils
{
	private static enum DebugLevel
	{

		// working on this - set log levels from within the program, get debug
		// info directly to the console while testing, or whatever
		NIL, LOG_SIMPLE, LOG_COMPLETE, CONSOLE
	}

	/**
	* Print a debug message to a destination. Currently, print to screen.
	*/
	protected static void debug(Object o, String s)
	{
		System.out.println(o.toString()+": "+s);
	}
}
