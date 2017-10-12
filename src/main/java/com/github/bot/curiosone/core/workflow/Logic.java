package com.github.bot.curiosone.core.workflow;

import com.github.bot.curiosone.core.analysis.EmotionAnalysis;
import com.github.bot.curiosone.core.extraction.Brain;
import com.github.bot.curiosone.core.extraction.BrainResponse;
import com.github.bot.curiosone.core.nlp.POS;
import com.github.bot.curiosone.core.nlp.Phrase;
import com.github.bot.curiosone.core.nlp.Sentence;
import com.github.bot.curiosone.core.nlp.Token;
import com.github.bot.curiosone.core.refinement.Refiner;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

/**
 * Manages the Logic Layer of the Curiosone.
 * This class consists of a static method used to generate an answer to a provided message.
 */
public class Logic {

  /**
   * Tries to compute an answer to a given Message.
   * @param msg the Message to be answered. Can be null.
   * @return a Message instance.
   *         If an answer has been found, the instance contains a choerent reply body.
   *         Otherwise, a default message is embedded in the Message instance
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
      /*
      String subject = answer.getScope();
      String verb = findFirstVerb(answer.getMessage());
      String object = "";
      boolean sp = false;
      boolean vp = false;
      boolean op = false;

      String refined = Refiner.refine(subject, verb, object, sp, vp, op);
      //TODO: add refinement here.
      */
      return new Message(answer.getMessage(), answer.getScope(), emotion);
    }

    // We have understood something but we are unable to answer now!
    return new Message("Sorry my head hurts, what were we talking about?", "", emotion);
  }

  /**
   * Finds the first verb in the given Sentence.
   * @param  String
   *         the sentence to search in
   * @return  the String representation of the first verb in the given sentence
   * @see  Token The Token class
   * @see  POS The Part of Speech Type Enum
   */
  private static final String findFirstVerb(String sentence) {
    List<Token> tl = Token.tokenize(sentence);

    return tl.stream().filter(t -> {
      POS p = t.getMeanings().iterator().next().getPOS();
      return p.equals(POS.V) || p.equals(POS.VP);
    })
      .findFirst()
      .toString();
  }
}
