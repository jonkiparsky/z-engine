package zengine;

public abstract class Preposition extends Grammar
{
        Noun noun;

	public Preposition(String name)
	{
//		super(name);
		this.name = name;
	}
	public String toString()
	{
		return name;
	}

}
