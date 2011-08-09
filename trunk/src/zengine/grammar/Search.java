package zengine.grammar;
import zengine.*;
import zengine.grammar.*;

/**
* Represents an "Search" action: catalog the player's pack
*/
public class Search extends Verb


{

	public Search()
	{
		super ("SEARCH");
		acceptable.add(None.class);

	}
	public void execute()
	{
		ZEngineMain.state.search();
	}	

}

