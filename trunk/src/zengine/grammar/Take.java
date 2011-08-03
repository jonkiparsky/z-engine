package zengine.grammar;


import zengine.*;

public class Take extends Verb


{

	public Take()
	{
		super ("TAKE");
	}
	public Take(Grammar g)
	{
		
	}

	public boolean accept(Grammar g)
	{
		if (g instanceof Noun)
		{
			this.noun = (Noun) g;
			return true;
			
		}

		else return false;
	}
	public void execute()
	{
		ZEngineMain.state.take(noun);
	}	

}

