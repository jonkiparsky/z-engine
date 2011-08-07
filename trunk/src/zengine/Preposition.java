package zengine;

public abstract class Preposition extends Grammar
{
        Noun noun;

	public Preposition(String s)
	{
		super();
		name = s;
	}

	public boolean accept(Grammar g)
	{
                if (g instanceof Noun)
                {
                        noun = (Noun) g;
                        return true;
                }
                return false;
	}

	public String toString()
	{
		return name;
	}



}
