package gamefiles.grammar;

import zengine.Grammar;


import zengine.ZEngineMain;
import zengine.Noun;
import zengine.Verb;
import zengine.IBurnable;

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
        
        // Alternative to other execute(). n will always be burnable, so no
        // errors for that here. Better way to handle validation of Noun?
        public void execute(IBurnable n)
        {       
                if (n instanceof Noun)
                        callback(n.burnText(), (Noun) n);
                else
                    System.out.println("Burn.execute(IBurnable n) -- n is not a Noun");
        }

	public void callback(String s, Noun n)
	{
		System.out.println(s);
		ZEngineMain.state.inventory_destroy(n);
														
	}

}
