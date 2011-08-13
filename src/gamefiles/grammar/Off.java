package gamefiles.grammar;

import zengine.Preposition;

/**
* represents an off preposition or state
*/
public class Off extends Preposition
{
	public Off()
	{
		super ("OFF");
                acceptable.add(zengine.Noun.class);
	}

}
