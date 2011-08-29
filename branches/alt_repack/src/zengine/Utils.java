package zengine;

import java.io.File;

/**
* A collection of useful methods 
*/
public class Utils
{
	private static File gameFilesLocation;
	private static File engineFilesLocation;
	
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

        /**
         * Returns the users local root path.
         */
	public static String getLocalRoot()
	{
		File file = new File(".");
		return file.getAbsolutePath();
	}
        
        /**
         * Returns the file path for gamefiles.rooms.
         */
	public static File getRoomsDir()
	{
		
		System.out.println(gameFilesLocation.getAbsolutePath());
		
		String sep = System.getProperty("file.separator");
		String roomsPath = gameFilesLocation.getAbsolutePath() + sep + "rooms";
		return new File(roomsPath);
	}

        /**
         * Returns the file path for gamefiles.props.
         * @return 
         */
	public static String getPropsPath()
	{
		
		String sep = System.getProperty("file.separator");
		String propsPath = gameFilesLocation.getAbsolutePath() + sep 
				+ "props" + sep;
	
		System.out.println("propspath = " +propsPath);
		return propsPath;
	}


	/**
         * Used for debugging. Prints various file paths.
         */
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


	public static void seekLocalRoots()
	{
		File file = new File (".");
		gameFilesLocation = seekSource(file, "gamefiles");
		
		engineFilesLocation = seekSource(file, "zengine");
	
	
		if (gameFilesLocation ==null)
		{
			System.out.println("can't find gamefiles. exiting");
			System.exit(0);
		}
		if (engineFilesLocation ==null)
		{
			System.out.println("can't find enginefiles. exiting");
			System.exit(0);
		}
	}

	private static File seekSource(File file, String seekName)
	{
	//	System.out.println("seeking: "+file.getAbsolutePath());
		LOOP: for (File f: file.listFiles())
		{
			if(f.getName().charAt(0)=='.')
				continue LOOP;
			if (f.getName().equals("bin"))
				continue;
			if (f.getName().equals("build")) 
				continue;
	//		System.out.println("f.getName(): "+f.getName());
	//		System.out.println("seekName = " +seekName);
			if (f.getName().trim().equals(seekName.trim()))
			{
				System.out.println("Found "+seekName);
				return f;
			}
			if (f.isDirectory())
			{
	//			System.out.println("seeking in "+f.getName());
				File returnFile = seekSource(f, seekName);
				if (returnFile != null)
					return(seekSource(f, seekName));
			}
		}
	//	System.out.println ("can't find: "+seekName);
		return null;
	}
}
