package gamefiles.grammar;

import zengine.Noun;
import zengine.Preposition;
import zengine.Grammar;

public class Switch extends Noun
{
        public Switch()
        {
                super("SWITCH");
                plural = false;
                setState(new Off());
                acceptable.add(None.class);
                fixture = true;
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
        
        public void setState(Preposition prep)
        {
                if (prep instanceof On)
                        state = prep.toString();
                else if (prep instanceof Off)
                        state = prep.toString();
        }
        
        public boolean isLightSource()
        {
                return state.equals("ON");
        }
}
