
package zengine.grammar;


import zengine.Room;


/**
* Represents a living room
*/
public class LivingRoom extends Room
{
   public LivingRoom()
   {
      super("Living Room");
		description= "You are in the living room.";
   }
	
	public void setExits()
	{
      setExit("SOUTH", Room.getRoom("Hall"));

	}
	
}
