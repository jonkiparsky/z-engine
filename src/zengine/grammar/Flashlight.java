package zengine.grammar;

import zengine.Noun;

/**
* Represents a Flashlight object
*/
public class Flashlight extends Noun
{
	public Flashlight()
	{
		super ("FLASHLIGHT");
		plural = false;
		state = "OFF";
		//acceptable.add(None.class);
	}	
}
