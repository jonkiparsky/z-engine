package zengine.grammar;


import zengine.*;

public class Take extends Verb


{

	public Take()
	{
		super ("TAKE");
		acceptable.add(Noun.class);
	}
	public Take(Grammar g)
	{
		
	}

	public void execute()
	{

		noun = (Noun)complements.get("zengine.Noun");
		if (noun != null)
			ZEngineMain.state.take(noun);
	}	

}

