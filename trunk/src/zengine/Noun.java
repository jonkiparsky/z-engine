package zengine;

public abstract class Noun extends Grammar
{
	protected String desc;
	protected String state;
	protected boolean plural;

	public Noun(String name)
	{
		super();
		this.name = s;
		desc = "";
	}

	public boolean plural()
	{
		return plural;
	}

	public String getState()
	{
		return state;
	}
	
	public String toString()
	{
		return name;
	}

	public String itemDescription()
	{
		if (state != null)
			desc+="The "+ name + " is " + state;
	
		return (desc);
	}


}
