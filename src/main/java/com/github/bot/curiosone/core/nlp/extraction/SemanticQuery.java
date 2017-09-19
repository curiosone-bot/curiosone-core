package com.github.bot.curiosone.core.nlp.extraction;

import java.util.List;
import com.github.bot.curiosone.core.knowledge.SemanticRelationType;

/**
 * Semantic network search token.
 *
 */
public class SemanticQuery {
  
  /**
   * Question/affirmation object.
   */
  private String object;
  
  /**
   * Affirmation subject.
   */
  private String subject;
  
  /**
   * Object adjectives;
   */
  private List<String> adjectives;
  
  /**
   * Sentence verb.
   */
  private String verb;
  
  /**
   * @see SemanticRelationType
   */
  private SemanticRelationType relation;

  public SemanticQuery(SemanticRelationType relation, String subject, String object, List<String> adjectives, String verb) {
    
    this.relation = relation;
    this.subject = subject;
    this.object = object;
    this.adjectives = adjectives;
    this.verb = verb;
  }
  
  public SemanticQuery(SemanticRelationType relation, String object, List<String> adjectives, String verb) {

    this(relation, null, object, adjectives, verb);
  }

  public String getObject() {
    return object;
  }
  
  public String getSubject() {
    return subject;
  }

  public List<String> getAdjectives() {
    return adjectives;
  }

  public SemanticRelationType getRelation() {
    return relation;
  }

  public String getVerb() {
    return verb;
  }

  
}
