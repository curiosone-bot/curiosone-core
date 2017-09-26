package com.github.bot.curiosone.core.knowledge;

import com.ibm.icu.impl.Relation;

import java.util.List;

import java.util.Objects;


/**
 * Resumes a semantic query parameters.
 *
 */
public class SemanticQuery {


  private SemanticRelationType relation;
  
  private String subject;
  
  private String object;
  
  private List<String> objAdjectives;
  
  private String verb;
  

  /**
   * Constructor.
   * @param relation {@link Relation}
   * @param subject Affirmation subject.
   * @param object Question/affirmation object
   * @param adjectives Object adjectives
   * @param verb Sentence verb.
   */
  public SemanticQuery(SemanticRelationType relation, String subject,String object,
      List<String> adjectives, String verb) {
    
    this.relation = relation;
    this.subject = subject;
    this.object = object;
    this.objAdjectives = adjectives;
    this.verb = verb;
  }
  
  public SemanticQuery(SemanticRelationType relation,String subject, String object, String verb) {

    this(relation, subject, object, null, verb);
  }
  
  public SemanticQuery(SemanticRelationType relation, String object,
      List<String> adjectives, String verb) {

    this(relation, null, object, adjectives, verb);
  }
  
  public SemanticQuery(SemanticRelationType relation, String object, String verb) {

    this(relation, null, object, null, verb);
  }
  
  public String getObject() {
    return object;
  }
  
  public String getSubject() {
    return subject;
  }

  public List<String> getAdjectives() {
    return objAdjectives;
  }

  public SemanticRelationType getRelation() {
    return relation;
  }

  public String getVerb() {
    return verb;
  }
  
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

  @Override
  public int hashCode() {
    return Objects.hash(relation, subject, object, verb);
  }
  
}
