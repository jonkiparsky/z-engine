package zengine;

import java.lang.reflect.*;
import java.util.HashMap;
import java.lang.Class;
import java.util.Scanner;
import java.util.Properties;
import gamefiles.grammar.*;

public class Parser
{
	private boolean error;
	Scanner scan;
	Scanner in;
	Scanner inputScanner;
	private Properties macros;

       
	/**
	* The no-arg constructor returns a Parser prepared to accept 
	* input from STDIN
	*/
	public Parser()
	{
		error = false;
		in = new Scanner(System.in);
		loadProperties();
	}	

	/**
	* Creates a parser on a particular String. This may be a candidate for
	* deletion, although it could be useful for testing.
	*/
	public Parser(String command)
	{
		error = false;
		scan = new Scanner(command);
	
		loadProperties();
	}

	/**
	*	There are currently three methods for accepting a move. Make the 
	*	appropriate change in ZEngineMain
	*	
	*	This one recognizes good words used wrongly and non-words, and reacts
	*	correctly to those. It does not what to do with the end of a sentence,
	*	though.
	*/
	public void makeMove()
	{
		String input;
		do
		{
	//		System.out.println(ZEngineMain.state.dateAsString());
			System.out.print(">> ");
			input = in.nextLine();
		} while (input.length() == 0);
		
		inputScanner = new Scanner(input);
		processMove();
	}	


	private void processMove()
	{
		StringBuffer tokens = new StringBuffer();
		
		String first = nextWord();	
				
		Grammar token = tokenise(first);
		if (token==null) 
		{	
			
			error ("I don't recognize that word - see parser.makeMove");
			return;
		}
		
		Sentence sentence = Sentence.getSentence(token);
		
		if (sentence == null)
		{
			error ("error 1");
			return;
		}

			// If one-word command, simply make a one-word sentence and try to
			// execute it.

		while (inputScanner.hasNext())
		{
			String word = inputScanner.next();
			Grammar g = tokenise(word);
			if (error) 
			{
				error = false;
				break;
			}
			tokens.append(g.toString());
			tokens.append(", ");
			if (g == null) 
			{
				parseFail(word);
				break;
			}		
	
			if (!sentence.accept(g))
			{
				parseFail(g);
				break;
			}
		}
				
		if (error)
		{
			error("I don't know what that word means: see parser.makeMove\n"+
				"Tokens: "+tokens);
			error= false;
			return;
		}
		
		if (sentence.accept(tokenise("None")))
		{
			System.out.println(tokens.toString());
			
			sentence.execute();
		}
		else
		{
			error("premature end of input: " + tokens.toString());
		}
	
	}

	/**
	*	Returns the next word in the input.
	*/
	private String nextWord()
	{
		String word = inputScanner.next();
		if (macros.get(word) != null)
			word =(String) macros.get(word);
	
		return word;
	}

	/**
	*	Implemented for politeness only. If this is ever needed, please explain
	*	why in this javadoc string. 
	*/

	public String toString()
	{
		return "PARSER";
	}


	/**
	* Respond to a failure to parse a String
	*/
	private void parseFail(String s)
	{
		error("I don't know what "+s+" means(pfs)");
	}


	/**
	* Respond to a failure to parse a grammar object
	*/
	private void parseFail(Grammar g)
	{
		error("Cannot understand "+g.toString()+" here(pfg)");
	}


	/**
	*	Attempts to create and return a grammar object for the class represented
	*	by the String "input"
	*	
	*/
	public Grammar tokenise( String input)
	{
		input = correctCase(input);
		Class c; 
		Grammar g = null;
		try 
		{
			c = Class.forName("gamefiles.grammar." + input);
			g = (Grammar) c.newInstance();
			/*if (g == null)
			{
				c = Class.forName("zengine." + input);
				g = (Grammar) c.newInstance();
			}*/
		}
		catch (ClassNotFoundException cnfe)
		{
			    if ((g = tokeniseRoom(input)) == null)
			    {
				    System.out.println("I don't know what "+input+" means! (cnfe)");
				    error = true;
			    }
		}
		catch (InstantiationException ie)
		{	
			if ((g = tokeniseRoom(input)) == null)
			{
				error = true;
				System.out.println("error: "+ ie.getMessage());
			}
		}
		catch (IllegalAccessException iae)
		{
			g = tokeniseRoom(input); 
			if (g == null)
			{
				error = true;
				System.out.println("error: "+ iae.getMessage());
			}
		}
		
		return g;
	}

	/**
	*	Return a Room object for the current input. This will eat input until it
	*	matches a room, which may end up being incorrect.
	*/	
	public Grammar tokeniseRoom(String input)
	{
		Room r = ZEngineMain.model().getRoom(input.toLowerCase());
		if (r !=null) 
			return r;
		if (in.hasNext())
			return tokeniseRoom(input + " "+ nextWord());
		else return null;	
	
	}

	private String correctCase(String s)
	{
		StringBuilder sb= new StringBuilder(s.toLowerCase());
		sb.replace(0,1, sb.substring(0,1).toUpperCase());
		return sb.toString();	
	}
	
	public Parser parser()
	{
		return this;
	}

	private void error(String s)
	{
		Utils.debug(this, s);
	}	

	private void loadProperties()
	{
			// hidden literal string buried in the code
			// "macros" is the partial name of the file containing abbreviated
			// command names.

		macros = PropertyLoader.getProperties("macros");

	}


	public void test()
	{
		System.out.println("---------");
		System.out.println("Parse Test");
		System.out.println("---------");	
	}

}
