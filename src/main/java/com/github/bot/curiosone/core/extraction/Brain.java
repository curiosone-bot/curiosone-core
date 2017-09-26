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
    return Conversation.getAnswer(phrase);
  }

  /**
   * Response to something.
   * @param  sentence the sentence to generate the response to
   * @param  scope the scope of the conversation
   * @throws IOException 
   */
  public static Optional<BrainResponse> compute(Sentence sentence, String scope) throws IOException {
    if (sentence.isQuestion()) {
      return Question.getAnswer(sentence, scope);
    }
    return Affirmation.getAnswer(sentence, scope);
  }
}
