package zengine.grammar;
import zengine.Direction;

public class South extends Direction
{
	public South()
	{
		super ("SOUTH");
		acceptable.add(zengine.None.class);
	}
}
