package zengine.grammar;

import zengine.Direction;

public class North extends Direction
{
	public North()
	{
		super ("NORTH");
		acceptable.add(zengine.None.class);
	}


	
}
