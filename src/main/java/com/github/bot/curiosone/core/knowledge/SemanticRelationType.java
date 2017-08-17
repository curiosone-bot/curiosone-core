package com.github.bot.curiosone.core.knowledge;

/**
 * Semantic Relation Type that can be used between two Concepts.
 * @author Christian Sordi
 */
public enum SemanticRelationType {
  /**
   * Identificatori univoci dell'enumerazioni.
   */
  HYPERONYM, // e' un iperonimo
  ANTONYM, // e' il contrario di
  HYPONYM, // e' un iponimo
  MERONYM_PART, // e' parte di
  SIMILIAR_TO, // simile a
  USAGE, // viene usato per
  TIME, // Data

  // sono solo una parte dei Pointer relativi a Wordnet
}
