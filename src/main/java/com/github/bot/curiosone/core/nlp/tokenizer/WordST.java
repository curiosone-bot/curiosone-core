package com.github.bot.curiosone.core.nlp.tokenizer;

public enum WordST {
	/**
	 * Cfr. https://wordnet.princeton.edu/man/lexnames.5WN.html
	 *    e http://www.ucl.ac.uk/internet-grammar/wordclas/open.htm
	 */
	ALL,	            // all adjective/adverb
	PERT,               // relational adjectives (pertainyms)
	TOPS,               // unique beginner for nouns
	ACT,                // nouns denoting acts or actions
	ANIMAL,             // nouns denoting animals
	ARTIFACT, 			// denoting man-made objects
	ATTRIBUTE,  		// nouns denoting attributes of people and objects
	BODY,               // nouns denoting body parts,
						// opp. verbs of grooming, dressing and bodily care
	COGNITION,          // nouns denoting cognitive processes and contents,
						// opp. verbs of thinking, judging, analyzing, doubting
	COMMUNICATION,      // nouns denoting communicative processes and contents
						// opp. verbs of telling, asking, ordering, singing
	EVENT,             	// nouns denoting natural events
	FEELING, 			// nouns denoting feelings and emotions
	FOOD, 				// nouns denoting foods and drinks
	GROUP,              // nouns denoting groupings of people or objects
	LOCATION,     		// nouns denoting spatial position
	MOTIVE, 			// nouns denoting goals
	OBJECT,  			// nouns denoting natural objects (not man-made)
	PERSON, 			// nouns denoting people
	PHENOMENON,         // nouns denoting natural phenomena
	PLANT,              // nouns denoting plants
	POSSESSION, 		// nouns denoting possession and transfer of possession
						// opp. verbs of buying, selling, owning
	PROCESS, 			// nouns denoting natural processes
	QUANTITY,  			// nouns denoting quantities and units of measure
	                    // determiners before a noun (all,both,many,each,every,several,few,enough,no)  
	RELATION, 			// nouns denoting relations between people or things or ideas
	SHAPE, 				// nouns denoting two and three dimensional shapes
	STATE, 				// nouns denoting stable states of affairs
	SUBSTANCE, 			// nouns denoting substances
	TIME, 				// nouns denoting time and temporal relations
	CHANGE, 			// verbs of size, temperature change, intensifying, etc.
	COMPETITION,        // verbs of fighting, athletic activities
	CONSUMPTION, 		// verbs of eating and drinking
	CONTACT, 			// verbs of touching, hitting, tying, digging
	CREATION,           // verbs of sewing, baking, painting, performing
	EMOTION,    		// verbs of feeling
	MOTION,   			// verbs of walking, flying, swimming
	PERCEPTION, 		// verbs of seeing, hearing, feeling
	SOCIAL, 			// verbs of political and social activities and events
	STATIVE, 			// verbs of being, having, spatial relations
	WEATHER, 			// verbs of raining, snowing, thawing, thundering
	ADJ_PPL,  			// adj.ppl participial adjectives
	// Pronouns
	PERSONAL_SUBJECTIVE,// pronouns (i, you, he, she, it, we, you, they)
	PERSONAL_OBJECTIVE, // pronouns (me, you, him, her, it, us, you, them)
	POSSESSIVE,         // pronouns (mine, yours, his, hers, ours, theirs)
	REFLEXIVE,          // pronouns (myself, yourself, himself, herself, itself, oneself, ourselves, yourselves, themselves)
	RECIPROCAL,         // pronouns (each other, one another)
	RELATIVE,           // pronouns (that, which, who, whose, whom, where, when)
	DEMONSTRATIVE,      // pronouns (this, that, these, those)
	INTERROGATIVE,      // pronouns (who, what, why, where, when, whatever)
	INDEFINITE,         // pronouns (anything, anybody, anyone, something, somebody, someone, nothing, nobody, none, no one)
	                    // opp. determiners (a, an)
	// Determiners 
	DEFINITE,           // determiners (the)
    OTHER               // determiners before a noun (any, that, those, this, some, whatever, whichever)
}
