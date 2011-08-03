package zengine;


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
		acceptable = Direction.class;
	}
	public Go(Grammar g)
	{

	}
	
	public void execute()
	{
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

	public boolean accept(Grammar g)
	{
		if (!super.accept(g))
		{
			return false;
		}
			this.direction=(Direction) g;
			return true;
	
	}

// below are to be deleted
/*	public Go(Direction d)
	{
		super ("GO");
		this.direction = d;
	}
*/
/*	public Go(Grammar g)
	{
		this.direction=null;
	}

*/

/*	public void put(Direction token)
	{
		System.out.println("Called into Go!");	
			this.direction = token;
	}
*/


}
