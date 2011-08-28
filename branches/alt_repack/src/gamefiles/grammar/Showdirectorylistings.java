package gamefiles.grammar;

import zengine.Verb;
import zengine.ZEngineMain;
import zengine.Utils;
import zengine.Confirmable;
//import zengine.Timer;

public class Showdirectorylistings extends Verb{
    
        public Showdirectorylistings()
        {
                super("QUIT");
                acceptable.add(None.class);
        }
    
        public void execute()
        {
				Utils.printDirectoryListings();
        }


}
