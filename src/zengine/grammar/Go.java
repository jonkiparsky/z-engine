package zengine.grammar;


import zengine.*;

/**
* Represents a "Go" action
*/
public class Go extends Verb
{
	Direction direction;
        public static String desc;
        
        static
        {
                desc = "- Go\nUsage: Go (DIRECTION)\nExample: Go North\nDescription: Go through the exit to a direction specified";
        }
	
	public Go()
	{
		super("GO");
		acceptable.add( Direction.class);
	}
	public Go(Direction g)
	{
		super("GO");
			this.complements.put(Direction.class.getName(), g);
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
