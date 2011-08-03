package zengine;


class PPhrase extends Grammar
{
	String s;
	public PPhrase(String s)
	{
		super();
		this.s=s;
	}
	public String toString()
	{
		return ("PPHRASE: "+s);
	}

	public boolean accept(Grammar g)
	{
		return true;
	}

}
