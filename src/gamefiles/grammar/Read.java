package gamefiles.grammar;

import zengine.Grammar;
import zengine.Noun;
import zengine.IReadable;

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
        
        // Alternative to the other. Object passed will always have readText()
        // so no errors will be found here.
        public void execute(IReadable n)
        {
                callback(n.readText());
        }

	public void callback(String s)
	{
		System.out.println(s);
	}

}
