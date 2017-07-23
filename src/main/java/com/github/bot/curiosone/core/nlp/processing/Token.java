package com.github.bot.curiosone.core.nlp.processing;

import java.util.List;

import com.github.bot.curiosone.core.nlp.interfaces.ParsingNode;

public class Token implements ParsingNode
{
	String word;
	NonTerminalValue value;
	
	Token(String word, NonTerminalValue value)
	{
		this.value = value;
		this.word = word;
	}
	
	public String getWord() { return word; }
	
	public NonTerminalValue getTerminalValue() { return value; }

	@Override
	public NonTerminalValue getValue() 
	{
		return null;
	}

	@Override
	public List<ParsingNode> getSons() 
	{
		return null;
	}

	@Override
	public boolean isRadix() 
	{
		return false;
	}

	@Override
	public boolean isLeaf()
	{
		return true;
	}
	

}
