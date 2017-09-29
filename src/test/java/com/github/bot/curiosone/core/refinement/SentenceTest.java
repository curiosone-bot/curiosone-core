package com.github.bot.curiosone.core.refinement;

import static org.junit.Assert.assertTrue;

import java.util.stream.Collectors;

import org.junit.Test;

public class SentenceTest {

  private Sentence sen;
  
  /**
   * Test constructor.
   */
  public SentenceTest() {
    sen = new Sentence(SentenceType.Answer);
    sen.add(new Word("cake", WordPart.Noun));
    sen.add(new Word("be",   WordPart.Verb));
    sen.add(new Word("lie",  WordPart.Noun));
  }
  
  @Test
  /**
   * Main test.
   */
  public void main() {
    assertTrue(sen.getType().equals(SentenceType.Answer));
    
    assertTrue(sen.getWords()
        .map(Word::getForm)
        .collect(Collectors.joining(" "))
        .equals("cake be lie"));
  }
}
