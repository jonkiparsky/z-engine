package gamefiles.rooms;

import zengine.Room;
import gamefiles.grammar.Banana;
import gamefiles.grammar.Switch;

/**
* Represents a living room
*/
public class LivingRoom extends Room
{
        public LivingRoom()
        {
                super("Living Room");
                teleportName = "living room";
		description= "You are in the living room.";
		hiddenObjects.add(new Banana());
        }
	
	public void setExits()
	{
                setExit("SOUTH", Room.getRoom("Hall"));
	}	
}
