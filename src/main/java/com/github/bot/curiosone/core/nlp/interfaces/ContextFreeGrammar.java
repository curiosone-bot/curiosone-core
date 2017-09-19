package interfaces;

import java.util.List;

import grammarProcessing.Token;

@FunctionalInterface
public interface ContextFreeGrammar 
{
	boolean isCorrect(List<Token> tokens);
}
