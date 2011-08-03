package zengine.rooms;

import zengine.nouns.Flashlight;
import zengine.Room;

/**
* Represents a hallway, that is a room between rooms
*/
public class Hall extends Room
{
	

   public Hall()
   {
      super ("Hall");
	 	description ="You are in a hallway";
		items.put("FLASHLIGHT", new Flashlight());
   }

	public void setExits()
	{
		setExit("NORTH", Room.getRoom("LivingRoom"));
	}


}

