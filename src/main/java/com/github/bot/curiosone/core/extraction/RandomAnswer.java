package com.github.bot.curiosone.core.extraction;

import com.github.bot.curiosone.core.nlp.Phrase;
import com.github.bot.curiosone.core.nlp.Token;

import java.util.List;
import java.util.Optional;

/**
 * Handles precomputed answers to sentences not understood by the chatbot.
 * Contains a method to get a randomly generated answer.
 * @see  com.github.bot.curiosone.core.nlp.Phrase The Phrase Class
 * @see  com.github.bot.curiosone.core.extraction.BrainResponse The BrainResponse Class
 */
public class RandomAnswer {

  /**
   * Contains constants added to the answer when the input is made by a single word.
   */
  private static final String[] CONSTANTS = {
      "?",
      "? I do not understand",
      ".. that is cool"
  };

  /**
   * Contains answers to a not-english sentence.
   */
  private static final String[] ENGLISH_ANSWERS = {
      "I think you should speak english",
      "PLEASE, speak english!",
      "Are you a robot too?"
  };

  /**
   * Contains answers for too much general/not understood Sentences.
   */
  private static final String[] GENERAL_ANSWERS = {
      "What a nice day to talk to a chatbot!",
      "Can you reformulate your sentence please?",
      "Tell me something interesting please."
  };

  /**
   * Private constructor.
   */
  private RandomAnswer() { }

  /**
   * Gives a general answer to a general user input.
   * @param  phrase
   *         given in input
   * @return  a BrainResponse instance, containing a general answer
   * @see  com.github.bot.curiosone.core.nlp.Phrase The Sentence Phrase
   * @see  com.github.bot.curiosone.core.extraction.BrainResponse The BrainResponse Class
   */
  public static BrainResponse getAnswer(Phrase phrase) {
    List<Token> tokenList = phrase.getTokens();

    if (tokenList.size() == 1) {
      return new BrainResponse(phrase.getText() + CONSTANTS[randomIndex(CONSTANTS)], "");
    } else {
      long count = tokenList.stream().map(Token::isKnown).filter(x -> !x).count();
      if (count > tokenList.size() / 2) {
        return new BrainResponse(ENGLISH_ANSWERS[randomIndex(ENGLISH_ANSWERS)], "");
      }
    }
    return new BrainResponse(GENERAL_ANSWERS[randomIndex(GENERAL_ANSWERS)], "");
  }

  /**
   * Returns a random index of the array given in input.
   * @param  array
   *         whose index is needed
   * @return  the randomly generated index
   */
  private static int randomIndex(String[] array) {
    return (int)(Math.random() * array.length);
  }
}
