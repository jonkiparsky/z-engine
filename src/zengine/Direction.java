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
	
	public String toString()
	{
		return s;
	}

}

