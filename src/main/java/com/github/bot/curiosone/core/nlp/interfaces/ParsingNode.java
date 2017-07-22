package interfaces;

import java.util.List;

import processing.NonTerminalValue;

public interface ParsingNode 
{
	NonTerminalValue getValue();
	List<ParsingNode> getSons();
	boolean isRadix();
	boolean isLeaf();
}
