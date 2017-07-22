package interfaces;

import java.util.ArrayList;

import processing.Sentence;


public interface ModularExtractor 
{
	ArrayList<String> getParams(Sentence s);
}
