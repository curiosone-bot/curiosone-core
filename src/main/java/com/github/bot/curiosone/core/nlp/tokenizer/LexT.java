package com.github.bot.curiosone.core.nlp.tokenizer;

/**
 * Enumeraton that contains all sub-tipology of elements in a generic grammar.
 * @see com.github.bot.curiosone.core.nlp.tokenizer.Word
 * @see https://wordnet.princeton.edu/man/lexnames.5WN.html
 * @see http://www.ucl.ac.uk/internet-grammar/wordclas/open.htm 
 * @author Andrea Rivitto && Eugenio Schintu
 */
public enum LexT {
    /**
     * All adjective/adverb.
     */
    ALL,
    /**
     * Pertainyms: relational adjectives.
     */
    PERT,
    /**
     * Unique beginner for nouns.
     */
    TOPS,
    /**
     * Nouns denoting acts or actions.
     */
    ACT,
    /**
     * Nouns denoting animals.
     */
    ANIMAL,
    /**
     * Denoting man-made objects.
     */
    ARTIFACT,
    /**
     * Nouns denoting attributes of people and objects.
     */
    ATTRIBUTE,
    /**
     * As nuon: denoting body parts.
     * As verb: verbs of grooming, dressing and bodily care.
     */
    BODY,
    /**
     * As noun: denoting cognitive processes and contents.
     * As verb: verbs of thinking, judging, analyzing, doubting.
     */
    COGNITION,
    /**
     * As noun: denoting communicative processes and contents.
     * As verb: verbs of telling, asking, ordering, singing.
     */
    COMMUNICATION,
    /**
     * Nouns denoting natural events.
     */
    EVENT,
    /**
     * Nouns denoting feelings and emotions.
     */
    FEELING,
    /**
     * Nouns denoting foods and drinks.
     */
    FOOD,
    /**
     * Nouns denoting groupings of people or objects. 
     */
    GROUP,
    /**
     * Nouns denoting spatial position.
     */
    LOCATION,
    /**
     * Nouns denoting goals.
     */
    MOTIVE,
    /**
     * Nouns denoting natural objects (not man-made). 
     */
    OBJECT,
    /**
     * Nouns denoting people.
     */
    PERSON,
    /**
     * Nouns denoting natural phenomena. 
     */
    PHENOMENON,
    /**
     * Nouns denoting plants. 
     */
    PLANT,
    /**
     * As noun: nouns denoting possession and transfer of possession.
     * As verb: verbs of buying, selling, owning.
     */
    POSSESSION,
    /**
     * Nouns denoting natural processes.
     */
    PROCESS,
    /**
     * Nouns denoting quantities and units of measure determiners
     * before a noun (all,both,many,each,every,several,few,enough,no).
     */
    QUANTITY,
    /**
     * Nouns denoting relations between people or things or ideas.
     */
    RELATION,
    /**
     * Nouns denoting two and three dimensional shapes.
     */
    SHAPE,
    /**
     * Nouns denoting stable states of affairs.
     */
    STATE,
    /**
     * Nouns denoting substances.
     */
    SUBSTANCE,
    /**
     * Nouns denoting time and temporal relations.
     */
    TIME,
    /**
     * Verbs of size, temperature change, intensifying, etc.
     */
    CHANGE,
    /**
     * Verbs of fighting, athletic activities.
     */
    COMPETITION,
    /**
     * Verbs of eating and drinking.
     */
    CONSUMPTION,
    /**
     * Verbs of touching, hitting, tying, digging.
     */
    CONTACT,
    /**
     * Verbs of sewing, baking, painting, performing.
     */
    CREATION,
    /**
     * Verbs of feeling.
     */
    EMOTION,
    /**
     * Verbs of walking, flying, swimming.
     */
    MOTION,
    /**
     * Verbs of seeing, hearing, feeling.
     */
    PERCEPTION,
    /**
     * Verbs of political and social activities and events.
     */
    SOCIAL,
    /**
     * Verbs of being, having, spatial relations.
     */
    STATIVE,
    /**
     * Verbs of raining, snowing, thawing, thundering.
     */
    WEATHER,
    /**
     * Participial adjectives.
     */
    ADJ_PPL,
    /**
     * i, you, he, she, it, we, you, they.
     */
    PERSONAL_SUBJECTIVE,
    /**
     * me, you, him, her, it, us, you, them.
     */
    PERSONAL_OBJECTIVE,
    /**
     * mine, yours, his, hers, ours, theirs.
     */
    POSSESSIVE,
    /**
     * myself, yourself, himself, herself, itself, oneself, ourselves, yourselves, themselves.
     */
    REFLEXIVE,
    /**
     * each other, one another.
     */
    RECIPROCAL,
    /**
     * that, which, who, whose, whom, where, when.
     */
    RELATIVE,
    /**
     * this, that, these, those.
     */
    DEMONSTRATIVE,
    /**
     * who, what, why, where, when, whatever.
     */
    INTERROGATIVE,
    /**
     * As pronouns: anything, anybody, anyone, something, 
     * somebody, someone, nothing, nobody, none, no one.
     * As determiners: a, an.
     */
    INDEFINITE,
    /**
     * The.
     */
    DEFINITE,
    /**
     * before a noun (any, that, those, this, some, whatever, whichever).
     */
    OTHER
}
