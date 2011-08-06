package zengine;
public class ZEngineMain
{
	Model model;
	public static State state;
	public static Parser parser;	
	private static boolean test = false;


	public static void main(String[] args)
	{
		//if (args.length> 0) test = true;
                test = true;
		System.out.println("test = " +test);
		ZEngineMain main = new ZEngineMain();
		main.start();		
		
	}

	private void start()
	{
		model = new Model();
		state = new State();
		parser = new Parser();			
		System.out.println("instantiated model and state");

		if (test)
		{
			parser.test();
		}
		while (true) 
 		//	 parser.movePhrase();
			parser.makeMove();	
	}
}
