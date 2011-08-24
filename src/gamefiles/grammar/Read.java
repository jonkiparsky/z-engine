package gamefiles.grammar;

import zengine.Grammar;
import zengine.Noun;

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
		if (noun !=null)
			noun.execute(this);
	}

	public void callback(String s)
	{
		System.out.println(s);
	}

}
