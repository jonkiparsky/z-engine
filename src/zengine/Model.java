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
// private static HashMap<String, Item> items;
//	private static HashMap<String, Action> actions;
	
	static
	{
		rooms = new HashMap<String, Room>();
//		items = new HashMap<String, Item>();
//		actions = new HashMap<String, Action> ();
	}
		
	public Model()
	{
		loadActions();
	}

	private static void loadActions()
	{
		rooms.put("Hall", new Hall());
//		System.out.println("Hall" + rooms.get("Hall"));
//		System.out.println("Hall->NORTH" + rooms.get("Hall").getExit("NORTH"));


		rooms.put("LivingRoom", new LivingRoom());
//		System.out.println("LivingRoom" + rooms.get("LivingRoom"));
//		System.out.println("LivingRoom->SOUTH" + rooms.get("LivingRoom").getExit("SOUTH"));
//

		for (String s : rooms.keySet())
		{
			rooms.get(s).setExits();
//			System.out.println(s+ ": "+rooms.get(s));
		}

//		System.out.println("exiting model.loadActions");

//		System.out.println("-------------------");
	}

/*
	private static void loadActions()
	{
		rooms.put("Hall", makeRoom("Hall"));
		rooms.put("LivingRoom", makeRoom("LivingRoom"));

		for (String s : rooms.keySet())
		{
			rooms.get(s).setExits();
			System.out.println(s+ ": "+rooms.get(s));
		}	
	}
*/
		
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
