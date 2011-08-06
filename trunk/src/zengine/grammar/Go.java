package zengine.grammar;


import zengine.*;

/**
* Represents a "Go" action
*/
public class Go extends Verb
{
	Direction direction;
	
	public Go()
	{
		super("GO");
		acceptable.add( Direction.class);
	}
	public Go(Grammar g)
	{

	}
	
	public void execute()
	{

	
		direction = (Direction)complements.get("zengine.Direction");

			if (direction==null)
		{
			System.out.println("I don't know how to do that");
			return;
		}

		
		
		if (ZEngineMain.state.current_loc.getExit(direction) == null)
		{	
			System.out.println("I can't see an exit that way");
		}
		else
		{	
			ZEngineMain.state.go(direction);
		}
	}



}
