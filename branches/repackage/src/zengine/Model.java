package zengine;

import java.util.*;
import zengine.grammar.Hall;
import zengine.grammar.LivingRoom;
import zengine.grammar.Kitchen;
import zengine.grammar.Cellar;
import zengine.grammar.Attic;

import zengine.properties.PropertyLoader;


/**
* The state of the game at any given moment. 
*/
public class Model
{

	private static HashMap<String, Room> rooms;	
	private static String roomPackage = "zengine.grammar";
			
	static
	{
		rooms = new HashMap<String, Room>();
	}
		
	public Model()
	{
		loadActions();
	}

	/**
	*	Initialize rooms semidynamically. Reads rooms from list in
	*	resources.properties. Really dynamic is possible, but would require rooms
	*	in separate package. 
	*/
	private static void loadActions()
	{
		Parser p = new Parser();

		Properties resources = PropertyLoader.getProperties("resources");
		String list = (String)resources.get("rooms");
		for (String s: list.split(","))
		{
			rooms.put(s, makeRoom(s));			
		
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
      System.out.println("called makeRoom: " + name);
      try{
      Class c = Class.forName("zengine.grammar."+name);
      return (Room)c.newInstance();
      }
      catch (Exception e)
      {
         e.printStackTrace();
         return null;
      }
   
	}
	public static Room getRoom(String name)
	{
		return rooms.get(name);
	}


}
