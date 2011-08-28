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


	// debugging file references
	public static void printDirectoryListings()
	{
		System.out.println("showing directory listings");
		File f = new File (".");
		System.out.println("Root directory: " +getLocalRoot());
		System.out.println("getLocalRoot function is " +
				(f.getAbsolutePath().equals(getLocalRoot())? "indeed" : "not") + 
				" correct");
		System.out.println("Rooms directory: " +getRoomsDir().getAbsolutePath());
		System.out.println("Props directory: " +getPropsPath());
		recurse(f, 0);	
	}
	
	private static void recurse(File file, int n)
	{
		for (File f: file.listFiles())
		{
		if(f.getName().charAt(0)=='.')
			continue;
	 	printFileName(f, n);
		if (f.isDirectory())
			recurse(f, n+1);
		}
	}

	private static void printFileName(File f, int n)
	{
		for (int i = 0; i <n; i++)
			System.out.print("\t");
		System.out.println(f.getName());
		
	}

}
