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

  DERIVATIONALLY_RELATED_FORM,

  /**
   * Derived from adjective.
   */

  DERIVED_FROM_ADJ,

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

  INSTANCE_HYPERNYM,

  /**
   * Hyponym.
   */

  HYPONYM,

  /**
   * Hyponym instance.
   */

  INSTANCE_HYPONYM,

  /**
   * Member holonym.
   */

  MEMBER_HOLONYM,

  /**
   * Holonym substance.
   */

  SUBSTANCE_HOLONYM,

  /**
   * Part holonym.
   */

  PART_HOLONYM,

  /**
   * Meronym member.
   */

  MEMBER_MERONYM,

  /**
   * Meronym substance.
   */

  SUBSTANCE_MERONYM,

  /**
   * Meronym part.
   */

  PART_MERONYM,

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