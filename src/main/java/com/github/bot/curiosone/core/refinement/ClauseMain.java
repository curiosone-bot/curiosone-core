package com.github.bot.curiosone.core.refinement;

import com.github.bot.curiosone.core.refinement.interfaces.Clause;
import com.github.bot.curiosone.core.refinement.interfaces.Phrase;

public class ClauseMain implements Clause {

  private Phrase subject;
  private Phrase predicate;
  
  /**
   * Clause constructor.
   * @param subject subject
   * @param predicate predicate
   */
  public ClauseMain(Phrase subject, Phrase predicate) {
    this.subject   = subject;
    this.predicate = predicate;
  }
  
  @Override
  public String toString() {
    return "MC { " + subject + " " + predicate + " }";
  }

}
