package com.github.bot.curiosone.core.extraction;

import com.github.bot.curiosone.core.nlp.Phrase;
import com.github.bot.curiosone.core.nlp.Sentence;

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
  public static Optional<String> conversate(Phrase phrase) {
    //TODO: This method should return an optional BrainResponse
    //TODO: Convesation should return an optional BrainResponse
    String answer = Conversation.getAnswer(phrase);
    if (answer.equals("")) {
      return Optional.empty();
    }
    return Optional.of(answer);
  }

  /**
   * compute description.
   * @param  sentence [description]
   * @param  scope [description]
   */
  public static Optional<BrainResponse> compute(Sentence sentence, String scope) {
    if (sentence.isQuestion()) {
      return Question.getAnswer(sentence, scope);
    }
    return Affirmation.getAnswer(sentence, scope);
  }
}
