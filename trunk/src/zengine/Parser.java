package zengine;

import java.lang.reflect.*;
import java.util.HashMap;
import java.lang.Class;
import java.util.Scanner;
import java.util.ArrayList;
import zengine.grammar.*;

public class Parser
{
	private boolean error;
	Scanner scan;
	Scanner in;
        
        
        ArrayList<String> validWords;

	public Parser()
	{
		error = false;
		in = new Scanner(System.in);
                initWords();
                
	}	

	public Parser(String command)
	{
		error = false;
		scan = new Scanner(command);
                initWords();
	}
        
        private void initWords()
        {
            validWords = new ArrayList<String>();
                validWords.add("go");
                validWords.add("north");
                validWords.add("south");
                validWords.add("east");
                validWords.add("west");
                validWords.add("take");
                validWords.add("flashlight");
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
		
		StringBuffer tokens = new StringBuffer();
		do
		{
			System.out.print(">> ");
			input = in.nextLine();
		} while (input.length() == 0);
		
		String[] move = input.split("\\W");
		
      Sentence sentence = Sentence.getSentence(tokenise(move[0]));
		
		if (sentence == null)
		{
			error ("error 1");
			return;
		}

		if (move.length <2) 
		{
			sentence.execute();
			return;
		}
	
		String[] m = new String[move.length - 1];		
		System.arraycopy(move, 1, m, 0, move.length - 1);
				
		for (String word: m)
		{
			Grammar g = tokenise(word);
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

	/*
	*	There are currently three methods for accepting a move. Make the 
	*	appropriate change in ZEngineMain
         * 
         *      movePhrase() used to test phrases.
	*/
        
        public void movePhrase()
        {
                System.out.print(">> ");
                String[] move = in.nextLine().split(" ");
                ArrayList<Grammar> gram = new ArrayList<Grammar>();
                for (String s : move)
                        gram.add(tokenise(s));
                NPhrase phrase = new NPhrase(move, gram);
                phrase.execute();
        }
        
	public void move()
	{
		
		System.out.print(">> ");
		String[] move = in.nextLine().split(" ");
                ArrayList<String> moveBuffer = updateBuffer(move);
                moveBuffer = validateBuffer(moveBuffer);
                do
                {
                        Grammar g = tokenise(move[0]);
                        if (g.accept(tokenise(move[1])))
                        {
                                g.execute();
                        }
                        move = new String[moveBuffer.size()];
                        if (moveBuffer.size() >= 2)
                        {
                            //move = new String[moveBuffer.size()];
                            move[0] = moveBuffer.remove(0);
                            move[1] = moveBuffer.remove(0);
                        }
                } while (move.length >= 2);
	}
        
        private ArrayList<String> updateBuffer(String[] move)
        {
                ArrayList<String> newMove = new ArrayList<String>();
                if (move.length >= 2)
                {
                        for (int i = 2; i != move.length; i++)
                            newMove.add(move[i]);
                }
                return newMove;
        }
        
        private ArrayList<String> validateBuffer(ArrayList<String> moveBuffer)
        {
                ArrayList<String> copy = (ArrayList<String>) moveBuffer.clone();
                Grammar g = null;
                for (String s : moveBuffer)
                {
                        if (!validWords.contains(s))
                            copy.remove(s);
                }
                return copy;
        }


	public Verb parse(String verb, String complement)
	{
		Verb ret=null;
		Grammar v = tokenise(verb);
		Grammar c = tokenise(complement);
		try{
		 ret = (Verb)v.getClass().getConstructor(c.getClass().
						getSuperclass()).newInstance(c);
		}

		catch (Exception e)
		{
			System.out.println("Parse fuckup!");
			e.printStackTrace();

	
		}

		return ret;
	}


	/**
	* Attempting to force a Grammar item into its base class
	* This definitively does not work.
	*/
	private <T> T  forceType(T word)
	{
		return word;
	}

	public void parseFail(String s)
	{
		error("I don't know what "+s+" means");
	}

	public void parseFail(Grammar g)
	{
		error("Cannot understand "+g.toString()+" here");
	}

	public Grammar tokenise( String input)
	{
		input = correctCase(input);
		Class c; 
		Grammar g=null;
		try{
		c = Class.forName("zengine.grammar."+input);
		g = (Grammar)c.newInstance();
		}
		catch (Exception e)
		{
			error = true;
			System.out.println(e.toString());
		}
		
		return g;
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

	public boolean endOfString()
	{
		return !scan.hasNext();
	}

	private void error(String s)
	{
		System.out.println(s);
	}	

	public void test()
	{
		System.out.println("---------");
		System.out.println("Parse Test");
		System.out.println("---------");
	
		Grammar g = tokenise("inventory");
		g.execute();

		System.out.println(">>Go north");	
		Grammar gram = tokenise("Go");
		g = tokenise("north");
		if (gram.accept(g))
			gram.execute();

		System.out.println(">>take flash");	
gram = tokenise("take");
		g = tokenise("flashlight");
		if (gram.accept(g))
			gram.execute();
	
		System.out.println(">>take north");	
gram = tokenise("take");
		g = tokenise("north");
		if (gram.accept(g))
			gram.execute();

			

		System.out.println(">>Go south");	
		gram = tokenise("Go");
		g = tokenise("south");
		if (gram.accept(g))
			gram.execute();
		
	
		System.out.println(">>take flash");	
		gram = tokenise("take");
		g = tokenise("flashlight");
		if (gram.accept(g))
			gram.execute();

		System.out.println(">>drop flash");	
		gram = tokenise("drop");
		g = tokenise("flashlight");
		if (gram.accept(g))
			gram.execute();

		System.out.println(">>Go north");	
		gram = tokenise("Go");
		g = tokenise("north");
		if (gram.accept(g))
			gram.execute();

		System.out.println(">>Go south");	
		gram = tokenise("Go");
		g = tokenise("south");
		if (gram.accept(g))
			gram.execute();

		System.out.println(">>drop flash");	
		gram = tokenise("drop");
		g = tokenise("flashlight");
		if (gram.accept(g))
			gram.execute();


		gram = tokenise("North");
		g = tokenise("none");
		error("North.accept(none) returns: "+gram.accept(g));


/*
		System.out.println(">>entertest flash");	
		gram = tokenise("entertest");
		g = tokenise("flashlight");
		if (gram.accept(g))
			gram.execute();
*/
		g = tokenise("inventory");
		g.execute();
}

}