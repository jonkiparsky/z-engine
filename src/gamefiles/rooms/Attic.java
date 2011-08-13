/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zengine.rooms;

import zengine.Room;

public class Attic extends Room 
{
        public Attic()
        {
                super("Attic");
                description = "You are in a dusty attic.";
				isDark=true;

        }

        
        public void setExits()
        {
                setExit("DOWN", Room.getRoom("Kitchen"));
        }
}
