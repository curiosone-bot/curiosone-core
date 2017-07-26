package com.github.bot.curiosone.core.nlp.tokenizer;

/**
 * Part Of Speech (POS) type.
 * @see  https://en.oxforddictionaries.com/grammar/word-classes-or-parts-of-speech
 * @author Andrea Rivetto && Eugenio Schintu
 */
public enum POST {
	/**
	 * Noun.
	 */
	NOUN,
	/**
	 * Verb.
	 */
	VERB,
	/**
	 * Adjective.
	 */
	ADJECTIVE,
	/**
	 * Adverb.
	 */
	ADVERB,
	/**
	 * Pronoun.
	 */
	PRONOUM,
	/**
	 * Preposition.
	 */
	PREPOSITION,
	/**
	 * Comjunction.
	 */
	CONJUNCTION,
	/**
	 * Determiner.
	 */
	DETERMINER,
	/**
	 * Interjection.
	 */
	INTERJECTION
}