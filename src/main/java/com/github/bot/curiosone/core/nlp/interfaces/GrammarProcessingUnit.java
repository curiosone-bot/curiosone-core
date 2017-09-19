package interfaces;

import grammarProcessing.Sentence;

public interface GrammarProcessingUnit 
{
	Sentence parse(String input);
	String generate(Sentence input);
}
