package gamefiles.grammar;


import zengine.*;

/**
* Run a self-test, as defined in Parser.  
*/
public class Entertest extends Verb
{
	
	public Entertest()
	{
		super("ENTERTEST");
		acceptable.add(None.class);
	}


	public Entertest(Grammar g)
	{

	}
	
	public void execute()
	{

		ZEngineMain.parser.test();
	}

	public boolean accept(Grammar g)
	{
		return super.accept(g);	
			
	}


}
