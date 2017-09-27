package com.github.bot.curiosone.core.extraction;

import com.github.bot.curiosone.core.nlp.Phrase;
import com.github.bot.curiosone.core.nlp.Sentence;

import java.io.IOException;
import java.util.Optional;

/**
 * Description.
 */
public class Brain {
  /**
   * Private Constructor.
   */
  private Brain() {}

  /**
   * Response to a conversational message.
   * @param  phrase the message to generate the response to
   * @return the response if the message needs a conversational answer.
   */
  public static Optional<BrainResponse> conversate(Phrase phrase) {
    Optional<BrainResponse> answ = Conversation.getAnswer(phrase);
    if (answ.isPresent()) {
      return answ;
    }
    return BadWords.getAnswer(phrase);
  }

  /**
   * Generate a random response.
   * @param  phrase the message to generate the response to
   * @return the response.
   */
  public static BrainResponse random(Phrase phrase) {
    return RandomAnswer.getAnswer(phrase);
  }

  /**
   * Response to something.
   * @param  sentence the sentence to generate the response to
   * @param  scope the scope of the conversation
   */
  public static Optional<BrainResponse> compute(Sentence sentence, String scope) {
    if (sentence.isQuestion()) {
      return Question.getAnswer(sentence, scope);
    }
    return Affirmation.getAnswer(sentence, scope);
  }
}
