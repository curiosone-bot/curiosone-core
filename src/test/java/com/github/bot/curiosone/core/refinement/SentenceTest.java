package com.github.bot.curiosone.core.refinement;

import java.util.ArrayList;
import java.util.List;

public class SentenceTest {

  /**
   * Constructor.
   */
  public SentenceTest() {
    
    List<Word> ws = new ArrayList<>();
    ws.add(new Word("cake", WordPart.Noun));
    ws.add(new Word("be",   WordPart.Verb));
    ws.add(new Word("lie",  WordPart.Noun));
    
    System.out.println(">: " + ws);
    
    Sentence sen = new Sentence(Sentence.Type.Answer, ws);

    //System.out.println(sen.refine());
  }
}
