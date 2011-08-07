package zengine;

public abstract class Verb extends Grammar
{
	
	protected Noun noun = null;	
	protected PPhrase prepPhrase = null;
	protected Direction dir = null;

	public Verb()
	{
		super();
	}


	public Verb(String name)
	{
		this.name = name;
	}
	
	public Verb(Grammar g)
	{
	}
	
	public void execute()
	{
		System.out.println("Don't know how to do "+name);
	}
        
        public void execute(Preposition prep)
        {
            
        }
	
	public String toString()
	{
		return (name);
	}


}

