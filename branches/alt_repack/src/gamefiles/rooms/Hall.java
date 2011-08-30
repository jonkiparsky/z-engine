package gamefiles.rooms;

import gamefiles.grammar.Tract;
import gamefiles.grammar.Flashlight;
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
                longDescription = "You are in a long, narrow hallway. There are ornate windows covering the walls, with armor statues between each.";
		items.addItem(new Tract());
		items.addItem(new Flashlight());
        }

	public void setExits()
	{
		setExit("NORTH", Room.getRoom("Living Room"));
                setExit("SOUTH", Room.getRoom("Kitchen"));
	}


}

