package com.github.bot.curiosone.core.refinement;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class SentenceTest {

  private Sentence sen;
  
  /**
   * Constructor.
   */
  public SentenceTest() {
    
    List<Word> ws = new ArrayList<>();
    ws.add(new Word("cake", WordPart.Noun));
    ws.add(new Word("be",   WordPart.Verb));
    ws.add(new Word("lie",  WordPart.Noun));
    sen = new Sentence(Sentence.Type.Answer, ws);
  }
  
  @Test
  public void main() {
    System.out.println("hello");
  }
}
