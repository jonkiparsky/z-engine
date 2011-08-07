package zengine.grammar;

import zengine.Verb;
import zengine.None;
import zengine.ZEngineMain;

public class Quit extends Verb {
    
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
                ZEngineMain.state.quit();
        }
}
