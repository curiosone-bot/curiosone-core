package com.github.bot.curiosone.core.refinement;

/**
 * Stores the possible types for a Noun.
 */
public enum TypeNoun {
  Singular("SINGULAR"),
  Plural("PLURAL"),
  Name("");

  /**
   * Stores a String representation of the category of a noun.
   */
  private String category;

  /**
   * Sets the category.
   * @param  category
   *         the category to be set
   */
  private TypeNoun(String category) {
    this.category = category;
  }

  /**
   * Returns a String representation of the category.
   * @return  a String representation of the category
   */
  public String toCategory() {
    return category;
  }

}
