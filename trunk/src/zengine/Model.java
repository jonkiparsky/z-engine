package zengine;

import java.util.*;
//import zengine.rooms.*;
import zengine.properties.PropertyLoader;
import java.io.File;

/**
* The state of the game at any given moment. 
*/
public class Model
{

	private static HashMap<String, Room> rooms;	
	private static String roomPackage;
        private static String itemPackage;
			
	static
	{
		rooms = new HashMap<String, Room>();
	}
        
        public static void init(String roomPackage, String itemPackage)
        {
                Model.roomPackage = roomPackage + ".";
                Model.itemPackage = itemPackage + "."; 
                loadActions();
        }
	/**
	* 	Load rooms dynamically, by reading contents of "rooms" directory. 
	*	All rooms will be loaded and placed in a hashmap, which can be accessed by
	*	Verb methods through the State class. 
	*/
	private static void loadActions()
	{
		String pathToRooms = "src/externalRoom";

		File roomsDir = new File(pathToRooms);
		
		for (String s: roomsDir.list())
		{	
			if (s.charAt(0) == '.')  continue;  // skip the dotfiles
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
                        Class c = Class.forName(roomPackage + name);
                        return (Room)c.newInstance();
                }
                catch (Exception e)
                {
                        e.printStackTrace();
                        return null;
                }
   
	}
        
        public static void createRoom(String name)
        {
                rooms.put(name, makeRoom(name));
        }
	
        public static Room getRoom(String name)
	{
		return rooms.get(name);
	}
        
        public static String getRoomPackage()
        {
                return roomPackage;
        }
        
        public static String getItemPackage()
        {
                return itemPackage;
        }
}
