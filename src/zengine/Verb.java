package zengine;

public abstract class Verb extends Grammar
{
	String s;
	
	protected Noun noun = null;	
	protected PPhrase prepPhrase = null;
	protected Direction dir = null;

	public Verb()
	{
		super();
	}


	public Verb(String s)
	{
		this.s = s;
	}
	
	public Verb(Grammar g)
	{
	}
	
	public void execute()
	{
		System.out.println("Don't know how to do "+s);
	}
	
	public String toString()
	{
		return (s);
	}


}

