package gamefiles.grammar;

import zengine.Grammar;

/**
* represents no Grammar item: end of input
*/
public class None extends Grammar
{
	
	public None()
	{
		super ("NONE");
		this.name="NONE";
	}

	public String toString()
	{	
		return name;
	}
}
