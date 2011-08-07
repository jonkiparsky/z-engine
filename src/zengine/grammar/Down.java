package zengine.grammar;

import zengine.Direction;

public class Down extends Direction
{
	public Down()
	{
		super ("DOWN");
		acceptable.add(zengine.None.class);
	}


	
}
