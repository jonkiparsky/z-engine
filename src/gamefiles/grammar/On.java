package gamefiles.grammar;

import zengine.*;

public class On extends Preposition
{
	public On()
	{
		super ("ON");
                acceptable.add(zengine.Noun.class);
	}

}
