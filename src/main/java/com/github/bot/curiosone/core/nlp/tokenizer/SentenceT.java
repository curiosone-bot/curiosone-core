package com.github.bot.curiosone.core.nlp.tokenizer;

/**
 * Different typology of sentence. 
 * @author Andrea Rivitto && Eugenio Schintu
 * @see percorso fino all grammatica da aggiungere successivamente
 */
public enum SentenceT {
    /**
     * Sentence in input is a question.
     */
    QUESTION,   
    /**
     * Sentence in input is a affirmation.
     */
    ANSWER, 
    /**
     * Sentence in input has more than 3 words unrecognizables for the rules of a standard english.
     */
    NOT_ENGLISH,    
    /**
     * Sentence in input has more than one sentence in his structure.
     */
    MORE_SENTENCE,  
    /**
     * Sentence in input has the most two bad words written.
     */
    SOMETING_WRONG
}