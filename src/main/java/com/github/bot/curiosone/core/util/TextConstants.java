package com.github.bot.curiosone.core.util;

/**
 * Stores full or partial text messages so as that they can be easily shared between Classes.
 */
public class TextConstants {

  /**
   * Stores an empty String.
   */
  public static final String EMPTY_STR = "";

  /**
   * String representation of the IllegalArgumentException message thrown in Meaning.setFrequency()
   * method.
   */
  public static final String FREQ_MUST_BE_POS_ERR = "Frequency must be positive";

  /**
   * String representation of the IndexOutOfBoundsException message thrown in ParseTable.get()
   * method.
   */
  public static final String POS_OUTSIDE_ERR = "Position outside the table";

  /**
   * Stores openening square bracket symbol.
   */
  public static final String OPEN_SQ_BRACKET = "[";

  /**
   * Stores closing square bracket symbol.
   */
  public static final String CLOSE_SQ_BRACKET = "]";

  /**
   * Stores the String representation for the comma separator used in toString methods.
   */
  public static final String COMMA_SEP = ", ";

  /**
   * Stores new line character.
   */
  public static final char NEWLINE = '\n';

  /**
   * Stores the String representation for the two dots separator used in toString methods.
   */
  public static final String TWO_DOTS_SEP = ": ";

  /**
   * Stores opening inequality bracket symbol.
   */
  public static final String OPEN_INEQ_BRACKET = "<";

  /**
   * Stores closing inequality bracket symbol.
   */
  public static final String CLOSE_INEQ_BRACKET = ">";

  /**
   * Stores opening circle bracket symbol.
   */
  public static final String OPEN_PARENTHESIS = "(";

  /**
   * Stores a space character followed by the opening parenthesis symbol.
   */
  public static final String SPACE_OPEN_PARENTHESIS = " (";

  /**
   * Stores closing parenthesis symbol.
   */
  public static final String CLOSE_PARENTHESIS = ")";

  /**
   * Stores error message to be used when the Curiosone is not able to understan a Message.
   */
  public static final String SORRY_MY_HEAD_ERROR =
      "Sorry my head hurts, what were we talking about?";

  /**
   * Stores pronoun/determiner "what".
   */
  public static final String WHAT = "what";

  /**
   * Stores pronoun/determiner "who".
   */
  public static final String WHO = "who";

  /**
   * Stores pronoun/determiner "where".
   */
  public static final String WHERE = "where";

  /**
   * Stores part of the "I don't know" answer message.
   */
  public static final String DO_NOT_KNOW = "I do not know ";

  /**
   * Stores part of the "I don't know" answer message.
   */
  public static final String DO_YOU = "! Do you?";

  /**
   * Stores "is a" relation textual representation.
   */
  public static final String IS_A = " is a ";

  /**
   * Stores "is" relation textual representation.
   */
  public static final String IS = " is ";

  /**
   * Stores "is in" relation textual representation.
   */
  public static final String IS_IN = " is in ";

  /**
   * Stores part of the "For what i know" answer message.
   */
  public static final String WHAT_I_KNOW = "For what I know, ";

  /**
   * Stores question mark symbol.
   */
  public static final char QUESTION_MARK = '?';

  /**
   * Stores exclamation mark symbol.
   */
  public static final String EXCL_MARK = "!";

}
