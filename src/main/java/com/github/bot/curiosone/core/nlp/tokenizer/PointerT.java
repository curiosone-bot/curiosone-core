package com.github.bot.curiosone.core.nlp.tokenizer;

/**
 * Lexical/semantic link.
 *
 * @author Andrea Rivitto && Eugenio Schintu
 * @see https://web.stanford.edu/class/cs276a/projects/docs/jwnl/javadoc/net/didion/jwnl/data/PointerType.html
 */

public enum PointerT {

  /**
   * Antonym.
   */

  ANTONYM,

  /**
   * Attribute.
   */

  ATTRIBUTE,

  /**
   * Cause.
   */

  CAUSE,

  /**
   * Derived.
   */

  DERIVED,

  /**
   * Entailed by.
   */

  ENTAILED_BY,

  /**
   * Entailment.
   */

  ENTAILMENT,

  /**
   * Hyperonym.
   */

  HYPERNYM,

  /**
   * Hyponym.
   */

  HYPONYM,

  /**
   * Member of holonym.
   */

  MEMBER_HOLONYM,

  /**
   * Member of meronym.
   */

  MEMBER_MERONYM,

  /**
   * Part of holonym.
   */

  PART_HOLONYM,

  /**
   * Part of meronym.
   */

  PART_MERONYM,

  /**
   * Participle of.
   */

  PARTICIPLE_OF,

  /**
   * See also.
   */

  SEE_ALSO,

  /**
   * Similar.
   */

  SIMILAR_TO,

  /**
   * Substance of holomyn.
   */

  SUBSTANCE_HOLONYM,

  /**
   * Substanc of meronym.
   */

  SUBSTANCE_MERONYM,

  /**
   * Verb group.
   */

  VERB_GROUP
}