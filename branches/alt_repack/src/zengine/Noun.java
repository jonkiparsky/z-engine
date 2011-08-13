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


	/**
	* Override this when an object is emitting light. This should be true only
	* when the object is emitting light: a flashlight is not a light source when
	* it is turned off.
	*/
	public boolean isLightSource()
	{
		return false;
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
