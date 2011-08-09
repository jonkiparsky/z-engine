package zengine.grammar;

import zengine.Noun;

public class Knife extends Noun
{
        public Knife()
        {
                super("KNIFE");
                plural = false;
                acceptable.add(None.class);
        }
}
