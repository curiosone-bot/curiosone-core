package com.github.bot.curiosone.core.refinement;

import com.github.bot.curiosone.core.refinement.interfaces.Clause;

public class MainClause implements Clause {

  private NounPhrase subject;
  private VerbPhrase predicate;
  
  /**
   * Clause constructor.
   * @param subject subject
   * @param predicate predicate
   */
  public MainClause(NounPhrase subject, VerbPhrase predicate) {
    this.subject   = subject;
    this.predicate = predicate;
  }
  
  /**
   * Returns the subject phrase.
   * @return phrase
   */
  public NounPhrase getSubject() {
    return subject;
  }

  /**
   * Returns the predicate phrase.
   * @return phrase
   */
  public VerbPhrase getPredicate() {
    return predicate;
  }

  @Override
  public String toString() {
    return subject.toString() + " " + predicate.toString();
  }

}
