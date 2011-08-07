package zengine.grammar;
     
import zengine.*;

/**
* Represents a Drop action
*/
public class Drop extends Verb


{
	public Drop()
	{
		super ("DROP");
		this.acceptable.add(Noun.class);
	}
	public Drop(Grammar g)
	{
		
	}

	public boolean accept(Grammar g)
	{
		if (!super.accept(g))
		{	
			return false;
		}
			this.noun = (Noun) g;
			return true;
	}


	public void execute()
	{
		ZEngineMain.state.drop(noun);
	}	

}

