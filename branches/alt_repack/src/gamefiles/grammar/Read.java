package gamefiles.grammar;

import zengine.Grammar;
import zengine.Noun;
import zengine.ZEngineMain;
import zengine.Verb;

public class Read extends Verb
{
	public Read()
	{
		super("READ");
		acceptable.add(Noun.class);
	}

	public Read(Grammar g)
	{
	}

	public void execute()
	{

		
		noun = (Noun)complements.get("zengine.Noun");

		System.out.println(noun.toString());
		
//		noun = ZEngineMain.state.checkContext(noun);
			

	if (noun ==null)
	//	System.out.println("You can't see a "+noun.toString()+" here!");

		;
	else
			noun.execute(this);
	}
        
	public void callback(String s)
	{
		System.out.println(s);
	}

}
