package com.github.bot.curiosone.core.refinement.inflections;
/**
 * Noun's inflections.
 * @author Claudio Venanzi
 */

public enum Noun implements Inflection {
  Singular("SINGULAR"),
  Plural("PLURAL");
  
  private String category;
  
  private Noun(String category) {
    this.category = category;
  }
  
  @Override
  public String toString() {
    return category;
  }
}