package zengine;


/**
* Kickoff class for Z-Engine. This class does nothing but start the game; no
* working code should lodge here.
*/
public class ZEngineMain
{
	public static State state;
	public static Parser parser;	


	public static void main(String[] args)
	{
		ZEngineMain main = new ZEngineMain();
		main.start();		
		
	}

	private void start()
	{
		Model.init("externalRoom", "externalItem");
		state = new State();
		parser = new Parser();			

		state.look();		
		while (true) 
			parser.makeMove();	
	}
}
