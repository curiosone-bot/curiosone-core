package com.github.bot.curiosone.core.nlp;

/**
 * Contains all sub-tipology of elements in a generic grammar.
 * @see  <a href="https://goo.gl/3Fem5E">List of WordNet lexicographer file names and numbers</a>
 * @see  <a href="https://goo.gl/ThRj4o"> An Introduction to Word Classes</a>
 */
public enum LEX {
  
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
   * Participal adjectives.
   */
  ADJ_PPL,

  /**
   * Nouns: I, You, He, She, It, We, You, They.
   */
  PERSONAL_SUBJECTIVE,

  /**
   * Pronouns: me, you, him, her, it, us, you, them.
   */
  PERSONAL_OBJECTIVE,

  /**
   * Pronouns: mine, yours, his, hers, ours, theirs.
   */
  POSSESSIVE,

  /**
   * Pronouns: myself, yourself, himself, herself, itself, oneself, ourselves,
   * yourselves, themselves.
   */
  REFLEXIVE,

  /**
   * Pronouns: each other, one another.
   */
  RECIPROCAL,

  /**
   * Pronouns: that, which, who, whose, whom, where, when.
   */
  RELATIVE,

  /**
   * Pronouns: this, that, these, those.
   */
  DEMONSTRATIVE,

  /**
   * Pronouns: who, what, why, where, when, whatever.
   */
  INTERROGATIVE,

  /**
   * As pronouns: anything, anybody, anyone, something,
   * somebody, someone, nothing, nobody, none, no one.
   * As determiners: a, an.
   */
  INDEFINITE_ARTICLE,

  /**
   * The.
   */
  DEFINITE_ARTICLE,

  /**
    * Conjunctions: Coordinators (and, or, but).
    */
  COORDINATOR,

  /**
   * Conjunctions: Subordinators (while, because, before, since, till, unless, whereas, wheter).
   */
  SUBORDINATOR,

  /**
   * Interjections.
   */
  GENERIC,
  REGARDS,
  APOLOGIZE,
  GRATITUDE,
  DISGUST,
  SURPRISE,
  PAIN,

  /**
   * Participial adjectives.
   */
  PPL,

  /**
   * Mail address.
   */
  MAIL,

  /**
   * Unknown.
   */
  UNKN,

  /**
   * Indefinite.
   */
  INDEFINITE
}
