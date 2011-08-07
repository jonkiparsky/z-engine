package zengine.grammar;

import zengine.Noun;
import zengine.None;
import zengine.Grammar;
import zengine.Preposition;

public class Flashlight extends Noun
{
	public Flashlight()
	{
		super ("FLASHLIGHT");
		plural = false;
		setState(new Off());
		acceptable.add(zengine.None.class);
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
