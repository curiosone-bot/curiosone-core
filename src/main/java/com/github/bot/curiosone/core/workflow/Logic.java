package com.github.bot.curiosone.core.workflow;

import com.github.bot.curiosone.core.extraction.Brain;
import com.github.bot.curiosone.core.extraction.BrainResponse;
import com.github.bot.curiosone.core.nlp.Phrase;
import com.github.bot.curiosone.core.nlp.Sentence;

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

    // We just use the first now.
    List<Phrase> phrases = Phrase.extract(msg.getMessage());
    if (phrases.size() == 0) {
      return new Message("Sorry my head hurts, what were we talking about?", "");
    }
    Phrase phrase = phrases.get(0);

    // If it's a conversational text answer directly.
    Optional<String> opt = Brain.conversate(phrase);
    if (opt.isPresent()) {
      return new Message(opt.get(), "");
    }

    // We just use the first now.
    List<Sentence> sentences = Sentence.extract(phrase);
    if (sentences.size() == 0) {
      return new Message("Sorry my head hurts, what were we talking about?", "");
    }
    Sentence sentence = sentences.get(0);

    Optional<BrainResponse> opta = Brain.compute(sentence, msg.getScope());
    if (opta.isPresent()) {
      BrainResponse answer = opta.get();
      //TODO: add analysis.
      //TODO: add refinement.
      return new Message(answer.getMessage(), answer.getScope());
    }
    return new Message("Sorry my head hurts, what were we talking about?", "");
  }
}
