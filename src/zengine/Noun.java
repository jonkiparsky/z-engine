package zengine;

public abstract class Noun extends Grammar
{
	protected String s;
        protected String desc;
	protected String state;
	protected boolean plural;

	public Noun(String s)
	{
		super();
		this.s=s;
                desc = "";
                //acceptable.add(None.class);
	}

	public boolean plural()
	{
		return plural;
	}

	public boolean accept(Grammar g)
	{
                /*for (Class c : acceptable)
                {
                        if (g.getClass() == c)
                            return true;
                }
                return false;*/
                if (super.accept(g))
                    return true;
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
			desc+="The "+ s+ " is " + state;
	
		return (desc);
	}


}
