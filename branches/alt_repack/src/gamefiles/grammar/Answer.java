package gamefiles.grammar;

import zengine.ZEngineMain;
import zengine.Verb;

public class Answer extends Verb
{
	public Answer(String s)
	{
		super(s);
		this.name=s;
		acceptable.add(None.class);
	}

	

	
	public void execute()
	{
		ZEngineMain.state.answer(this.name);
	}
}
