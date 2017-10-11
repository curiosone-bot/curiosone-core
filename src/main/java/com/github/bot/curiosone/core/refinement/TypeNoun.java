package com.github.bot.curiosone.core.refinement;

/**
 * Type of noun.
 */
public enum TypeNoun {
  Singular("SINGULAR"),
  Plural("PLURAL"),
  Name("");
  
  private String category;
  
  private TypeNoun(String category) {
    this.category = category;
  }
  
  /**
   * Return noun's category.
   * @return category
   */
  public String toCategory() {
    return category;
  }
  
}
