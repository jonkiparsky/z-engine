package gamefiles.grammar;


import zengine.*;

public class Take extends Verb
{
        public static String desc;
        static
        {
                desc = " - Take\nUsage: Take (ITEM)\nExample: Take Flashlight\nDescription: Takes an item from the room you're in";
        }

	public Take()
	{
		super ("TAKE");
		acceptable.add(Noun.class);
	}
	public Take(Grammar g)
	{
		
	}

	public void execute()
	{

		noun = (Noun)complements.get("zengine.Noun");
		if (noun != null)
			ZEngineMain.state.take(noun);
	}	

}

