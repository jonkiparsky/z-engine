package zengine;

public abstract class Preposition extends Grammar
{
        Noun noun;

	public Preposition(String name)
	{
		super(name);
	}

	public boolean accept(Grammar g)
	{
                if (super.accept(g))
                {
                        if (g instanceof Noun)
                                noun = (Noun) g;
                        return true;
                }
                return false;
	}

}
