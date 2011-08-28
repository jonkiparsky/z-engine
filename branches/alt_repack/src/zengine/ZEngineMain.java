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
                
                testTimer();
                System.out.println("Changing time step to 30 mins");
                Time.resetTime();
                Time.setTimeStep(30);
                testTimer();
                
		state.look();		
		while (true) 
			parser.makeMove();	
	}
        
        private void testTimer()
        {  
                Time.showTime();
                
                System.out.println("\nIncreasing 1 timestep");
                Time.increaseTime(1);
                Time.showTime();
                
                System.out.println("\nIncreasing 17 timesteps");
                Time.increaseTime(17);
                Time.showTime();
                
                System.out.println("\nIncreasing 30 timesteps");
                Time.increaseTime(30);
                Time.showTime();
                
                System.out.println("\nIncreasing 75 timesteps");
                Time.increaseTime(75);
                Time.showTime();
                
                System.out.println("\nIncreasing 150 timesteps");
                Time.increaseTime(150);
                Time.showTime();
        }
}
