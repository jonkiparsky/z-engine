package zengine;


/**
* Abstract class representing the grammatical class of directions
*/
public abstract class Direction extends Grammar
{
	String s;

	public Direction(String s )
	{
		super();
		this.s=s;

		this.acceptable.add(None.class);
	}
	
	public String toString()
	{
		return s;
	}

}

