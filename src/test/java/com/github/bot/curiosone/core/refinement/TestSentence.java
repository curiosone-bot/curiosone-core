package com.github.bot.curiosone.core.refinement;

import com.github.bot.curiosone.core.refinement.Sentence.Type;
import com.github.bot.curiosone.core.refinement.interfaces.Clause;
import com.github.bot.curiosone.core.refinement.interfaces.Phrase;

import org.junit.Test;

public class TestSentence {

  @Test
  public void main() {
    Sentence s = new Sentence(Type.Answer);
    Clause main;
    Phrase np = new PhraseNoun();
    Phrase vp = new PhraseVerb();
    main = new ClauseMain(np, vp);
    s.addClause(main);
    System.out.println(s);
  }
  
}
