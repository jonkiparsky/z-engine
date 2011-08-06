package zengine.grammar;

import zengine.*;

public class Turn extends Verb


{

	public Turn()
	{
		super ("TURN");
		acceptable.add(Noun.class);
	}
	public Turn(Grammar g)
	{
		
	}

	public void execute(Preposition prep)
	{
		
		ZEngineMain.state.turn(noun, prep);
	}

}

