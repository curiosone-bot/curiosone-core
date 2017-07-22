package processing;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import interfaces.ContextFreeGrammar;
import interfaces.ParsingException;

public final class Grammar implements ContextFreeGrammar 
{
	private static Grammar grammar;
	
	private List<Rule> procedures = new ArrayList<Rule>();
	
	private Grammar() 
	{
		StringBuilder sb = new StringBuilder();

		try(BufferedReader br = Files.newBufferedReader(Paths.get("resources/grammar.txt")))
		{	
			while(br.ready())
			{
				String[] array = br.readLine().split(",");
				
				if (array.length <= 1) continue;
				
				if (array.length == 2)
					
					procedures.add(new Rule(
											NonTerminalValue.valueOf(array[0]),
											NonTerminalValue.valueOf(array[1])
											));
				if (array.length == 3)
					
					procedures.add(new Rule(
											NonTerminalValue.valueOf(array[0]),
											NonTerminalValue.valueOf(array[1]),
											NonTerminalValue.valueOf(array[2])
											));
				
				if (array.length > 3) continue;
				
			}
		}
		
		catch (IOException e)	{}
	}
	
	public static Grammar get()
	{
		if (grammar==null) grammar = new Grammar();
			
		return grammar;
	}


	@Override
	public ParseTree getParseTree(List<Token> tokens) throws ParsingException
	{
		ParseTree tree = new ParseTree(tokens);
		
		return tree;
	}

	public List<Rule> getProcedures()
	{
		return procedures;
	}

}
