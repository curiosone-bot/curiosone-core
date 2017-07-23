package com.github.bot.curiosone.core.nlp.processing;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.github.bot.curiosone.core.nlp.interfaces.ParsingNode;

public class NonTerminal implements ParsingNode
{
	private NonTerminalValue value;
	
	private List<ParsingNode> sons;
	
	public NonTerminal(NonTerminalValue value, ParsingNode token1, ParsingNode token2)
	{
		this.value = value;
		this.sons = new ArrayList<ParsingNode>(Arrays.asList(token1,token2));
	}


	public NonTerminalValue getValue()
	{
		return value;
	}


	@Override
	public List<ParsingNode> getSons()
	{

		return sons;
	}


	@Override
	public boolean isRadix()
	{
		return false;
	}


	@Override
	public boolean isLeaf() 
	{
		return false;
	}
}
