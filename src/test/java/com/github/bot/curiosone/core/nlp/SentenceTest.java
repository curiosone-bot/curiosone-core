package com.github.bot.curiosone.core.nlp;

// SUPPRESS CHECKSTYLE AvoidStarImport
import static org.junit.Assert.*;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

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
  }
}
