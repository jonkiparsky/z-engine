package zengine.grammar;
import zengine.Direction;

public class Southwest extends Direction
{
	public Southwest()
	{
		super ("SOUTHWEST");
		acceptable.add(None.class);
	}
}
