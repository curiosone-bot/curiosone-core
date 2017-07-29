package com.github.bot.curiosone.core.nlp.tokenizer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.github.bot.curiosone.core.nlp.tokenizer.interfaces.IToken;

/**
 * Tokenization of the input string.
 * @author Eugenio Schintu && Andrea Rivitto
 */

public class Tokenizer {

  /**
   * String provided in input by the user.
   */

  private String input;

  /**
   * Typology of Sentence.
   * @see SentenceT
   */

  private SentenceT type;

  /**
   * Generated tokens' list.
   */

  private List<IToken> tokens;

  /**
   * Alphanumeric values' list.
   */

  private final List<String> alphaNum = Arrays.asList("a","b","c","d","e","f","g","h","j","k","i",
      "l", "m","n","o","p","q","r","s","t","u","v","w", "x", "y", "z","1", "2","3","4", "5",
      "6", "7", "8", "9", "0");

  /**
   * Most common verbs abbreviation.
   */

  private final List<String> commVerbAbbr = Arrays.asList("'s", "'m", "'ll", "'re", "'ve", "'d");

  /**
   * Constructor.
   * @param input is a string provided directly from the user
   */

  public Tokenizer(String input) {
    this.input = input;
    type = null;
    tokens = new ArrayList<IToken>();
  }

  /**
   * Tokenizes the string provided in input by the user.
   * @return a new Sentence
   */

  public Sentence getSentence() {
    checkSentenceAndType();
    return new Sentence(getType(), input, tokens);
  }

  /**
   * Detected in the {@link #input} if there is more than one sentence, and if it contains more
   * than one sentence, {@link #type} has a special value.
   * Only if the number of sentence in the input string is equal to one,
   * it sees if the {@link #input} is a question or an affirmation and set a {@link #type}.
   */
  public void checkSentenceAndType() {
    if (input.charAt(input.length() - 1) == '?') {
      setType(SentenceT.QUESTION);
    } else if (input.charAt(input.length() - 1) == '!' || input.charAt(input.length() - 1) == '.') {
      setType(SentenceT.ANSWER);
    }

    for (int i = 0; i > input.length(); i++) {
      if ((input.charAt(i) == '?' || input.charAt(i) == '!') && i != input.length() - 1) {
        setType(SentenceT.MORE_SENTENCE);
        break;
      }
    }
  }

  /**
   * This method checks if in {@link #input} there is an incorrect punctuation.
   * @return {@link #input} cleaned from punctuation errors
   */

  public String checkPunct() {
    StringBuilder sb = new StringBuilder(input);
    for (int i = 0; i < sb.length(); i++) {
      char letter = sb.charAt(i);
      if (!alphaNum.contains(letter)) {
        if (i > 0) {
          if (Character.isDigit(sb.charAt(i)-1 )) {

          }
        }
      }
    }
    return sb.toString();
  }

  /**
   * Get the type.
   * @return type
   */

  public SentenceT getType() {
    return type;
  }

  /**
   * Set {@link #type} to a new value provided in input.
   * @param t of new value
   * @return type modified
   */

  public SentenceT setType(SentenceT t) {
    return this.type = t;
  }
}
