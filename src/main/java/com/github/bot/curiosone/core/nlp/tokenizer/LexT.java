package com.github.bot.curiosone.core.nlp.tokenizer;
/**
 * Enumeraton that contains all sub-tipology of elements in a generic grammar
 * 
 * @see com.github.bot.curiosone.core.nlp.tokenizer.Word
 * @see https://wordnet.princeton.edu/man/lexnames.5WN.html         
 * @see http://www.ucl.ac.uk/internet-grammar/wordclas/open.htm
 * 
 * @author Andrea Rivitto && Eugenio Schintu
 */
public enum LexT {
	/**
	 * All adjective/adverb
	 */
	ALL,

	/**
	 * Pertainyms (relational adjectives)
	 */
	PERT,
	
	/**
	 * Unique beginner for nouns
	 */
	TOPS,
	
	/**
	 * Nouns denoting acts or actions
	 */
	ACT,
	
	/**
	 * Nouns denoting animals
	 */
	ANIMAL,
	
	/**
	 * Denoting man-made objects
	 */
	ARTIFACT,
	
	/**
	 * Nouns denoting attributes of people and objects
	 */
	ATTRIBUTE,
	
	/**
	 * Nouns denoting body parts or verbs of grooming, dressing and bodily care
	 */
	BODY,

	/**
	 * Nouns denoting cognitive processes and contents or verbs of thinking, judging, analyzing, doubting 
	 */
	COGNITION,

	/**
	 * Nouns denoting communicative processes and contents or verbs of telling, asking, ordering, singing 
	 */
	COMMUNICATION,
	
	/**
	 * Nouns denoting natural events
	 */
	EVENT,
	
	/**
	 * Nouns denoting feelings and emotions
	 */
	FEELING,
	
	/**
	 * Nouns denoting foods and drinks
	 */
	FOOD,
	
	/**
	 * Nouns denoting groupings of people or objects
	 */
	GROUP,
	
	/**
	 * Nouns denoting spatial position
	 */
	LOCATION,
	
	/**
	 * Nouns denoting goals
	 */
	MOTIVE,
	
	/**
	 * Nouns denoting natural objects (not man-made) 
	 */
	OBJECT,
	
	/**
	 * Nouns denoting people
	 */
	PERSON,
	
	/**
	 * Nouns denoting natural phenomena
	 */
	PHENOMENON,
	
	/**
	 * Nouns denoting plants
	 */
	PLANT,
	
	/**
	 * Nouns denoting possession and transfer of possession or verbs of buying, selling, owning
	 */
	POSSESSION,

	/**
	 * Nouns denoting natural processes
	 */
	PROCESS,
	
	/**
	 * Nouns denoting quantities and units of measure or determiners before a noun (all,both,many,each,every,several,few,enough,no)
	 */
	QUANTITY,
	
	/**
	 * Nouns denoting relations between people or things or ideas
	 */
	RELATION,
	
	/**
	 * Nouns denoting two and three dimensional shapes
	 */
	SHAPE,
	
	/**
	 * Nouns denoting stable states of affairs
	 */
	STATE,
	
	/**
	 * Nouns denoting substances
	 */
	SUBSTANCE,
	
	/**
	 * Nouns denoting time and temporal relations
	 */
	TIME,
	
	/**
	 * Verbs of size, temperature change, intensifying, etc.
	 */
	CHANGE,
	
	/**
	 * Verbs of fighting, athletic activities
	 */
	COMPETITION,
	
	/**
	 * Verbs of eating and drinking
	 */
	CONSUMPTION,
	
	/**
	 * Verbs of touching, hitting, tying, digging
	 */
	CONTACT,
	
	/**
	 * Verbs of sewing, baking, painting, performing
	 */
	CREATION,
	
	/**
	 * Verbs of feeling
	 */
	EMOTION,
	
	/**
	 * Verbs of walking, flying, swimming
	 */
	MOTION,
	
	/**
	 * Verbs of seeing, hearing, feeling
	 */
	PERCEPTION,
	
	/**
	 * Verbs of political and social activities and events
	 */
	SOCIAL,
	
	/**
	 * Verbs of being, having, spatial relations
	 */
	STATIVE,
	
	/**
	 * Verbs of raining, snowing, thawing, thundering
	 */
	WEATHER,
	
	/**
	 * Participial adjectives
	 */
	ADJ_PPL,
	
	
	/**
	 *  PRONOUNS
	 */
	
	
	/**
	 * I, You, He, She, It, We, You, They
	 */
	PERSONAL_SUBJECTIVE,
	
	/**
	 * Me, You, Him, Her, It, Us, You, Them
	 */
	PERSONAL_OBJECTIVE,
	
	/**
//	 * Mine, Yours, His, Hers, Ours, Theirs
	 */
	POSSESSIVE,
	
	/**
	 * Myself, Yourself, Himself, Herself, Itself, Oneself, Ourselves, Yourselves, Themselves
	 */
	REFLEXIVE,
	
	/**
	 * Each other, One another
	 */
	RECIPROCAL,
	
	/**
	 * That, Which, Who, Whose, Whom, Where, When
	 */
	RELATIVE,
	
	
	/**
	 * This, That, These, Those)
	 */
	DEMONSTRATIVE,
	
	/**
	 * Who, What, Why, Where, When, Whatever
	 */
	INTERROGATIVE,
	
	/**
	 * As pronoun: Anything, Anybody, Anyone, Something, Somebody, Someone, Nothing, Nobody, None, No one
	 * As determines: A, An
	 */
	INDEFINITE,
	                    // opp. determiners (a, an)
	
	/**
	 * DETERMINERS 
	 */
	
	
	/**
	 * The
	 */
	DEFINITE,
    
	/**
	 * Any, That, Those, This, Some, Whatever, Whichever
	 */
	OTHER
}
