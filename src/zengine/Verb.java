package zengine;

/**
 * Base class for Verbs.
 */
public abstract class Verb extends Grammar
{
	/**
     * Noun associated with this verb.
     */
	protected Noun noun = null;
        /**
         * Direction associated with this verb.
         */
	protected Direction dir = null;
	protected int duration = 10;
        /**
         * Default constructor. Use public Verb(String name) instead.
         */
	public Verb()
	{
		super();
	}
        
        /**
         * Constructor to be used for verb creation. Call this in derived
         * classes using super(String name).
         * @param name 
         * Name of the verb.
         */
	public Verb(String name)
	{
		this.name = name;
	}
	
        /**
         * Constructor that accepts grammar objects.
         */
	public Verb(Grammar g)
	{
	}
	
        /**
         * Default execute method. Should only be reached in error, override
         * in derived classes.
         */
	public void execute()
	{
		ZEngineMain.state.updateTime(duration);
	}
        
        /**
         * Default execute method. Should only be reached in error, override
         * in derived classes.
         * @param prep 
         * Preposition to associate with the verb.
         */
        public void execute(Preposition prep)
        {
            
        }
	
        /**
         * Returns the name of the Verb.
         */
	public String toString()
	{
		return (name);
	}


}

