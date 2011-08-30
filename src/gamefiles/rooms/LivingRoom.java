package gamefiles.rooms;

import zengine.Room;
import gamefiles.grammar.Banana;

/**
* Represents a living room
*/
public class LivingRoom extends Room
{
        public LivingRoom()
        {
                super("Living Room");
                teleportName = "Livingroom";
		description= "You are in the living room.";
		hiddenObjects.add(new Banana());
        }
	
	public void setExits()
	{
                setExit("SOUTH", Room.getRoom("Hall"));
	}	
}
