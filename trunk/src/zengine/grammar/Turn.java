package zengine.grammar;

import zengine.*;

public class Turn extends Verb


{

	public Turn()
	{
		super ("TURN");
	}
	public Turn(Grammar g)
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

