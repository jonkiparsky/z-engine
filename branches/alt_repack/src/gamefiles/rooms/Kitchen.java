/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gamefiles.rooms;

import zengine.Room;
import gamefiles.grammar.Knife;

public class Kitchen extends Room 
{
        public Kitchen()
        {
                super("Kitchen");
                description = "You are in a kitchen.";
                longDescription = "You are in a lavish kitchen. There is a rug on the floor, and various extravagant paintings on the walls.";
                items.put("KNIFE", new Knife());    
                //hiddenExits.put("DOWN", Room.getRoom("Cellar"));
        }
        
        public void setExits()
        {
                setExit("NORTH", Room.getRoom("Hall"));
                setExit("UP", Room.getRoom("Attic"));
                hiddenExits.put("DOWN", Room.getRoom("Cellar"));
        }
}
