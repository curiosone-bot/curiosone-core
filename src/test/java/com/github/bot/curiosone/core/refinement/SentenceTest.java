package com.github.bot.curiosone.core.refinement;

import org.junit.Test;

public class SentenceTest {

  @Test
  public void main() {
    Sentence sen = new Sentence(SentenceType.Answer);
    sen.add(new Word("cake"));
    sen.add(new Word("be"));
    sen.add(new Word("lie"));
    System.out.println(sen);
  }
}
