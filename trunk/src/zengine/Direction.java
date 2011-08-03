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
	}
	
	public boolean accept(Grammar g)
	{
		return false;
	}

	public String toString()
	{
		return s;
	}

}

