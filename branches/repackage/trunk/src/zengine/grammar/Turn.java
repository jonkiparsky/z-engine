package zengine.grammar;

import zengine.*;

public class Turn extends Verb
{
        Preposition prep;
        Noun noun;
        
	public Turn()
	{
		super ("TURN");
		acceptable.add(Preposition.class);
                acceptable.add(externalItem.Flashlight.class);
	}
        
        public Turn(Grammar g)
        {
            
        }
        
	public boolean accept(Grammar g)
	{
                if (Preposition.class.isAssignableFrom(g.getClass()))
                {
                        prep = (Preposition) g;
                        return true;
                }
                else if (Noun.class.isAssignableFrom(g.getClass()))
                {
                        noun = (Noun) g;
                        return true;
                }
                return false;
	}

	public void execute()
	{
                if (prep != null)
                    ZEngineMain.state.turn(prep);
                else
                    ZEngineMain.state.turn(noun);
	}

}

