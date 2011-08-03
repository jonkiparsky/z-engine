package zengine;

public abstract class Noun extends Grammar
{
	protected String s;
	protected String state;
	protected boolean plural;

	public Noun(String s)
	{
		super();
		this.s=s;
	}

	public boolean plural()
	{
		return plural;
	}

	public boolean accept(Grammar g)
	{
		return false;
	}

	public String getState()
	{
		return state;
	}
	
	public String toString()
	{
		return s;
	}

	public String itemDescription()
	{
		String itemDescription = s;
		if (state != null)
			s+="\nThe "+ s+ " is " + state;
	
		return (s);
	}


}
