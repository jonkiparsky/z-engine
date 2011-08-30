package gamefiles.grammar;

import zengine.Verb;
import zengine.ZEngineMain;
import zengine.Room;

public class Teleport extends Verb 
{
        private Room teleportRoom;
        
        public Teleport()
        {
                super("TELEPORT");
                acceptable.add(Room.class);
        }
        
        public Teleport(Room room)
        {
                super("TELEPORT");
                this.complements.put(Room.class.getName(), room);
        }
        
        public void execute()
        {
                teleportRoom = (Room)complements.get(Room.class.getName());
                
                if (teleportRoom == null)
                {
                        System.out.println("Teleport where?");
                        return;
                }
                
                ZEngineMain.state.moveToRoom(teleportRoom.teleportRoom());
                ZEngineMain.state.currentRoom().enter();
                ZEngineMain.state.look();
        }
}
