package zengine.grammar;

import zengine.Grammar;
import zengine.Preposition;
import zengine.Noun;

public class Flashlight extends Noun
{
	public Flashlight()
	{
		super ("FLASHLIGHT");
		plural = false;
		setState(new Off());
		acceptable.add(None.class);
	}
        
        public boolean accept(Grammar g)
        {
                if (super.accept(g))
                {
                        if (g.getClass() != None.class)
                            prep = (Preposition) g;
                        return true;
                }
                return false;
        }
        
        public void setState(zengine.Preposition prep)
        {
                if (prep instanceof On)
                        state = prep.toString();
                else if (prep instanceof Off)
                        state = prep.toString();                       
        }
}
