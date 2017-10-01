package com.github.bot.curiosone.core.workflow;

import com.github.bot.curiosone.core.extraction.Brain;
import com.github.bot.curiosone.core.extraction.BrainResponse;
import com.github.bot.curiosone.core.nlp.Phrase;
import com.github.bot.curiosone.core.nlp.Sentence;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

/**
 * Description.
 */
public class Logic {
  /**
   * talk description.
   * @param  msg [description]
   * @return [description]
   */
  public static Message talk(Message msg) {
    // We are not able to parse a null string :(
    if (msg == null) {
      return new Message("Sorry my head hurts, what were we talking about?", "");
    }
    Optional<BrainResponse> br;

    // We just use the first now.
    List<Phrase> phrases = Phrase.extract(msg.getMessage());
    if (phrases.size() == 0) {
      return new Message("Sorry my head hurts, what were we talking about?", "");
    }
    Phrase phrase = phrases.get(0);

    //TODO: add analysis here.

    // If it's a conversational text answer directly.
    br = Brain.conversate(phrase);
    if (br.isPresent()) {
      BrainResponse answer = br.get();
      return new Message(answer.getMessage(), answer.getScope());
    }

    // We just use the first now.
    List<Sentence> sentences = Sentence.extract(phrase);
    if (sentences.size() == 0) {
      BrainResponse answer = Brain.random(phrase);
      return new Message(answer.getMessage(), answer.getScope());
    }
    Sentence sentence = sentences.get(0);

    br = Brain.compute(sentence, msg.getScope());
    if (br.isPresent()) {
      BrainResponse answer = br.get();
      //TODO: add refinement here.
      return new Message(answer.getMessage(), answer.getScope());
    }

    // We have understood something but we are unable to answer now!
    return new Message("Sorry my head hurts, what were we talking about?", "");
  }
}