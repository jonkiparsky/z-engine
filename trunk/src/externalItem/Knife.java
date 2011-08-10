package externalItem;

import zengine.Noun;
import zengine.grammar.None;

public class Knife extends Noun
{
        public Knife()
        {
                super("KNIFE");
                plural = false;
                acceptable.add(None.class);
        }
}
