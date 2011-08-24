package gamefiles.grammar;

import zengine.Grammar;


import zengine.ZEngineMain;
import zengine.Noun;
import zengine.Verb;

public class Burn extends Verb
{
	public Burn()
	{
		super("BURN");
		acceptable.add(Noun.class);
	}

	public Burn(Grammar g)
	{
	}

	public void execute()
	{
		noun = (Noun)complements.get("zengine.Noun");
		if (noun !=null)
		{
			noun.execute(this);
		}
	}

	public void callback(String s, Noun n)
	{
		System.out.println(s);
		ZEngineMain.state.inventory_destroy(n);
														
	}

}
