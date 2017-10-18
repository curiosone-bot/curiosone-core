package com.github.bot.curiosone.core.refinement;

import com.github.bot.curiosone.core.refinement.interfaces.Clause;

public class ClauseMain implements Clause {

  private PhraseNoun subject;
  private PhraseVerb predicate;
  
  /**
   * Clause constructor.
   * @param subject subject
   * @param predicate predicate
   */
  public ClauseMain(PhraseNoun subject, PhraseVerb predicate) {
    this.subject   = subject;
    this.predicate = predicate;
  }
  
  /**
   * Returns the subject phrase.
   * @return phrase
   */
  public PhraseNoun getSubject() {
    return subject;
  }

  /**
   * Returns the predicate phrase.
   * @return phrase
   */
  public PhraseVerb getPredicate() {
    return predicate;
  }

  @Override
  public String toString() {
    return subject.toString() + " " + predicate.toString();
  }

}
