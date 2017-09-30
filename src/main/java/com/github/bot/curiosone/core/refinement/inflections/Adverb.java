package com.github.bot.curiosone.core.refinement.inflections;
/**
 * Adverb's inflections.
 * @author Claudio Venanzi
 */

public enum Adverb implements Inflection {
  Adjective("ADJECTIVE");
  
  private String category;
  
  private Adverb(String category) {
    this.category = category;
  }
  
  @Override
  public String toString() {
    return category;
  }
}
