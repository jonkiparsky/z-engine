package zengine;

import java.util.*;
import zengine.grammar.Hall;
import zengine.grammar.LivingRoom;
/**
* The state of the game as it stands
*/
public class Model
{

	private static HashMap<String, Room> rooms;	
	
	static
	{
		rooms = new HashMap<String, Room>();
	}
		
	public Model()
	{
		loadActions();
	}

	private static void loadActions()
	{
		rooms.put("Hall", new Hall());


		rooms.put("LivingRoom", new LivingRoom());

		for (String s : rooms.keySet())
		{
			rooms.get(s).setExits();
		}
	}

   private static Room makeRoom(String name)
   {
      System.out.println("called makeRoom: " + name);
      try{
      Class c = Class.forName(name);
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
