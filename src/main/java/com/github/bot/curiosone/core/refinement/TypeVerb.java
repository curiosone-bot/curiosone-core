package com.github.bot.curiosone.core.refinement;

/**
 * Stores a verb classification.
 */
public enum TypeVerb {
  PresentS1("PRESENT, SINGULAR, FIRST_PERSON"),
  PresentS2("PRESENT, SINGULAR, SECOND_PERSON"),
  PresentS3("PRESENT, SINGULAR, THIRD_PERSON"),
  PresentP1("PRESENT, PLURAL, FIRST_PERSON"),
  PresentP2("PRESENT, PLURAL, SECOND_PERSON"),
  PresentP3("PRESENT, PLURAL, THIRD_PERSON"),

  SimplePastS1("SIMPLE_PAST, SINGULAR, FIRST_PERSON"),
  SimplePastS2("SIMPLE_PAST, SINGULAR, SECOND_PERSON"),
  SimplePastS3("SIMPLE_PAST, SINGULAR, THIRD_PERSON"),
  SimplePastP1("SIMPLE_PAST, PLURAL, FIRST_PERSON"),
  SimplePastP2("SIMPLE_PAST, PLURAL, SECOND_PERSON"),
  SimplePastP3("SIMPLE_PAST, PLURAL, THIRD_PERSON"),

  ConditionalS1("CONDITIONAL, SINGULAR, FIRST_PERSON"),
  ConditionalS2("CONDITIONAL, SINGULAR, SECOND_PERSON"),
  ConditionalS3("CONDITIONAL, SINGULAR, THIRD_PERSON"),
  ConditionalP1("CONDITIONAL, PLURAL, FIRST_PERSON"),
  ConditionalP2("CONDITIONAL, PLURAL, SECOND_PERSON"),
  ConditionalP3("CONDITIONAL, PLURAL, THIRD_PERSON"),

  FutureSimpleS1("FUTURE_SIMPLE, SINGULAR, FIRST_PERSON"),
  FutureSimpleS2("FUTURE_SIMPLE, SINGULAR, SECOND_PERSON"),
  FutureSimpleS3("FUTURE_SIMPLE, SINGULAR, THIRD_PERSON"),
  FutureSimpleP1("FUTURE_SIMPLE, PLURAL, FIRST_PERSON"),
  FutureSimpleP2("FUTURE_SIMPLE, PLURAL, SECOND_PERSON"),
  FutureSimpleP3("FUTURE_SIMPLE, PLURAL, THIRD_PERSON"),

  ParticiplePastS1("SINGULAR, PAST, PARTICIPLE, FIRST_PERSON"),
  ParticiplePastS2("SINGULAR, PAST, PARTICIPLE, SECOND_PERSON"),
  ParticiplePastS3("SINGULAR, PAST, PARTICIPLE, THIRD_PERSON"),
  ParticiplePastP1("PAST, PARTICIPLE, PLURAL, FIRST_PERSON"),
  ParticiplePastP2("PAST, PARTICIPLE, PLURAL, SECOND_PERSON"),
  ParticiplePastP3("PAST, PARTICIPLE, PLURAL, THIRD_PERSON"),

  ParticiplePresentS1("PRESENT, SINGULAR, PARTICIPLE, FIRST_PERSON"),
  ParticiplePresentS2("PRESENT, SINGULAR, PARTICIPLE, SECOND_PERSON"),
  ParticiplePresentS3("PRESENT, SINGULAR, PARTICIPLE, THIRD_PERSON"),
  ParticiplePresentP1("PRESENT, PARTICIPLE, PLURAL, FIRST_PERSON"),
  ParticiplePresentP2("PRESENT, PARTICIPLE, PLURAL, SECOND_PERSON"),
  ParticiplePresentP3("PRESENT, PARTICIPLE, PLURAL, THIRD_PERSON"),

  Infinitive("INFINITIVE");

  /**
   * Stores a String representation of the verb classification.
   */
  private String category;

  /**
   * Sets the String representation of the verb classification.
   * @param  category
   *         the desired classification
   */
  private TypeVerb(String category) {
    this.category = category;
  }

  /**
   * Returns the String representation of the classification.
   * @return  the String representation of the classification.
   */
  public String toCategory() {
    return category;
  }
}
