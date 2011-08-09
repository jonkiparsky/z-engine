/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zengine.grammar;

import zengine.Room;

public class Cellar extends Room 
{
        public Cellar()
        {
                super("Cellar");
                description = "You are in a dank cellar.";
        }
        
        public void setExits()
        {
                setExit("UP", Room.getRoom("Kitchen"));
        }
}
