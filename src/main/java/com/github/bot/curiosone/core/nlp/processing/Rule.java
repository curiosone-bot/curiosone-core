package processing;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class Rule
{
	private ArrayList<NonTerminalValue> out;
	
	private NonTerminalValue in;
	
	public Rule(NonTerminalValue out, NonTerminalValue in)
	{
		this.out = new ArrayList<NonTerminalValue>(Arrays.asList());
		this.in = in;
	}
	
	public Rule(NonTerminalValue out1, NonTerminalValue out2, NonTerminalValue in)
	{
		this.out = new ArrayList<NonTerminalValue>(Arrays.asList(out1,out2));
		this.in = in;
	}
	
	public List<NonTerminalValue> getOut()
	{
		return out;
	}
	
	public NonTerminalValue getIn()
	{
		return in;
	}
}
