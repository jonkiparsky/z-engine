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
 
		System.out.println(Utils.getLocalRoot());
	
		strings = PropertyLoader.getProperties("strings");

                //testTimer();
                
		state.look();		
		while (true) 
			parser.makeMove();	
	}
        
        private void testTimer()
        {  
                // Commented times are what the correct time would be.
                // Number passed is an hour.
                // Monday 00:00
                Timer.showTime();
                Timer.increaseTime(12f);
                // Monday 12:00
                Timer.showTime();
                Timer.increaseTime(9f);
                // Monday 21:00
                Timer.showTime();
                Timer.increaseTime(48f);
                // Wednesday 21:00
                Timer.showTime();
                Timer.increaseTime(72f);
                // Saturday 21:00
                Timer.showTime();
                Timer.increaseTime(72f);
                // Tuesday 21:00
                Timer.showTime();
        }
}
