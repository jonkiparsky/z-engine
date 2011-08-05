package zengine.grammar;


import zengine.*;

/**
* Run a self-test, as defined in Parser.  
*/
public class EnterTest extends Verb
{
	
	public EnterTest()
	{
		super("ENTERTEST");
		acceptable.add(None.class);
	}


	public EnterTest(Grammar g)
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
