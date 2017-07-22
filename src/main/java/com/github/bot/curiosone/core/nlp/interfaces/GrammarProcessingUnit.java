package interfaces;

import processing.Sentence;

public interface GrammarProcessingUnit 
{
	Sentence parse(String input);
	String generate(Sentence input);
}
