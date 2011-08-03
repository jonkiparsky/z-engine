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

	private <T> T  forceType(T word)
	{
		return word;
	}


	public void parseFail(Grammar g)
	{
		System.out.println("Cannot understand "+g.toString()+" here");
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
			e.printStackTrace();
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

	
}
