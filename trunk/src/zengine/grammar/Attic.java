/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zengine.grammar;

import zengine.Room;

public class Attic extends Room 
{
        public Attic()
        {
                super("Attic");
                description = "You are in a dusty attic.";
        }
        
        public void setExits()
        {
                setExit("DOWN", Room.getRoom("Kitchen"));
        }
}
