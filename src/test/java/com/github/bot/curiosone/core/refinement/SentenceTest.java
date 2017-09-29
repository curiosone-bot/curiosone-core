package com.github.bot.curiosone.core.refinement;

import static org.junit.Assert.assertTrue;

import java.util.stream.Collectors;

import org.junit.Test;

public class SentenceTest {

  private Sentence a;
  
  public SentenceTest() {
    a = new Sentence(SentenceType.Answer);
    a.add(new Word("cake", WordPart.Noun));
    a.add(new Word("be",   WordPart.Verb));
    a.add(new Word("lie",  WordPart.Noun));
    System.out.println(a.getWords().map(Word::getForm).collect(Collectors.joining(" ")));
  }
  
  @Test
  public void main() {
    assertTrue(a.getType().equals(SentenceType.Answer));
  }
}
