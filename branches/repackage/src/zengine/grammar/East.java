package zengine.grammar;
import zengine.Direction;

/**
* Represents the direction "east"
*/
public class East extends Direction
{
	public East()
	{
		super ("EAST");
		acceptable.add(zengine.None.class);
	}
}
