package com.github.bot.curiosone.core.refinement;

import org.junit.Test;

public class TestSentence {

  @Test
  public void main() {
    
    MainClause mc = new MainClause(
        new NounPhrase(new NounWord(NounType.Plural, "cat")), //subject
        new VerbPhrase(new VerbWord("go"))                    //verb
    );

    Sentence s = new Sentence(SentenceType.Answer, mc);
    
    System.out.println(s);
  }
  
}
