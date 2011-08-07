package zengine.grammar;

import zengine.Noun;
import zengine.Preposition;

/**
* Represents a Flashlight object
*/
public class Flashlight extends Noun
{
	public Flashlight()
	{
		super ("FLASHLIGHT");
		plural = false;
		setState(new Off());
		acceptable.add(zengine.None.class);
	}
        
        public void setState(zengine.Preposition prep)
        {
                if (prep instanceof On)
                        state = prep.toString();
                else if (prep instanceof Off)
                        state = prep.toString();                       
        }
}
