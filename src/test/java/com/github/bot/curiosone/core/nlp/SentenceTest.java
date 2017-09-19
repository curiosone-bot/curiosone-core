package com.github.bot.curiosone.core.nlp;

// SUPPRESS CHECKSTYLE AvoidStarImport
import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import com.github.bot.curiosone.core.nlp.cyk.POS;
import com.github.bot.curiosone.core.nlp.cyk.Sentence;

public class SentenceTest {
  @Test
  public void testInstantiation() {
    List<Phrase> phrases = Phrase.extract("What is an apple?");
    System.out.println(phrases);

    assertTrue(phrases.size() == 1);
    Phrase phrase = phrases.get(0);

    List<Sentence> sentences = Sentence.extract(phrase);
    System.out.println(sentences);

    assertTrue(sentences.size() > 0);
    Sentence sentence = sentences.get(0);

    assertTrue(sentence.respect(new POS[] {POS.PRON, POS.VP}));
    assertTrue(sentence.respect(new POS[] {POS.PRON, POS.V, POS.DET, POS.N}));
    assertFalse(sentence.respect(new POS[] {POS.PRON, POS.VP, POS.N}));
    assertFalse(sentence.respect(new POS[] {POS.PRON, POS.N}));

    List<String>[] parameters = sentence.get(new POS[] {POS.PRON, POS.V, POS.NP});
    assertTrue(parameters.length == 3);
    System.out.println(parameters[0]);
    System.out.println(parameters[1]);
    System.out.println(parameters[2]);
  }
}
