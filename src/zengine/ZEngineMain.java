package zengine;


/**
* Kickoff class for Z-Engine. This class does nothing but start the game; no
* working code should lodge here.
*/
public class ZEngineMain
{
	Model model;
	public static State state;
	public static Parser parser;	
	private static boolean test = false;


	public static void main(String[] args)
	{
		if (args.length> 0) test = true;
		System.out.println("test = " +test);
		ZEngineMain main = new ZEngineMain();
		main.start();		
		
	}

	private void start()
	{
		model = new Model();
		state = new State();
		parser = new Parser();			

		if (test)
		{
			parser.test();
		}
		
		while (true) 
			parser.makeMove();	
	}
}
