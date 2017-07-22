package com.github.bot.curiosone.core.nlp.processing;

public enum Typology 
{
	QUESTION,     // Needs to be an AFFIRMATION plus it has the question mark
	AFFIRMATION,  // If it has a subject (may be implicit), a verb and a direct object
	UNKNOWN       // Everything else
}
