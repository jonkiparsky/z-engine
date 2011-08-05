package zengine;

/*
 * NPhrase should hold a noun phrase that will execute with or without additional
 * preposition phrases (PPhrase). Either a NPhrase or PrepNPhrase must be at the
 * start of a sentence structure. Sentences cannot start with a PPhrase, and
 * NPhrase/PrepNPhrase can't be after the beginning.
 * 
 * If needed, PPhrase extension will be the additional phrase to execute.
 */
import java.util.ArrayList;

public class NPhrase extends Grammar 
{
        String[] structure;
        Noun noun;
        Verb verb;
        Preposition prep;
        PPhrase extension;      // Another name to make clearer?
        
        // Can only contain 3 words (noun, verb, prep).
        final int PHRASE_LIMIT = 3;
        
        public NPhrase (String[] s, ArrayList<Grammar> tokens)
        {
                super();
                structure = s;
                setAcceptable();
                int iterator = 0;
                // Assigns the words to the correct variable
                // Better way to do this?
                for (Grammar g : tokens)
                {
                        if (iterator != PHRASE_LIMIT)
                        {
                                if (Noun.class.isAssignableFrom(g.getClass()))
                                        noun = (Noun) g;
                                else if (Verb.class.isAssignableFrom(g.getClass()))
                                        verb = (Verb) g;
                                else if (Preposition.class.isAssignableFrom(g.getClass()))
                                        prep = (Preposition) g;
                        
                        }
                        iterator++;
                }
        }
        
        private void setAcceptable()
        {
                acceptable.add(Noun.class);
                acceptable.add(Verb.class);
                acceptable.add(Preposition.class);
        }
                
        public String toString()
        {
                return ("NPhrase: " + structure + "\nNoun: " + noun + "\nVerb: " + verb + "\nPrep: " + prep);
        }
        
        public boolean accept(Grammar g)
        {
                for (Class c : acceptable)
                {
                        if (c.isAssignableFrom(g.getClass()))
                        {
                            return true;
                        }
                }
                return false;               
        }
        
        public boolean accept(Grammar[] g)
        {
                for (Grammar gram : g)
                {
                        if (this.accept(g))
                            return true;
                        return false;
                }
                return false;
        }
        
        public boolean accept (ArrayList<Grammar> g)
        {
                for (Grammar gram : g)
                {
                        if (this.accept(g))
                            return true;
                        return false;
                }
                return false;
        }
}
