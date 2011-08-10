package externalVerb;
import zengine.*;
import java.util.HashMap;
import zengine.grammar.None;

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
	
	public void execute()
	{
		//ZEngineMain.state.inventory();
                HashMap<String, Noun> inventory = ZEngineMain.state._getInventory();
                if (inventory.size()==0)
		{
		System.out.println("You are unburdened by material goods.");
		
		}
		for (String s: inventory.keySet())
		{ 
			if (!inventory.get(s).plural())
			{
				System.out.println("You have a " + s + ".");
				if (inventory.get(s).getState() != null)
				    System.out.println(inventory.get(s).itemDescription());
			}
			else
			{
	    		System.out.println("You have "+s+".");
		    	System.out.println(inventory.get(s).itemDescription());
			}
            }
	}	

}
