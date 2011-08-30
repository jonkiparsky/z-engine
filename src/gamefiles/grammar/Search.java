package gamefiles.grammar;
import zengine.*;
import gamefiles.grammar.*;

/**
* Represents an "Search" action: catalog the player's pack
*/
public class Search extends Verb
{


	public Search()
	{
		super ("SEARCH");
		acceptable.add(None.class);
		duration = 120;
	}
	public void execute()
	{
		super.execute();
		ZEngineMain.state.search();
	}	

}

