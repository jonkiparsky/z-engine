
package zengine.rooms;


import zengine.Room;
import zengine.grammar.Banana;

/**
* Represents a living room
*/
public class LivingRoom extends Room
{
   public LivingRoom()
   {
      super("Living Room");
		description= "You are in the living room.";
		hiddenObjects.add(new Banana());
   }
	
	public void setExits()
	{
      setExit("SOUTH", Room.getRoom("Hall"));

	}
	
}
