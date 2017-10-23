package com.github.bot.curiosone.core.refinement;

import com.github.bot.curiosone.core.refinement.interfaces.Clause;

/**
 * Represents a semantically refined Clause.
 * Provides methods to create the semantically refined Clause and to retrieve its contents, such as
 * the subject and the predicate.
 * @see  Clause The Clause Interface
 */
public class ClauseMain implements Clause {

  /**
   * Stores the subject of this semantically refined Clause.
   */
  private PhraseNoun subject;

  /**
   * Stores the predicate of this semantically refined Clause.
   */
  private PhraseVerb predicate;

  /**
   * Constructs a semantically refined Clause with a given subject and its predicate.
   * @param  subject
   *         the subject of this semantically refined Clause
   * @param  predicate
   *         the predicate of this semantically refined Clause
   * @see  PhraseNoun The PhraseNoun class
   * @see  PhraseVerb The PhraseVerb class
   */
  public ClauseMain(PhraseNoun subject, PhraseVerb predicate) {
    this.subject   = subject;
    this.predicate = predicate;
  }

  /**
   * Gets the subject of this semantically refined Clause.
   * @return  the subject of this semantically refined Clause
   * @see  PhraseNoun The PhraseNoun Class
   */
  public PhraseNoun getSubject() {
    return subject;
  }

  /**
   * Gets the predicate of this semantically refined Clause.
   * @return  the predicate of this semantically refined Clause
   * @see  PhraseVerb The PhraseVerb Class
   */
  public PhraseVerb getPredicate() {
    return predicate;
  }

  /**
   * Returns a String representation of this semantically refined Clause, in the form:
   * [subject predicate].
   * @return  a String representation of this semantically refined Clause
   */
  @Override
  public String toString() {
    return subject.toString() + " " + predicate.toString();
  }

}
