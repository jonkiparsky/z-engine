package zengine;

public abstract class Preposition extends Grammar
{
	String s;


	public Preposition(String s)
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
