package com.github.bot.curiosone.core.refinement.inflections;
/**
 * @author Claudio Venanzi
 */

public enum Adjective implements Inflection {
  Comparative("COMPARATIVE"),
  Superlative("SUPERLATIVE");
  
  private String category;
  
  private Adjective(String category) {
    this.category = category;
  }
  
  @Override
  public String toString() {
    return category;
  }
}
