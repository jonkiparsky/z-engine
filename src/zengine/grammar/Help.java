package zengine.grammar;

import zengine.Verb;
import zengine.None;
import zengine.ZEngineMain;

public class Help extends Verb
{
        public Help()
        {
                super("HELP");
                acceptable.add(None.class);
        }
        
        public void execute()
        {
                ZEngineMain.state.help();
        }
}
