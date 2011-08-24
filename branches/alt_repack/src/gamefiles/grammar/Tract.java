package gamefiles.grammar;

import zengine.Noun;
import zengine.Verb;

public class Tract extends Noun
{
	private String text = "This appears to be a religious tome of some sort, but it is "
		+ "badly damaged. The author seems to be the prophet Goxlin, and his topic "
		+ "the speech of one of the islands of Indonesia";
	
	private String burnText = "The tract goes up in flames. You feel that Goxlin "
		+ "is peeved. Where did you get the matches, anyway?";
	
	public Tract()
	{
		super ("TRACT");
		this.name = "TRACT";

		
	}


	public void execute(Verb v)
	{
		if (v instanceof Read)
		{
			execute((Read)v);
		}
		else if (v instanceof Burn)
		{
			execute((Burn)v);
		}
	}

	public void execute(Read read)
	{
		System.out.println("Tract.execute("+read.toString()+")");
		read.callback(text);
	}

	public void execute(Burn burn)
	{
		System.out.println("Tract.execute("+burn.toString()+")");
		burn.callback(burnText, this);
	}



}
