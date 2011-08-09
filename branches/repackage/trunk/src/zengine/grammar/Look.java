package zengine.grammar;
import zengine.*;
import zengine.grammar.*;

/**
* Represents an "Look" action: catalog the player's pack
*/
public class Look extends Verb


{

	public Look()
	{
		super ("LOOK");
		acceptable.add(None.class);

	}
	public void execute()
	{
		ZEngineMain.state.look();
	}	

}

