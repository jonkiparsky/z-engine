package zengine;

import java.util.Properties;

/**
* Kickoff class for Z-Engine. This class does nothing but start the game; no
* working code should lodge here.
*/
public class ZEngineMain
{
	Model model;
	public static State state;
	public static Parser parser;	
	public static Properties strings;

	public static void main(String[] args)
	{
		ZEngineMain main = new ZEngineMain();
		main.start();		
		
	}

	private void start()
	{
		model = new Model();
		state = new State();
		parser = new Parser();			
 

		strings = PropertyLoader.getProperties("strings");


		state.look();		
		while (true) 
			parser.makeMove();	
	}
}
