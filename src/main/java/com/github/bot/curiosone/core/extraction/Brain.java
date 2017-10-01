package com.github.bot.curiosone.core.extraction;

import com.github.bot.curiosone.core.nlp.Phrase;
import com.github.bot.curiosone.core.nlp.Sentence;

import java.io.IOException;
import java.util.Optional;

/**
 * Manages the brain of the Curiosone.
 * This class contains utility methods to conversate, compute and/or randomly generate an answer to
 * a given sentence.
 */
public class Brain {
  /**
   * Private Constructor.
   */
  private Brain() {}

  /**
   * Answers to a conversational message.
   * @param phrase the message to generate the response to
   * @return an Optional instance, containing the response, if the message needs a conversational
   *         answer.
   */
  public static Optional<BrainResponse> conversate(Phrase phrase) {
    Optional<BrainResponse> answ = Conversation.getAnswer(phrase);
    return answ.isPresent() ? answ : BadWords.getAnswer(phrase);
  }

  /**
   * Generates a random response.
   * @param  phrase the message to generate the response to
   * @return a BrainResponse instance, representing the random response.
   */
  public static BrainResponse random(Phrase phrase) {
    return RandomAnswer.getAnswer(phrase);
  }

  /**
   * Answers to a sentence.
   * @param  sentence the sentence to generate the response to
   * @param  scope the scope of the conversation
   * @return an Optional instance, containing the response for the input sentence
   */
  public static Optional<BrainResponse> compute(Sentence sentence, String scope) {
    if (sentence.isQuestion()) {
      return Question.getAnswer(sentence, scope);
    }
    return Affirmation.getAnswer(sentence, scope);
  }
}
