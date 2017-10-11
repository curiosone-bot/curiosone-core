package com.github.bot.curiosone.core.extraction;

import com.github.bot.curiosone.core.nlp.Phrase;
import com.github.bot.curiosone.core.nlp.Sentence;

import java.io.IOException;
import java.util.Optional;

/**
 * Manages the Brain of the Curiosone.
 * Contains utility methods to conversate, compute and/or randomly generate an answer to a given
 * Sentence.
 * @see Phrase The Phrase Class
 * @see Sentence The Sentence Class
 */
public class Brain {

  /**
   * Private Constructor.
   */
  private Brain() {}

  /**
   * Answers to a conversational message.
   * @param  phrase
   *         the message to generate the response to
   * @return  an Optional instance, containing the response, if the message needs a conversational
   *          answer. The response is encapsulated in a BrainResponse instance.
   * @see  Phrase The Phrase Class
   * @see  Optional The Optional Class
   * @see  BrainResponse The BrainResponse Class
   */
  public static Optional<BrainResponse> conversate(Phrase phrase) {
    Optional<BrainResponse> answ = Conversation.getAnswer(phrase);
    return answ.isPresent() ? answ : BadWords.getAnswer(phrase);
  }

  /**
   * Generates a random response.
   * @param  phrase
   *         the message to generate the response to
   * @return  a BrainResponse instance, representing the randomly generated response.
   * @see  Phrase The Phrase Class
   * @see  BrainResponse The BrainResponse Class
   */
  public static BrainResponse random(Phrase phrase) {
    return RandomAnswer.getAnswer(phrase);
  }

  /**
   * Answers to a sentence.
   * This method is able to undestrad whether the Curiosone is responding to an affirmation or a
   * question.
   * @param  sentence
   *         the sentence to generate the response to
   * @param  scope
   *         the scope of the conversation
   * @return  an Optional instance, containing the response for the input Sentence
   * @see  Phrase The Phrase Class
   * @see  Optional The Optional Class
   * @see  BrainResponse The BrainResponse Class
   */
  public static Optional<BrainResponse> compute(Sentence sentence, String scope) {
    return sentence.isQuestion()
        ? Question.getAnswer(sentence, scope) : Affirmation.getAnswer(sentence, scope);
  }
}
