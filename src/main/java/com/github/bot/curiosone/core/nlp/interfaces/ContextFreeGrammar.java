package interfaces;

import java.util.List;

import processing.ParseTree;
import processing.Token;

@FunctionalInterface
public interface ContextFreeGrammar 
{
	ParseTree getParseTree(List<Token> tokens) throws ParsingException;
}
