package com.github.bot.curiosone.core.nlp.tokenizer;

import java.util.Arrays;
import java.util.List;

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
   * Acronyms with punctuation.
   */

  private final List<String> acronPunc = Arrays.asList("I.O.U.", "M.D.", "N.B.", "P.O.", "U.K.",
                                                       "U.S.","U.S.A.", "P.S.");
  /**
   * Common words containing periods.
   */

  private final List<String> comWordsPer = Arrays.asList("mr.", "mrs.", ".com", "dr.", "st.");

  /**
   * Common verbs abbreviation.
   */

  private final List<String> commVerbAbbr = Arrays.asList("'s", "'m", "'ll", "'re", "'ve");

  /**
   * Constructor.
   * @param input is a string provided directly from the user
   */

  public Tokenizer(String input) {
    this.input = input;
    type = null;
  }

  /**
   * Tokenizes the string provided in input by the user.
   * @return a new Sentence
   */

  public Sentence getSentence() {
    checkSentenceAndType();
    return new Sentence(null, input, null);
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
