package com.github.bot.curiosone.core.knowledge;

import java.util.List;

import java.util.Objects;

/**
 * Handles interrogations to the SemanticNetwork.
 * Provides methods to create and manage an interrogation to the SemanticNetwork.
 * @see  SemanticNetwork The SemanticNetwork Class
 */
public class SemanticQuery {

  /**
   * Stores the SemanticRelationType of this interrogation.
   * @see  SemanticRelationType The SemanticRelationType Enum
   */
  private SemanticRelationType relation;

  /**
   * Stores the subject of this interrogaiton.
   */
  private String subject;

  /**
   * Stores the object of this interrogaiton.
   */
  private String object;

  /**
   * Lists the adjectives of the object of this SemanticQuery.
   */
  private List<String> objAdjectives;

  /**
   * Stores the verb of this verb.
   */
  private String verb;

  /**
   * Constructs this SemanticQuery.
   * @param  relation
   *         the SemanticRelationType of this SemanticQuery
   * @param  subject
   *         the subject of this SemanticQuery
   * @param  object
   *         the object of this SemanticQuery
   * @param  adjectives
   *         the List of the adjectives of this SemanticQuery
   * @param  verb
   *         the verb of this SemanticQuery
   */
  public SemanticQuery(SemanticRelationType relation, String subject,String object,
      List<String> adjectives, String verb) {

    this.relation = relation;
    this.subject = subject;
    this.object = object;
    this.objAdjectives = adjectives;
    this.verb = verb;
  }

  /**
   * Constructs this SemanticQuery.
   * @param  relation
   *         the SemanticRelationType of this SemanticQuery
   * @param  subject
   *         the subject of this SemanticQuery
   * @param  object
   *         the object of this SemanticQuery
   * @param  verb
   *         the verb of this SemanticQuery
   */
  public SemanticQuery(SemanticRelationType relation,String subject, String object, String verb) {

    this(relation, subject, object, null, verb);
  }

  /**
   * Constructs this SemanticQuery.
   * @param  relation
   *         the SemanticRelationType of this SemanticQuery
   * @param  object
   *         the object of this SemanticQuery
   * @param  adjectives
   *         the List of the adjectives of this SemanticQuery
   * @param  verb
   *         the verb of this SemanticQuery
   */
  public SemanticQuery(SemanticRelationType relation, String object,
      List<String> adjectives, String verb) {

    this(relation, null, object, adjectives, verb);
  }

  /**
   * Constructs this SemanticQuery.
   * @param  relation
   *         the SemanticRelationType of this SemanticQuery
   * @param  object
   *         the object of this SemanticQuery
   * @param  verb
   *         the verb of this SemanticQuery
   */
  public SemanticQuery(SemanticRelationType relation, String object, String verb) {

    this(relation, null, object, null, verb);
  }

  /**
   * Gets the object of this SemanticQuery.
   * @return  the object of this SemanticQuery
   */
  public String getObject() {
    return object;
  }

  /**
   * Gets the subject of this SemanticQuery.
   * @return  the subject of this SemanticQuery
   */
  public String getSubject() {
    return subject;
  }

  /**
   * Gets the adjectives of this SemanticQuery.
   * @return  a List containing all the adjectives of this SemanticQuery
   */
  public List<String> getAdjectives() {
    return objAdjectives;
  }

  /**
   * Gets the relation of this SemanticQuery.
   * @return  the relation of this SemanticQuery
   */
  public SemanticRelationType getRelation() {
    return relation;
  }

  /**
   * Gets the verb of this SemanticQuery.
   * @return  the verb of this SemanticQuery
   */
  public String getVerb() {
    return verb;
  }

  /**
   * Checks whether this SemanticQuery equals to the given Object.
   * @param  other
   *         the other object to be compared against
   * @return  {@code true} if this SemanticQuery equals to the given object;
   *          {@code false} otherwise
   */
  @Override
  public boolean equals(Object other) {
    if (other == this) {
      return true;
    }
    if (other == null || other.getClass() != this.getClass()) {
      return false;
    }
    SemanticQuery that = (SemanticQuery) other;
    return this.relation.equals(that.relation)
        && this.subject.equals(that.subject)
        && this.object.equals(that.object)
        && this.verb.equals(that.verb);
  }

  /**
   * Calculates the HashCode of this SemanticQuery.
   * The HashCode depends on the relation, subject, object and verb of this SemanticQuery.
   * @return  the HashCode of this SemanticQuery
   */
  @Override
  public int hashCode() {
    return Objects.hash(relation, subject, object, verb);
  }
}
