package gamefiles.grammar;

import zengine.Verb;
import zengine.ZEngineMain;
import zengine.Confirmable;
import zengine.Time;

public class Quit extends Verb implements Confirmable{
    
        public static String desc;
        private static float timeAsked;
        private static float timeElapsed;
        static
        {
                desc = "- Quit\nUsage: Quit\nDescription: Quits Game";
        }
        public Quit()
        {
                super("QUIT");
                acceptable.add(None.class);
        }
    
        public void execute()
        {
		System.out.println("Are you sure you want to quit?");
		ZEngineMain.state.setConfirm((Confirmable)this);
                //timeAsked = Time.getTime();
        }
		
        public void confirm(String s)	
        {
                //timeElapsed = Time.getTime() - timeAsked;
                if (timeElapsed > 1)
                {
                        System.out.println("You have taken too long to confirm quitting.");
                }
                else if (s.equals("YES"))
		{
			String quitString = "Be seeing you...";
                        ZEngineMain.state.quit(quitString);
		}
		else if (s.equals("NO"))
		{
			System.out.println("So you'll stick around. Excellent.");
			ZEngineMain.state.clearConfirm();
		}
	}


}
