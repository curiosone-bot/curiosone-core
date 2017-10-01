package com.github.bot.curiosone.core.refinement;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.github.bot.curiosone.core.refinement.word.Noun;

public class SentenceTest {

  private Sentence sen;
  
  /**
   * Constructor.
   */
  public SentenceTest() {
    
    List<Word> ws = new ArrayList<>();
    ws.add(new Noun("cake"));
    ws.add(new Word("be"));
    ws.add(new Noun("lie"));
    sen = new Sentence(Sentence.Type.Answer, ws);
  }
  
  @Test
  public void main() {
    System.out.println(sen.refine());
  }
}
