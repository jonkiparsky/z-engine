package externalRoom;

import zengine.Room;

public class Garden extends Room
{
        public Garden()
        {
                super("GARDEN");
                description = "You are in a wonderful garden.";
        }
        
        public void setExits()
        {
                setExit("WEST", Room.getRoom("Kitchen"));
        }
}
