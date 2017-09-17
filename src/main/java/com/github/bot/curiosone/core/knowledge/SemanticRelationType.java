package com.github.bot.curiosone.core.knowledge;

/**
 * Semantic Relation Type that can be used between two Concepts.
 * @author Christian Sordi
 */
public enum SemanticRelationType {
  /**
   * Also See.
   */

  ALSO_SEE,

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
   * Derivationally related.
   */

  DERIVATIONALLY_RELATED,

  /**
   * Entailment.
   */

  ENTAILMENT,

  /**
   * Hyperonym.
   */

  HYPERNYM,

  /**
   * Hyperonym instance.
   */

  HYPERNYM_INSTANCE,

  /**
   * Hyponym.
   */

  HYPONYM,

  /**
   * Hyponym instance.
   */

  HYPONYM_INSTANCE,

  /**
   * Member holonym.
   */

  HOLONYM_MEMBER,

  /**
   * Holonym substance.
   */

  HOLONYM_SUBSTANCE,

  /**
   * Part holonym.
   */

  HOLONYM_PART,

  /**
   * Meronym member.
   */

  MERONYM_MEMBER,

  /**
   * Meronym substance.
   */
  
  MERONYM_SUBSTANCE,

  /**
   * Meronym part.
   */

  MERONYM_PART,

  /**
   * Participle.
   */

  PARTICIPLE,

  /**
   * Pertainym.
   */

  PERTAINYM,

  /**
   * Region.
   */

  REGION,

  /**
   * Region member.
   */

  REGION_MEMBER,

  /**
   * is a.
   */
  SEMANTICALLY_RELATED,
  /**
   * Similar.
   */

  SIMILAR_TO,

  /**
   * Topic.
   */

  TOPIC,

  /**
   * Topic member.
   */

  TOPIC_MEMBER,

  /**
   * Usage.
   */

  USAGE,

  /**
   * Usage member.
   */

  USAGE_MEMBER,

  /**
   * Verb group.
   */

  VERB_GROUP
}