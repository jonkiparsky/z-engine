/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zengine.grammar;

import zengine.Room;

public class Kitchen extends Room 
{
        public Kitchen()
        {
                super("Kitchen");
                description = "You are in a kitchen.";
                items.put("KNIFE", new Knife());    
					hiddenExits.put("DOWN", Room.getRoom("Cellar"));
        }
        
        public void setExits()
        {
                setExit("NORTH", Room.getRoom("Hall"));
                setExit("UP", Room.getRoom("Attic"));
        }
}
