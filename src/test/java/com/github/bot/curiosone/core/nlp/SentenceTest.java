package com.github.bot.curiosone.core.nlp;

// SUPPRESS CHECKSTYLE AvoidStarImport
import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.junit.Test;

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

    assertTrue(sentence.respect(POS.PRON, POS.VP));
    assertTrue(sentence.respect(POS.PRON, POS.V, POS.DET, POS.N));
    assertFalse(sentence.respect(POS.PRON, POS.VP, POS.N));
    assertFalse(sentence.respect(POS.PRON, POS.N));

    List<Word>[] parameters = sentence.parse(POS.PRON, POS.V, POS.NP);
    assertTrue(parameters.length == 3);
    System.out.println(parameters[0]);
    System.out.println(parameters[1]);
    System.out.println(parameters[2]);


    List<Word> words = sentence.get(POS.V);
    assertTrue(words.size() > 0);
    System.out.println(words);
  }
}
