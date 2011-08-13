package zengine.grammar;
import zengine.Direction;

public class Southeast extends Direction
{
	public Southeast()
	{
		super ("SOUTHEAST");
		acceptable.add(None.class);
	}
}
