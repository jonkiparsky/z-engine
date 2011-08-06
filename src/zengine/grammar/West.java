package zengine.grammar;

import zengine.Direction;
public class West extends Direction
{
	public West()
	{
		super ("WEST");
		acceptable.add(zengine.None.class);
	}
}
