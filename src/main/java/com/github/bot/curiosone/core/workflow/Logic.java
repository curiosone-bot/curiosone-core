package com.github.bot.curiosone.core.workflow;

import com.github.bot.curiosone.core.analysis.EmotionAnalysis;
import com.github.bot.curiosone.core.extraction.Brain;
import com.github.bot.curiosone.core.extraction.BrainResponse;
import com.github.bot.curiosone.core.nlp.Phrase;
import com.github.bot.curiosone.core.nlp.Sentence;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

/**
 * Manages the Logic Layer of the Curiosone.
 * This class consists of a static method used to generate an answer to a provided message.
 * @see  com.github.bot.curiosone.core.workflow.Message The Message Class
 */
public class Logic {

  /**
   * Tries to compute an answer to a given Message.
   * @param  msg
   *         the Message to be answered. Can be null.
   * @return a Message instance.
   *         If an answer has been found, the instance contains a choerent reply body.
   *         Otherwise, a default message is embedded in the Message instance
   * @see  com.github.bot.curiosone.core.workflow.Message The Message Class
   * @see  com.github.bot.curiosone.core.extraction.Brain The Brain Class
   * @see  com.github.bot.curiosone.core.extraction.BrainResponse The BrainResponse Class
   * @see  com.github.bot.curiosone.core.nlp.Phrase The Phrase Class
   * @see  com.github.bot.curiosone.core.nlp.Sentence The Sentence Class
   * @see  com.github.bot.curiosone.core.nlp.Phrase The Phrase Class
   * @see  java.util.Optional The Optional Class
   */
  public static Message talk(Message msg) {
    // We are not able to parse a null string :(
    if (msg == null) {
      return new Message("Sorry my head hurts, what were we talking about?", "", "");
    }
    Optional<BrainResponse> br;

    // We just use the first now.
    List<Phrase> phrases = Phrase.extract(msg.getMessage());
    if (phrases.size() == 0) {
      return new Message("Sorry my head hurts, what were we talking about?", "", "");
    }
    Phrase phrase = phrases.get(0);

    // Analysis
    String emotion = EmotionAnalysis.getEmotion(phrase);

    // If it's a conversational text answer directly.
    br = Brain.conversate(phrase);
    if (br.isPresent()) {
      BrainResponse answer = br.get();
      return new Message(answer.getMessage(), answer.getScope(), emotion);
    }

    // We just use the first now.
    List<Sentence> sentences = Sentence.extract(phrase);
    if (sentences.size() == 0) {
      BrainResponse answer = Brain.random(phrase);
      return new Message(answer.getMessage(), answer.getScope(), emotion);
    }
    Sentence sentence = sentences.get(0);

    br = Brain.compute(sentence, msg.getScope());
    if (br.isPresent()) {
      BrainResponse answer = br.get();
      //TODO: add refinement here.
      return new Message(answer.getMessage(), answer.getScope(), emotion);
    }

    // We have understood something but we are unable to answer now!
    return new Message("Sorry my head hurts, what were we talking about?", "", emotion);
  }
}
