package gamefiles.grammar;
import zengine.Direction;

public class South extends Direction
{
	public South()
	{
		super ("SOUTH");
		acceptable.add(None.class);
	}
}
