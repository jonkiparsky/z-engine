package zengine;

import java.util.*;
import gamefiles.rooms.*;
import java.io.File;

/**
* The state of the game at any given moment. 
*/
public class Model
{

	private static HashMap<String, Room> rooms;	
	private static String roomPackage = "gamefiles.rooms";
                //"c:/users/dan/my documents/netbeansprojects/altZengine/alt_repack/src/gamesfiles.rooms";
			
	static
	{
		rooms = new HashMap<String, Room>();
	}
	
        /**
         * Creates a new model. Loads rooms from the gamefiles/rooms directory.
         */
	public Model()
	{
		loadActions();
	}

	/**
	* 	Load rooms dynamically, by reading contents of "rooms" directory. 
	*	All rooms will be loaded and placed in a hashmap, which can be accessed by
	*	Verb methods through the State class. 
	*/
	private static void loadActions()
	{
		File roomsDir = Utils.getRoomsDir();
		
		for (String s: roomsDir.list())
		{	
			if (s.charAt(0) == '.')  continue;  // skip the dotfiles
			if (! s.contains("java")) continue;	// skip non-java files
							// this will be an error if we decide to accept clojure
							// or jython or other jvm-targeting languages...

			String name = s.split("\\.")[0];
		
			rooms.put(name, makeRoom(name));

		}

                for (String s : rooms.keySet())
		{
			rooms.get(s).setExits();
		}
	
	}
	

	/**
	* Load a room given its class name
	*/
        private static Room makeRoom(String name)
        {
                try
                {
                        Class c = Class.forName("gamefiles.rooms."+name);
                        return (Room)c.newInstance();
                }
                catch (Exception e)
                {
                        e.printStackTrace();
                        return null;
                }
	}
        
        /**
         * Returns a room given its name.
         */
	public static Room getRoom(String name)
	{
		return rooms.get(name);
	}
}