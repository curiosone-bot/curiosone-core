package interfaces;

import processing.Sentence;

public interface ExtractionRoute 
{
	ModularExtractor decode(Sentence s);
}
