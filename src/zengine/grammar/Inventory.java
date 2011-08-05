package zengine.grammar;
import zengine.*;


/**
* Represents an "Inventory" action: catalog the player's pack
*/
public class Inventory extends Verb


{

	public Inventory()
	{
		super ("INVENTORY");
		acceptable.add(None.class);

	}
/*
	public boolean accept(Grammar g)
	{
		return false;
		
	}
*/
	public void execute()
	{
		ZEngineMain.state.inventory();
	}	

}

