package zengine;

public abstract class Noun extends Grammar
{
	protected String desc;
	protected String state;
	protected boolean plural;
        protected Preposition prep;

	public Noun(String name)
	{
		super();
		this.name = name;
                acceptable.add(Preposition.class);
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
        
        
        public void setState(Preposition prep)
        {
                System.out.println("You can't set " + name + " status to " + prep.name);
        }
	
	public String toString()
	{
		return name;
	}

	public String itemDescription()
	{
		if (state != null)
			desc = "The "+ name + " is " + state;
	
		return (desc);
	}


}
