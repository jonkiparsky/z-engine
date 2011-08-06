package zengine;
import zengine.grammar.Go;

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

	public void execute()
	{
		System.out.println("Executing " +this.getClass().getName());
		Go go = new Go((Direction)this);
		go.execute();
	}

}

