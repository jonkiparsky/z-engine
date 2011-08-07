package zengine.grammar;

import zengine.*;

public class Turn extends Verb
{
        Preposition prep;
        
	public Turn()
	{
		super ("TURN");
		acceptable.add(Preposition.class);
	}
        
        public Turn(Grammar g)
        {
            
        }
        
	public boolean accept(Grammar g)
	{
                if (super.accept(g))
                {
                        prep = (Preposition) g;
                        return true;
                }
                return false;
	}

	public void execute()
	{
		
		ZEngineMain.state.turn(prep);
	}

}

