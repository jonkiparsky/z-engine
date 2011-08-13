package zengine;
import zengine.grammar.Go;
import zengine.grammar.None;

/**
* Abstract class representing the grammatical class of directions
*/
public abstract class Direction extends Grammar
{
	String name;

	public Direction(String name)
	{
		super();
		this.name=name;

		this.acceptable.add(None.class);
	}
	
	public String toString()
	{
		return name;
	}

	
	/**
	* A user may enter a direction name to go in that direction; this execute()
	* method allows that functionality.
	*/
	public void execute()
	{
		System.out.println("Executing " +this.getClass().getName());
		Go go = new Go((Direction)this);
		go.execute();
	}

}

