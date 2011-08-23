package gamefiles.grammar;

import zengine.Verb;
import zengine.ZEngineMain;
import zengine.Confirmable;

public class Quit extends Verb implements Confirmable{
    
        public static String desc;
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
        }
		
			public void confirm(String s)	
		{
			if (s.equals("YES"))
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
