package zengine;

import java.io.File;

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

		// disable output for now
//		System.out.println(o.toString()+": "+s);
	}

	public static String getLocalRoot()
	{
		File file = new File(".");
		return file.getAbsolutePath();
	}

	public static File getRoomsDir()
	{
		String sep = System.getProperty("file.separator");
		String roomsPath = getLocalRoot()+ sep + "src" + sep + 
				"gamefiles" + sep + "rooms";
		return new File(roomsPath);
	}

	public static String getPropsPath()
	{
		String sep = System.getProperty("file.separator");
		String propsPath = getLocalRoot()+ sep + "src" + sep + 
				"gamefiles" + sep + "props"+sep;
		return propsPath;
	}


}
